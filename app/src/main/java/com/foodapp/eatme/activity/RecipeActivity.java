package com.foodapp.eatme.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.CommentAdapter;
import com.foodapp.eatme.adapter.RecipeStepAdapter;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.model.Comment;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.model.Step;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RecipeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    Recipe recipe;
    TextView tvRecipeName;
    ImageView imgRecipe;
    TextView tvRecipeSummary;
    private
    TranslatorOptions options =
            new TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.KOREAN)
                    .build();
    final Translator englishKoreanTranslator =
            Translation.getClient(options);
    List<Step> steps;
    private final String LANG_EN = "en";
    private final String LANG_KR = "ko";
    private RecyclerView rcvListComment;
    private RecyclerView rcvListStep;
    private List<Comment> comments = new ArrayList<>();
    private CommentAdapter adapter;
    private Button btnComment;
    private EditText edtComment;
    private Comment currentReplyComment;
    private boolean isReplying = false;
    private ChildComment childComment;
    RemoteModelManager modelManager = RemoteModelManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        initUI();
        initData();
        initAction();
    }

    private void initUI() {
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
            isReplying = true;
            edtComment.setText(currentReplyComment.getUsername());
        }, new IClickNestedComment() {
            @Override
            public void onClickReplyNestedComment(ChildComment comment, Comment comment2) {
                currentReplyComment = comment2;
                childComment = comment;
                isReplying = true;
                edtComment.setText(childComment.getUsername());
            }

            @Override
            public void onClickReplyNestedComment1(ChildComment comment) {
            }
        });
        rcvListComment.setAdapter(adapter);
    }

    private void initData() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        recipe = getIntent().getParcelableExtra("recipe");
        tvRecipeName.setText(recipe.getSourceName());
        Glide.with(this).load(recipe.getImage()).into(imgRecipe);
        steps = recipe.getAnalyzedInstructions().get(0).getSteps();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("Locale.Helper.Selected.Language", null);
        getComments();
        if (lang == null) {
            lang = Locale.getDefault().getLanguage();
        }
        if (lang.equals(LANG_KR)) {
            for (int i = 0; i < steps.size(); i++) {
                Step step = steps.get(i);
                translate(step.getStep(), i);
            }
            toKor();
        } else {
            RecipeStepAdapter adapter = new RecipeStepAdapter(steps);
            rcvListStep.setLayoutManager(new LinearLayoutManager(this));
            rcvListStep.setAdapter(adapter);
        }
    }

    private void initAction() {
        btnComment.setOnClickListener(view -> sendComment());
    }

    private void sendComment() {
        String content = edtComment.getText().toString().trim();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        edtComment.setText("");
        DatabaseReference reference;
        if (isReplying) {
            String id = currentReplyComment.getCommentId();
            reference = FirebaseDatabase.getInstance().getReference().child("recipe").child(Integer.toString(recipe.getId())).child("comments").child(id).child("reply");
            String key = reference.push().getKey();
            Map<String, Object> map = new HashMap<>();
            map.put("content", content);
            map.put("commentId", key);
            map.put("timestamp", System.currentTimeMillis());
            map.put("username", user.getDisplayName() == null ? "User" : user.getDisplayName());
            reference.child(key).setValue(map);
        } else {
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
            reference.child("recipe").child(Integer.toString(recipe.getId())).child("comments").child(key).setValue(map);
        }

    }

    private void getComments() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("recipe").child(Integer.toString(recipe.getId())).child("comments");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> newComment = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Comment comment = postSnapshot.getValue(Comment.class);
                    comment.setExpandable(false);
                    if (comment != null) {
                        newComment.add(comment);
                    }
                }

                if (newComment != null) {
                    Collections.sort(newComment, (c1, c2) -> {
                        if (c1.getTimestamp() < c2.getTimestamp()) {
                            return 1;
                        }
                        if (c1.getTimestamp() == c2.getTimestamp()) {
                            return 0;
                        }
                        return -1;
                    });
                    comments.clear();
                    comments.addAll(newComment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
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
                        v -> Log.e("Translate", "Success"))
                .addOnFailureListener(
                        e -> {
                            TranslateRemoteModel germanModel =
                                    new TranslateRemoteModel.Builder(TranslateLanguage.KOREAN).build();
                            modelManager.deleteDownloadedModel(germanModel)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void v) {
                                            TranslateRemoteModel frenchModel =
                                                    new TranslateRemoteModel.Builder(TranslateLanguage.KOREAN).build();
                                            DownloadConditions conditions = new DownloadConditions.Builder()
                                                    .requireWifi()
                                                    .build();
                                            modelManager.download(frenchModel, conditions)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void v) {
                                                            // Model downloaded.
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Error.
                                                        }
                                                    });
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Error.
                                        }
                                    });
                            Log.e("Translate", e.getMessage());
                        });

        englishKoreanTranslator.translate(recipe.getSourceName())
                .addOnSuccessListener(
                        translatedText -> tvRecipeName.setText(translatedText))
                .addOnFailureListener(
                        e -> Log.e("Translate", e.getMessage()));
    }

    private void translate(String s, int i) {
        englishKoreanTranslator.translate(s)
                .addOnSuccessListener(
                        str -> {
                            steps.get(i).setStep(str);
                            if (i == steps.size() - 1) {
                                RecipeStepAdapter adapter = new RecipeStepAdapter(steps);
                                rcvListStep.setLayoutManager(new LinearLayoutManager(this));
                                rcvListStep.setAdapter(adapter);
                            }
                        }
                )
                .addOnFailureListener(
                        e -> Log.e("Translate", e.getMessage()));
    }


}