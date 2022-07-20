package com.foodapp.eatme.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.DietAdapter;
import com.foodapp.eatme.adapter.ListSearchRecipeAdapter;
import com.foodapp.eatme.adapter.MealTypeFilterAdapter;
import com.foodapp.eatme.api.ApiService;
import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.model.Diet;
import com.foodapp.eatme.model.MealType;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.util.LoadingDialog;
import com.foodapp.eatme.util.NetworkUtil;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRecipeActivity extends AppCompatActivity {
    private RecyclerView rcvListRecipe;
    private String searchIngredients;
    ConstraintLayout layoutContainer;
    private String mealType;
    private List<Recipe> recipes;
    private SearchView searchView;
    private RecyclerView rcvMealTypeFilter;
    private RecyclerView rcvDietFilter;
    private List<MealType> mealTypes;
    private List<Diet> diets;
    private MealTypeFilterAdapter mealTypeFilterAdapter;
    private DietAdapter dietAdapter;
    private TextView tvMealTypeMore;
    private TextView tvDietMore;
    private LoadingDialog loadingDialog;
    private final String SORT_DESCENDING = "desc";
    private final String SORT_ASCENDING = "asc";
    private TextView tvSuitable;
    private TextView tvPopularity;
    private TextView tvCalories;
    private TextView tvHealthiness;
    private TextView tvTime;
    private boolean isSuitable;
    private boolean isPopularity;
    private boolean isCalories;
    private boolean isHealthiness;
    private boolean isTime;
    private String sortBy;
    private String sortDirectionCalories;
    private String sortDirectionHealthiness;
    private String sortDirectionTime;
    private ImageView imgDirectionCalories;
    private ImageView imgDirectionHealthiness;
    private ImageView imgDirectionTime;
    private String diet;
    private int minCalories = 0;
    private int maxCalories = 5000;
    private int maxTime = 1000;
    private int minTime = 0;
    private RangeSlider rsCalories;
    private RangeSlider rsTime;
    private CardView cvApply;
    private CardView cvReset;
    private ImageView imgBack;
    private ImageView imgFilter;
    DrawerLayout drawerLayout;
    private LinearLayout layoutSearchEmpty;
    private LottieAnimationView lostConnection;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        initUI();
        initData();
        initAction();
    }


    private void initUI() {
        refreshLayout = findViewById(R.id.refresh_layout);
        lostConnection = findViewById(R.id.lost_connection);
        layoutSearchEmpty = findViewById(R.id.layout_empty_search);
        drawerLayout = findViewById(R.id.drawer_layout);
        imgFilter = findViewById(R.id.img_filter);
        imgBack = findViewById(R.id.img_back);
        rsCalories = findViewById(R.id.rs_calories);
        rsTime = findViewById(R.id.rs_time);
        cvApply = findViewById(R.id.cv_apply);
        cvReset = findViewById(R.id.cv_reset);
        imgDirectionCalories = findViewById(R.id.img_calories_direction);
        imgDirectionHealthiness = findViewById(R.id.img_healthiness_direction);
        imgDirectionTime = findViewById(R.id.img_time_direction);
        tvSuitable = findViewById(R.id.tv_suitable);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvCalories = findViewById(R.id.tv_calories);
        tvHealthiness = findViewById(R.id.tv_healthiness);
        tvTime = findViewById(R.id.tv_time);
        loadingDialog = new LoadingDialog(this);
        rcvDietFilter = findViewById(R.id.rcv_diet_filter);
        tvDietMore = findViewById(R.id.tv_diet_more);
        tvMealTypeMore = findViewById(R.id.tv_meal_type_more);
        rcvMealTypeFilter = findViewById(R.id.rcv_mealtype_filter);
        layoutContainer = findViewById(R.id.layout_search_recipe);
        searchView = findViewById(R.id.searchView);
        rcvListRecipe = findViewById(R.id.rcv_list_recipe);
    }

    private void initData() {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            lostConnection.setVisibility(View.VISIBLE);
        }else {
            lostConnection.setVisibility(View.GONE);
        }
        initSortDirection();
        initSortBy();
        searchIngredients = getIntent().getStringExtra("ingredients");
        diet = "";
        mealType = getIntent().getStringExtra("mealType");
        sortSuitable(null);
        initSortByString();
        initMealType();
        initDiet();
        initRcvDiet();
        initRcvMealType();
        searchView.setQuery(searchIngredients, false);
    }

    private void initSortBy() {
        tvSuitable.setTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        tvPopularity.setTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        tvTime.setTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        tvHealthiness.setTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        tvCalories.setTextColor(ContextCompat.getColor(this, R.color.gray_dark));
        isSuitable = false;
        isPopularity = false;
        isCalories = false;
        isHealthiness = false;
        isTime = false;
    }

    private void initSortByString() {
        if (isSuitable) sortBy = "min-missing-ingredients";
        if (isPopularity) sortBy = "popularity";
        if (isCalories) sortBy = "calories";
        if (isHealthiness) sortBy = "healthiness";
        if (isTime) sortBy = "time";
    }

    private void initSortDirection() {
        imgDirectionCalories.setImageResource(R.drawable.ic_down);
        imgDirectionHealthiness.setImageResource(R.drawable.ic_down);
        imgDirectionTime.setImageResource(R.drawable.ic_down);
        sortDirectionCalories = "";
        sortDirectionHealthiness = "";
        sortDirectionTime = "";
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initRcvDiet() {
        dietAdapter = new DietAdapter(diets, diet1 -> {
            for (Diet d : diets) {
                d.setSelected(false);
            }
            diet = diet1.getName();
            diets.get(diets.indexOf(diet1)).setSelected(true);
            dietAdapter.notifyDataSetChanged();
        });
        rcvDietFilter.setLayoutManager(new GridLayoutManager(this, 2));
        rcvDietFilter.setAdapter(dietAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initRcvMealType() {
        for (MealType m : mealTypes) {
            if (m.getName().equals(mealType)) {
                m.setSelected(true);
            }
        }
        mealTypeFilterAdapter = new MealTypeFilterAdapter(mealTypes, mealType1 -> {
            for (MealType m : mealTypes) {
                m.setSelected(false);
            }
            mealType = mealType1.getName();
            mealTypes.get(mealTypes.indexOf(mealType1)).setSelected(true);
            mealTypeFilterAdapter.notifyDataSetChanged();
        });
        rcvMealTypeFilter.setLayoutManager(new GridLayoutManager(this, 2));
        rcvMealTypeFilter.setAdapter(mealTypeFilterAdapter);
    }

    private void initMealType() {
        mealTypes = new ArrayList<>();
        mealTypes.add(new MealType("main course", R.drawable.main_course, R.string.main_course, false));
        mealTypes.add(new MealType("soup", R.drawable.soup, R.string.soup, false));
        mealTypes.add(new MealType("snack", R.drawable.snack, R.string.snack, false));
        mealTypes.add(new MealType("appetizer", R.drawable.appetizer, R.string.appetizer, false));
        mealTypes.add(new MealType("dessert", R.drawable.dessert, R.string.dessert, false));
        mealTypes.add(new MealType("salad", R.drawable.salad, R.string.salad, false));
        mealTypes.add(new MealType("drink", R.drawable.drink, R.string.drink, false));
        mealTypes.add(new MealType("bread", R.drawable.bread, R.string.bread, false));
        mealTypes.add(new MealType("breakfast", R.drawable.breakfast, R.string.breakfast, false));
        mealTypes.add(new MealType("side dish", R.drawable.side_dish, R.string.side_dish, false));
        mealTypes.add(new MealType("sauce", R.drawable.sauce, R.string.sauce, false));
        mealTypes.add(new MealType("fingerfood", R.drawable.fingerfood, R.string.fingerfood, false));
    }

    private void initDiet() {
        diets = new ArrayList<>();
        diets.add(new Diet("Gluten Free", R.drawable.drink, R.string.gluten_free, false));
        diets.add(new Diet("Ketogenic", R.drawable.drink, R.string.ketogenic, false));
        diets.add(new Diet("Vegetarian", R.drawable.drink, R.string.vegetarian, false));
        diets.add(new Diet("Lacto-Vegetarian", R.drawable.drink, R.string.lacto_vegetarian, false));
        diets.add(new Diet("Ovo-Vegetarian", R.drawable.drink, R.string.ovo_vegetarian, false));
        diets.add(new Diet("Vegan", R.drawable.drink, R.string.vegan, false));
        diets.add(new Diet("Pescetarian", R.drawable.drink, R.string.pescetarian, false));
        diets.add(new Diet("Paleo", R.drawable.drink, R.string.paleo, false));
        diets.add(new Diet("Primal", R.drawable.drink, R.string.primal, false));
        diets.add(new Diet("Low FODMAP", R.drawable.drink, R.string.low_fodmap, false));
        diets.add(new Diet("Whole30", R.drawable.drink, R.string.whole30, false));
    }

    private void initAction() {
        refreshLayout.setOnRefreshListener(() -> {
            initUI();
            initData();
            refreshLayout.setRefreshing(false);
        });
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchIngredients = query;
                closeKeyboard();
                sort();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        layoutContainer.setOnClickListener(view -> closeKeyboard());
        tvMealTypeMore.setOnClickListener(view -> {
            tvMealTypeMore.setText(tvMealTypeMore.getText().toString().equals("More") ? "Show Less" : "More");
            mealTypeFilterAdapter.onClickExpand(mealTypes);
        });
        tvDietMore.setOnClickListener(view -> {
            tvDietMore.setText(tvDietMore.getText().toString().equals("More") ? "Show Less" : "More");
            dietAdapter.onClickExpand(diets);
        });
        tvCalories.setOnClickListener(this::sortCalories);
        tvPopularity.setOnClickListener(this::sortPopularity);
        tvHealthiness.setOnClickListener(this::sortHealthiness);
        tvTime.setOnClickListener(this::sortTime);
        tvSuitable.setOnClickListener(this::sortSuitable);
        cvApply.setOnClickListener(this::applyFilter);
        imgBack.setOnClickListener(view -> {
            finish();
            onBackPressed();
        });
        imgFilter.setOnClickListener(view -> {
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END);
            } else drawerLayout.closeDrawer(GravityCompat.END);
        });
        cvReset.setOnClickListener(this::resetFilter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void resetFilter(View view) {
        maxCalories = 5000;
        minCalories = 0;
        maxTime = 500;
        minTime = 0;
        mealType = "";
        diet = "";
        for (int i = 0, dietsSize = diets.size(); i < dietsSize; i++) {
            Diet d = diets.get(i);
            d.setSelected(false);
        }
        for (int i = 0, mealTypesSize = mealTypes.size(); i < mealTypesSize; i++) {
            MealType m = mealTypes.get(i);
            m.setSelected(false);
        }
        mealTypeFilterAdapter.notifyDataSetChanged();
        dietAdapter.notifyDataSetChanged();
        rsCalories.setValues(0f, 5000f);
        rsCalories.setValues(0f, 5000f);
        rsTime.setValues(0f, 500f);
        drawerLayout.closeDrawer(GravityCompat.END);
        searchRecipe();
    }

    private void applyFilter(View view) {
        List<Float> valuesCalories = rsCalories.getValues();
        minCalories = Math.round(Collections.min(valuesCalories));
        maxCalories = Math.round(Collections.max(valuesCalories));
        List<Float> valuesTime = rsTime.getValues();
        maxTime = Math.round(Collections.max(valuesTime));
        minTime = Math.round(Collections.min(valuesTime));
        drawerLayout.closeDrawer(GravityCompat.END);
        searchRecipe();
    }

    private void sortSuitable(View view) {
        initSortBy();
        isSuitable = true;
        tvSuitable.setTextColor(ContextCompat.getColor(this, R.color.pink_light));
        initSortByString();
        initSortDirection();
        sort();
    }

    private void sortTime(View view) {
        initSortBy();
        isTime = true;
        tvTime.setTextColor(ContextCompat.getColor(this, R.color.pink_light));
        String oldSort = sortDirectionTime;
        initSortByString();
        initSortDirection();
        if (oldSort.equals(SORT_DESCENDING)) {
            imgDirectionTime.setImageResource(R.drawable.ic_up);
            sortDirectionTime = SORT_ASCENDING;
        } else {
            imgDirectionTime.setImageResource(R.drawable.ic_down_active);
            sortDirectionTime = SORT_DESCENDING;
        }
        sort();
    }


    private void sortHealthiness(View view) {
        initSortBy();
        isHealthiness = true;
        tvHealthiness.setTextColor(ContextCompat.getColor(this, R.color.pink_light));
        String oldSort = sortDirectionHealthiness;
        initSortByString();
        initSortDirection();
        if (oldSort.equals(SORT_DESCENDING)) {
            imgDirectionHealthiness.setImageResource(R.drawable.ic_up);
            sortDirectionHealthiness = SORT_ASCENDING;
        } else {
            imgDirectionHealthiness.setImageResource(R.drawable.ic_down_active);
            sortDirectionHealthiness = SORT_DESCENDING;
        }
        sort();
    }

    private void sortPopularity(View view) {
        initSortBy();
        isPopularity = true;
        tvPopularity.setTextColor(ContextCompat.getColor(this, R.color.pink_light));
        initSortByString();
        initSortDirection();
        sort();
    }

    private void sortCalories(View view) {
        initSortBy();
        isCalories = true;
        tvCalories.setTextColor(ContextCompat.getColor(this, R.color.pink_light));
        String oldSort = sortDirectionCalories;
        initSortByString();
        initSortDirection();
        if (oldSort.equals(SORT_DESCENDING)) {
            imgDirectionCalories.setImageResource(R.drawable.ic_up);
            sortDirectionCalories = SORT_ASCENDING;
        } else {
            imgDirectionCalories.setImageResource(R.drawable.ic_down_active);
            sortDirectionCalories = SORT_DESCENDING;
        }
        sort();
    }

    private void sort() {

        searchRecipe();
    }

    private void searchRecipe() {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toasty.info(this, "No internet connection.", Toast.LENGTH_SHORT, true).show();
            return;
        }
        loadingDialog.showDialog(null);
        String sortDirection = "desc";
        if (isHealthiness) {
            sortDirection = sortDirectionHealthiness;
        }
        if (isTime) {
            sortDirection = sortDirectionTime;
        }
        if (isCalories) {
            sortDirection = sortDirectionCalories;
        }
        ApiService.apiService.getSearchList(searchIngredients, 100, diet, sortBy, sortDirection,
                maxTime, minCalories, maxCalories,
                true, true, mealType).enqueue(new Callback<ApiFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiFoodResponse> call, @NonNull Response<ApiFoodResponse> response) {
                loadingDialog.hideDialog();
                ApiFoodResponse results = response.body();
                if (results != null) {
                    recipes = results.getResults();
                    List<Recipe> filterRecipe = new ArrayList<>();
                    for (Recipe recipe : recipes) {
                        if (recipe.getReadyInMinutes() < maxTime && recipe.getReadyInMinutes() > minTime) {
                            filterRecipe.add(recipe);
                        }
                    }
                    recipes.clear();
                    recipes.addAll(filterRecipe);
                    ListSearchRecipeAdapter adapter = new ListSearchRecipeAdapter(recipes, ListRecipeActivity.this);
                    rcvListRecipe.setLayoutManager(new GridLayoutManager(ListRecipeActivity.this, 2));
                    rcvListRecipe.setAdapter(adapter);
                    if (recipes.isEmpty()) {
                        layoutSearchEmpty.setVisibility(View.VISIBLE);
                    } else {
                        layoutSearchEmpty.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiFoodResponse> call, @NonNull Throwable t) {
                Toasty.info(ListRecipeActivity.this, R.string.error, Toast.LENGTH_SHORT, true).show();
                loadingDialog.hideDialog();
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}