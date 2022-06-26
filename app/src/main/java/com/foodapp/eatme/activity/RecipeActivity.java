package com.foodapp.eatme.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.CommentAdapter;
import com.foodapp.eatme.adapter.RecipeStepAdapter;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.dao.RecipeDatabase;
import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.model.Comment;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.model.Step;
import com.foodapp.eatme.util.LocaleHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.languageid.LanguageIdentifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class RecipeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Recipe recipe;
    TextView tvRecipeName;
    ImageView imgSave;
    ImageView imgRecipe;
    TextView tvCommentEmpty;
    private boolean isLiked;
    private final TranslatorOptions options = new TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.KOREAN)
            .build();
    final Translator englishKoreanTranslator = Translation.getClient(options);
    List<Step> steps;
    private RecyclerView rcvListComment;
    private RecyclerView rcvListStep;
    private final List<Comment> comments = new ArrayList<>();
    private CommentAdapter adapter;
    private Button btnComment;
    private EditText edtComment;
    private Comment currentReplyComment;
    private LinearLayout layoutLoading;
    private ChildComment childComment;
    private static final int COMMENT_NORMAL = 0;
    private static final int COMMENT_REPLY = 1;
    private static final int COMMENT_NESTED_REPLY = 2;
    private int commentStatus = COMMENT_NORMAL;
    private String currentLanguage;
    private ConstraintLayout layoutRecipe;
    RecipeDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        initUI();
        initData();
        initAction();
    }

    private void initUI() {
        imgSave = findViewById(R.id.img_save_recipe);
        layoutRecipe = findViewById(R.id.layout_recipe);
        layoutLoading = findViewById(R.id.layout_loading);
        tvCommentEmpty = findViewById(R.id.tv_comment_empty);
        tvCommentEmpty.setVisibility(View.GONE);
        imgRecipe = findViewById(R.id.img_recipe);
        btnComment = findViewById(R.id.btn_comment_submit);
        edtComment = findViewById(R.id.edt_comment);
        tvRecipeName = findViewById(R.id.tv_recipe_name);
        rcvListComment = findViewById(R.id.rcv_list_comment);
        rcvListComment.setHasFixedSize(true);
        rcvListComment.setLayoutManager(new LinearLayoutManager(this));
        rcvListStep = findViewById(R.id.rcv_list_step);
        adapter = new CommentAdapter(comments, this, comment3 -> {
            currentReplyComment = comment3;
            commentStatus = COMMENT_REPLY;
            edtComment.setText(currentReplyComment.getUsername());
        }, new IClickNestedComment() {
            @Override
            public void onClickReplyNestedComment(ChildComment comment, Comment comment2) {
                currentReplyComment = comment2;
                childComment = comment;
                commentStatus = COMMENT_NESTED_REPLY;
                edtComment.setText(childComment.getUsername());
            }

            @Override
            public void onClickReplyNestedComment1(ChildComment comment) {
            }
        });
        rcvListComment.setAdapter(adapter);
    }

    private void initData() {
        database = RecipeDatabase.getInstance(getApplicationContext());
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        recipe = getIntent().getParcelableExtra("recipe");
        tvRecipeName.setText(recipe.getSourceName());
        Glide.with(this).load(recipe.getImage()).into(imgRecipe);
        steps = recipe.getAnalyzedInstructions().get(0).getSteps();
        currentLanguage = LocaleHelper.getCurrentLanguage();
        checkIfLiked();
        getComments();
        switch (currentLanguage) {
            case LocaleHelper.LANG_KR:
                detectLanguage();
                break;
            default:
                initStepAdapter();
        }
    }

    private void initStepAdapter() {
        RecipeStepAdapter adapter = new RecipeStepAdapter(steps);
        rcvListStep.setLayoutManager(new LinearLayoutManager(this));
        rcvListStep.setAdapter(adapter);
        layoutLoading.setVisibility(View.GONE);
        layoutRecipe.setVisibility(View.VISIBLE);
    }

    private void initAction() {
        imgSave.setOnClickListener(view -> saveRecipe());
        btnComment.setOnClickListener(view -> sendComment());
    }

    private void saveRecipe() {
        database.recipeDAO().insert(recipe);
        addRecipeToFirebase();
        Toasty.normal(this, isLiked ? "Unliked" : "Saved").show();
        isLiked = !isLiked;
    }

    private void sendComment() {
        switch (commentStatus) {
            case 0:
                normalComment();
                break;
            case 1:
                replyComment();
                break;
            case 2:
                replyNestedComment();
                break;
        }
        commentStatus = COMMENT_NORMAL;
    }

    private void replyNestedComment() {
        String content = edtComment.getText().toString().trim();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        edtComment.setText("");
        DatabaseReference reference;
        String id = currentReplyComment.getCommentId();
        reference = FirebaseDatabase.getInstance().getReference().child("recipe").child(Integer.toString(recipe.getId())).child("comments").child(id).child("reply");
        String key = reference.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("commentId", key);
        map.put("timestamp", System.currentTimeMillis());
        map.put("username", user.getDisplayName() == null ? "User" : user.getDisplayName());
        map.put("userReply", childComment.getUsername());
        if (key != null) {
            reference.child(key).setValue(map);
        }
    }

    private void replyComment() {
        String content = edtComment.getText().toString().trim();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        edtComment.setText("");
        DatabaseReference reference;
        String id = currentReplyComment.getCommentId();
        reference = FirebaseDatabase.getInstance().getReference().child("recipe").child(Integer.toString(recipe.getId())).child("comments").child(id).child("reply");
        String key = reference.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("commentId", key);
        map.put("timestamp", System.currentTimeMillis());
        map.put("username", user.getDisplayName() == null ? "User" : user.getDisplayName());
        map.put("userReply", currentReplyComment.getUsername());
        if (key != null) {
            reference.child(key).setValue(map);
        }
    }

    private void normalComment() {
        String content = edtComment.getText().toString().trim();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        edtComment.setText("");
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        String key = reference.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put("isExpandable", false);
        map.put("username", user.getDisplayName() == null ? "User" : user.getDisplayName());
        map.put("postId", recipe.getId());
        map.put("content", content);
        map.put("timestamp", System.currentTimeMillis());
        map.put("userId", user.getUid() + "");
        map.put("commentId", key);
        if (key != null) {
            reference.child("recipe").child(Integer.toString(recipe.getId())).child("comments").child(key).setValue(map);
        }
    }

    private void getComments() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("recipe").child(Integer.toString(recipe.getId())).child("comments");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Comment> newComment = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Comment comment = postSnapshot.getValue(Comment.class);
                    if (comment != null) {
                        comment.setExpandable(false);
                        newComment.add(comment);
                    }
                }
                Collections.sort(newComment, (c1, c2) -> Long.compare(c2.getTimestamp(), c1.getTimestamp()));
                comments.clear();
                comments.addAll(newComment);
                if (comments.isEmpty()) {
                    tvCommentEmpty.setVisibility(View.VISIBLE);
                } else {
                    comments.size();
                    tvCommentEmpty.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("cmt", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void toKor() {
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        englishKoreanTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        v -> {
                            Log.e("Translate", "Success");
                            englishKoreanTranslator.translate(recipe.getSourceName())
                                    .addOnSuccessListener(
                                            translatedText -> tvRecipeName.setText(translatedText))
                                    .addOnFailureListener(
                                            e -> Log.e("Translate", e.getMessage()));
                            for (int i = 0; i < steps.size(); i++) {
                                Step step = steps.get(i);
                                int index = i;
                                englishKoreanTranslator.translate(step.getStep())
                                        .addOnSuccessListener(
                                                str -> {
                                                    steps.get(index).setStep(str);
                                                    if (index == steps.size() - 1) {
                                                        RecipeStepAdapter adapter = new RecipeStepAdapter(steps);
                                                        rcvListStep.setLayoutManager(new LinearLayoutManager(this));
                                                        rcvListStep.setAdapter(adapter);
                                                        layoutLoading.setVisibility(View.GONE);
                                                        layoutRecipe.setVisibility(View.VISIBLE);
                                                    }
                                                }
                                        )
                                        .addOnFailureListener(
                                                e -> Log.e("Translate", e.getMessage()));
                            }
                        })
                .addOnFailureListener(e -> Log.e("Error", "Model could n’t be downloaded " + e));
    }

    private void detectLanguage() {
        LanguageIdentifier languageIdentifier =
                LanguageIdentification.getClient();
        languageIdentifier.identifyLanguage(steps.get(0).getStep())
                .addOnSuccessListener(
                        languageCode -> {
                            if (languageCode.equals(LocaleHelper.LANG_KR)) {
                                initStepAdapter();
                            } else {
                                toKor();
                            }
                        })
                .addOnFailureListener(
                        e -> {
                        });

    }

    private void addRecipeToFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        if (isLiked) {
            deleteRecipeFirebase();
            database.recipeDAO().delete(recipe.getId());
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        ref.push().setValue(recipe);
    }

    private void deleteRecipeFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        Query deleteQuery = ref.orderByChild("id").equalTo(recipe.getId());
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    recipeSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecipeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkIfLiked() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        Query query = ref.orderByChild("id").equalTo(recipe.getId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isLiked = dataSnapshot.getChildrenCount() > 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}