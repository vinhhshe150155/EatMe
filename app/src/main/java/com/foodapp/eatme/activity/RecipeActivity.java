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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
import com.foodapp.eatme.util.LoadingDialog;
import com.foodapp.eatme.util.LocaleHelper;
import com.foodapp.eatme.util.NetworkUtil;
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
    private RecyclerView rcvListStep;
    private final List<Comment> comments = new ArrayList<>();
    private CommentAdapter adapter;
    private ImageView imgSubmitComment;
    private EditText edtComment;
    private Comment currentReplyComment;
    private LoadingDialog loadingDialog;
    private ChildComment childComment;
    private static final int COMMENT_NORMAL = 0;
    private static final int COMMENT_REPLY = 1;
    private static final int COMMENT_NESTED_REPLY = 2;
    private int commentStatus = COMMENT_NORMAL;
    private ConstraintLayout layoutRecipe;
    private RecipeDatabase database;
    private ImageView imgBack;
    private NutriListener nutriListener;
    private RecipeDetailsListener recipeDetailsListener;
    private RecipeExtend recipeExtend;
    private TextView tvKcal;
    private TextView tvProtein;
    private ConstraintLayout layoutShare;
    private TextView tvFat;
    private TextView tvCarbs;
    private ScrollView scrollView;
    private RecyclerView rcvIngredient;
    private ImageView imgCancelReplying;
    private TextView tvReplying;
    private LinearLayout layoutReplying;
    private LinearLayout layoutComment;
    private String typeRecipe = "on";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        initUI();
        initData();
        initAction();
    }

    @SuppressLint("SetTextI18n")
    private void initUI() {
        layoutComment = findViewById(R.id.layout_comment);
        layoutReplying = findViewById(R.id.layout_replying);
        imgCancelReplying = findViewById(R.id.img_cancel_replying);
        tvReplying = findViewById(R.id.tv_replying);
        layoutShare = findViewById(R.id.layout_share_recipe);
        scrollView = findViewById(R.id.scroll_view);
        tvKcal = findViewById(R.id.tv_recipe_detail_calories);
        imgShare = findViewById(R.id.img_share_recipe);
        tvProtein = findViewById(R.id.tv_recipe_detail_protein);
        tvFat = findViewById(R.id.tv_recipe_detail_fat);
        tvCarbs = findViewById(R.id.tv_recipe_detail_carbs);
        rcvIngredient = findViewById(R.id.rcv_recipe_detail_ingredient);
        btnLike = findViewById(R.id.btn_like_recipe);
        layoutRecipe = findViewById(R.id.layout_recipe);
        tvCommentEmpty = findViewById(R.id.tv_comment_empty);
        tvCommentEmpty.setVisibility(View.GONE);
        imgBack = findViewById(R.id.img_back);
        imgRecipe = findViewById(R.id.img_recipe);
        imgSubmitComment = findViewById(R.id.img_comment_submit);
        edtComment = findViewById(R.id.edt_comment);
        tvRecipeName = findViewById(R.id.tv_recipe_name);
        RecyclerView rcvListComment = findViewById(R.id.rcv_list_comment);
        rcvListComment.setHasFixedSize(false);
        rcvListComment.setLayoutManager(new LinearLayoutManager(this));
        loadingDialog = new LoadingDialog(this);
        rcvListStep = findViewById(R.id.rcv_list_step);
        layoutReplying.setVisibility(View.GONE);
        adapter = new CommentAdapter(comments, this, comment3 -> {
            currentReplyComment = comment3;
            commentStatus = COMMENT_REPLY;
            tvReplying.setText(getResources().getString(R.string.replying) + " " + comment3.getUsername() + ":");
            layoutReplying.setVisibility(View.VISIBLE);
            edtComment.requestFocus();
            showKeyboard();
        }, new IClickNestedComment() {
            @Override
            public void onClickReplyNestedComment(ChildComment comment, Comment comment2) {
                currentReplyComment = comment2;
                childComment = comment;
                commentStatus = COMMENT_NESTED_REPLY;
                tvReplying.setText(getResources().getString(R.string.replying) + " " + comment.getUsername() + ":");
                layoutReplying.setVisibility(View.VISIBLE);
                edtComment.requestFocus();
                showKeyboard();
            }

            @Override
            public void onClickReplyNestedComment1(ChildComment comment) {
            }
        });
        rcvListComment.setAdapter(adapter);
    }

    private void initData() {
        if (NetworkUtil.isNetworkAvailable(this)) {
            if (getIntent().getParcelableExtra("recipe") instanceof RecipeExtend) {
                initOnline(0);
            } else {
                initOnline(1);
            }

        } else {
            initOffline();
        }
        String currentLanguage = LocaleHelper.getCurrentLanguage();
        if (LocaleHelper.LANG_KR.equals(currentLanguage)) {
            detectLanguage();
        } else {
            initStepAdapter();
        }
    }

    private void initOffline() {
        loadingDialog.showDialog(null);
        typeRecipe = "off";
        database = RecipeDatabase.getInstance(getApplicationContext());
        recipeExtend = getIntent().getParcelableExtra("recipe");
        recipeExtend = database.recipeDAO().getItemById((long) recipeExtend.getId());
        tvRecipeName.setText(recipeExtend.getTitle());
        Glide.with(this).load(recipeExtend.getImage()).into(imgRecipe);
        steps = recipeExtend.getAnalyzedInstructions().get(0).getSteps();
        layoutComment.setVisibility(View.GONE);
        btnLike.setLiked(true);
        setNutriExtentInfo(recipeExtend.getNutriExtend());
        IngredientAdapter ingredientsAdapter = new IngredientAdapter(RecipeActivity.this, recipeExtend.getExtendedIngredients());
        rcvIngredient.setLayoutManager(new LinearLayoutManager(RecipeActivity.this, LinearLayoutManager.VERTICAL, false));
        rcvIngredient.setHasFixedSize(true);
        rcvIngredient.setAdapter(ingredientsAdapter);
    }

    private void initOnline(int type) {
        loadingDialog.showDialog(null);
        ApiRecipeDetailManager apiRecipeDetailManager = new ApiRecipeDetailManager();
        database = RecipeDatabase.getInstance(getApplicationContext());
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        initApiListener();
        //from saved list
        if (type == 0) {
            recipeExtend = getIntent().getParcelableExtra("recipe");
            initRecipe();
        } else {
            recipe = getIntent().getParcelableExtra("recipe");
            initRecipeExtend();
        }
        tvRecipeName.setText(recipe.getTitle());
        Glide.with(this).load(recipe.getImage()).into(imgRecipe);
        apiRecipeDetailManager.getNutriExtend(nutriListener, recipe.getId());
        apiRecipeDetailManager.getRecipeDetails(recipeDetailsListener, recipe.getId());
        if (!recipe.getAnalyzedInstructions().isEmpty()) {
            steps = recipe.getAnalyzedInstructions().get(0).getSteps();
        } else {
            steps = new ArrayList<>();
        }
        typeRecipe = "on";
        checkIfLiked();
        getComments();
    }

    private void initRecipeExtend() {
        recipeExtend = new RecipeExtend();
        recipeExtend.setTitle(recipe.getTitle());
        recipeExtend.setReadyInMinutes(recipe.getReadyInMinutes());
        recipeExtend.setId(recipe.getId());
        recipeExtend.setAnalyzedInstructions(recipe.getAnalyzedInstructions());
        recipeExtend.setCuisines(recipe.getCuisines());
        recipeExtend.setDiets(recipe.getDiets());
        recipeExtend.setDishTypes(recipe.getDishTypes());
        recipeExtend.setCookingMinutes(recipe.getCookingMinutes());
        recipeExtend.setImage(recipe.getImage());
        recipeExtend.setHealthScore(recipe.getHealthScore());
        recipeExtend.setPreparationMinutes(recipe.getPreparationMinutes());
    }

    private void initRecipe() {
        recipe = new Recipe();
        recipe.setTitle(recipeExtend.getTitle());
        recipe.setReadyInMinutes(recipeExtend.getReadyInMinutes());
        recipe.setId(recipeExtend.getId());
        recipe.setAnalyzedInstructions(recipeExtend.getAnalyzedInstructions());
        recipe.setCuisines(recipeExtend.getCuisines());
        recipe.setDiets(recipeExtend.getDiets());
        recipe.setDishTypes(recipeExtend.getDishTypes());
        recipe.setCookingMinutes(recipeExtend.getCookingMinutes());
        recipe.setImage(recipeExtend.getImage());
        recipe.setHealthScore(recipeExtend.getHealthScore());
        recipe.setPreparationMinutes(recipeExtend.getPreparationMinutes());
    }

    private void initStepAdapter() {
        RecipeStepAdapter adapter = new RecipeStepAdapter(steps);
        rcvListStep.setLayoutManager(new LinearLayoutManager(this));
        rcvListStep.setAdapter(adapter);
        loadingDialog.hideDialog();
        layoutRecipe.setVisibility(View.VISIBLE);
    }

    private void initAction() {
        btnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if (typeRecipe.equals("off")) {
                    btnLike.setLiked(true);
                    return;
                }
                saveRecipe();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if (typeRecipe.equals("off")) {
                    btnLike.setLiked(true);
                    return;
                }
                saveRecipe();
            }
        });
        imgSubmitComment.setOnClickListener(view -> sendComment());
        imgBack.setOnClickListener(view -> {
            finish();
            onBackPressed();
        });
        scrollView.setOnClickListener(view -> closeKeyboard());
        imgShare.setOnClickListener(view -> shareRecipe());
        imgCancelReplying.setOnClickListener(view -> cancelReplying());
    }

    private void cancelReplying() {
        commentStatus = COMMENT_NORMAL;
        layoutReplying.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void saveRecipe() {
        if (database.recipeDAO().getItemById((long) recipeExtend.getId()) != null) {
            database.recipeDAO().delete(recipeExtend.getId());
        } else {
            database.recipeDAO().insert(recipeExtend);
        }
        addRecipeToFirebase();
        if (!isLiked) {
            Toasty.custom(this, R.string.saved_cap, R.drawable.heart_hand, R.color.pink_light, Toast.LENGTH_SHORT, true,
                    true).show();
        } else {
            Toasty.custom(this, R.string.unliked, R.drawable.broken_heart, R.color.gray_light, Toast.LENGTH_SHORT, true,
                    true).show();
        }
        isLiked = !isLiked;
    }

    private void sendComment() {
        if (edtComment.getText().toString().trim().equals("")) {
            return;
        }
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
        cancelReplying();
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
                            englishKoreanTranslator.translate(recipe.getTitle())
                                    .addOnSuccessListener(
                                            translatedText -> {
                                                tvRecipeName.setText(translatedText);
                                                recipe.setTitle(translatedText);
                                                recipe.setTitle(translatedText);
                                            })
                                    .addOnFailureListener(
                                            e -> Log.e("Translate", e.getMessage()));
                            if (!steps.isEmpty()) {
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
                                                            loadingDialog.hideDialog();
                                                            layoutRecipe.setVisibility(View.VISIBLE);
                                                        }
                                                    }
                                            )
                                            .addOnFailureListener(
                                                    e -> Log.e("Translate", e.getMessage()));
                                }
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
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        ref.push().setValue(recipeExtend);
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
                Toasty.error(RecipeActivity.this, R.string.error, Toast.LENGTH_SHORT, true).show();
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
                    recipeExtend.setNutriExtend(nutriExtend);
                    setNutriExtentInfo(nutriExtend);
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
                if (response != null) {
                    recipeExtend.setExtendedIngredients(response.getExtendedIngredients());
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

    private void setNutriExtentInfo(NutriExtend nutriExtend) {
        tvKcal.setText(nutriExtend.getCalories().replace("k", ""));
        tvProtein.setText(nutriExtend.getProtein());
        tvFat.setText(nutriExtend.getFat());
        tvCarbs.setText(nutriExtend.getCarbs());
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.showSoftInput(view, 0);
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