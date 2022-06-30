package com.foodapp.eatme.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.CommentAdapter;
import com.foodapp.eatme.adapter.IngredientAdapter;
import com.foodapp.eatme.adapter.RecipeStepAdapter;
import com.foodapp.eatme.api.ApiRecipeDetailManager;
import com.foodapp.eatme.api.NutriListener;
import com.foodapp.eatme.api.RecipeDetailsListener;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.dao.RecipeDatabase;
import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.model.Comment;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.model.Step;
import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.foodapp.eatme.util.LocaleHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.languageid.LanguageIdentifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.ByteArrayOutputStream;
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
    LikeButton btnLike;
    ImageView imgRecipe;
    ImageView imgShare;
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
    private ImageView imgSubmitComment;
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
    private RecipeDatabase database;
    private ImageView imgBack;
    private ApiRecipeDetailManager apiRecipeDetailManager;
    private NutriListener nutriListener;
    private RecipeDetailsListener recipeDetailsListener;
    private RecipeExtend recipeExtend;
    private TextView tvKcal;
    private TextView tvProtein;
    private ConstraintLayout layoutShare;
    private TextView tvFat;
    private TextView tvCarbs;
    private NestedScrollView nestedScrollView;
    private RecyclerView rcvIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        initUI();
        initData();
        initAction();
    }

    private void initUI() {
        layoutShare = findViewById(R.id.layout_share_recipe);
        nestedScrollView = findViewById(R.id.nested_scroll_view);
        tvKcal = findViewById(R.id.tv_recipe_detail_calories);
        imgShare = findViewById(R.id.img_share_recipe);
        tvProtein = findViewById(R.id.tv_recipe_detail_protein);
        tvFat = findViewById(R.id.tv_recipe_detail_fat);
        tvCarbs = findViewById(R.id.tv_recipe_detail_carbs);
        rcvIngredient = findViewById(R.id.rcv_recipe_detail_ingredient);
        btnLike = findViewById(R.id.btn_like_recipe);
        layoutRecipe = findViewById(R.id.layout_recipe);
        layoutLoading = findViewById(R.id.layout_loading);
        tvCommentEmpty = findViewById(R.id.tv_comment_empty);
        tvCommentEmpty.setVisibility(View.GONE);
        imgBack = findViewById(R.id.img_back);
        imgRecipe = findViewById(R.id.img_recipe);
        imgSubmitComment = findViewById(R.id.img_comment_submit);
        edtComment = findViewById(R.id.edt_comment);
        tvRecipeName = findViewById(R.id.tv_recipe_name);
        rcvListComment = findViewById(R.id.rcv_list_comment);
        rcvListComment.setHasFixedSize(true);
        rcvListComment.setLayoutManager(new LinearLayoutManager(this));
        rcvListStep = findViewById(R.id.rcv_list_step);
        adapter = new CommentAdapter(comments, this, comment3 -> {
            currentReplyComment = comment3;
            commentStatus = COMMENT_REPLY;
        }, new IClickNestedComment() {
            @Override
            public void onClickReplyNestedComment(ChildComment comment, Comment comment2) {
                currentReplyComment = comment2;
                childComment = comment;
                commentStatus = COMMENT_NESTED_REPLY;
            }

            @Override
            public void onClickReplyNestedComment1(ChildComment comment) {
            }
        });
        rcvListComment.setAdapter(adapter);
    }

    private void initData() {
        apiRecipeDetailManager = new ApiRecipeDetailManager(this);
        initApiListener();
        database = RecipeDatabase.getInstance(getApplicationContext());
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        recipe = getIntent().getParcelableExtra("recipe");
        tvRecipeName.setText(recipe.getSourceName());
        Glide.with(this).load(recipe.getImage()).into(imgRecipe);
        steps = recipe.getAnalyzedInstructions().get(0).getSteps();
        currentLanguage = LocaleHelper.getCurrentLanguage();
        apiRecipeDetailManager.getNutriExtend(nutriListener, recipe.getId());
        apiRecipeDetailManager.getRecipeDetails(recipeDetailsListener, recipe.getId());

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
        btnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                saveRecipe();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                saveRecipe();
            }
        });
        imgSubmitComment.setOnClickListener(view -> sendComment());
        imgBack.setOnClickListener(view -> {
            finish();
            onBackPressed();
        });
        nestedScrollView.setOnClickListener(view -> closeKeyboard());
        imgShare.setOnClickListener(view -> shareRecipe());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void saveRecipe() {
        database.recipeDAO().insert(recipe);
        addRecipeToFirebase();
        Toasty.normal(this, isLiked ? "Unliked" : "Saved").show();
        isLiked = !isLiked;
    }

    private void sendComment() {
        closeKeyboard();
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
                btnLike.setLiked(isLiked);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initApiListener() {
        nutriListener = new NutriListener() {
            @Override
            public void didFetch(NutriExtend nutriExtend, String message) {
                if (nutriExtend != null) {
                    tvKcal.setText(nutriExtend.getCalories().replace("k", ""));
                    tvProtein.setText(nutriExtend.getProtein());
                    tvFat.setText(nutriExtend.getFat());
                    tvCarbs.setText(nutriExtend.getCarbs());
                }
            }

            @Override
            public void didError(String message) {
                Log.e("Nutri", message);
            }
        };
        recipeDetailsListener = new RecipeDetailsListener() {
            @Override
            public void didFetch(RecipeExtend response, String message) {
                recipeExtend = response;
                if (recipeExtend != null) {
                    IngredientAdapter ingredientsAdapter = new IngredientAdapter(RecipeActivity.this, recipeExtend.getExtendedIngredients());
                    rcvIngredient.setLayoutManager(new LinearLayoutManager(RecipeActivity.this, LinearLayoutManager.VERTICAL, false));
                    rcvIngredient.setHasFixedSize(true);
                    rcvIngredient.setAdapter(ingredientsAdapter);
                }

            }

            @Override
            public void didError(String message) {
                Log.e("recipeDetails", message);
            }
        };

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void shareRecipe() {
        Bitmap bitmap = Bitmap.createBitmap(layoutShare.getWidth(), layoutShare.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(ContextCompat.getColor(this, R.color.white));
        layoutShare.draw(canvas);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                bitmap, "Eat Me", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Share recipe through..."));
    }
}