package com.foodapp.eatme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.ListRecipeActivity;
import com.foodapp.eatme.activity.MainActivity;
import com.foodapp.eatme.adapter.SearchIngredientAdapter;
import com.foodapp.eatme.adapter.SuggestMealTypeAdapter;
import com.foodapp.eatme.model.IngredientLocale;
import com.foodapp.eatme.model.MealType;
import com.foodapp.eatme.util.ListIngredient;
import com.foodapp.eatme.util.LocaleHelper;
import com.foodapp.eatme.util.StringUtil;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeFragment extends Fragment {
    private MaterialCardView cvFindRecipe;
    private LinearLayout layoutIngredientSuggest;
    private List<MealType> mealTypes;
    private List<IngredientLocale> searchIngredient;
    private List<IngredientLocale> allIngredientsLocale;
    private RecyclerView rcvMealType;
    private RecyclerView rcvIngredientSearch;
    private SearchView svIngredient;
    private SearchIngredientAdapter searchIngredientAdapter;
    private DrawerLayout drawerLayout;
    private ConstraintLayout layoutMain;
    private ImageView imgMenu;
    private ListIngredient listIngredient;
    private String currentLanguage = LocaleHelper.LANG_EN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        initData();
        bindingAction();
        return view;
    }

    private void initUI(View view) {
        if (getActivity() != null) {
            drawerLayout = getActivity().findViewById(R.id.drawerlayout);
        }
        imgMenu = view.findViewById(R.id.img_home_menu);
        cvFindRecipe = view.findViewById(R.id.card_view);
        layoutIngredientSuggest = view.findViewById(R.id.linear_ingredient_suggest);
        rcvMealType = view.findViewById(R.id.main_activity_rcv_mealtype);
        svIngredient = view.findViewById(R.id.main_sv_search_ingredients);
        rcvIngredientSearch = view.findViewById(R.id.main_activity_rcv_search_ingredient);
        layoutMain = view.findViewById(R.id.layout_main);
        svIngredient.clearFocus();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL);
        rcvIngredientSearch.addItemDecoration(itemDecoration);
        rcvIngredientSearch.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        searchIngredientAdapter = new SearchIngredientAdapter(ingredient -> {
            searchIngredient.add(ingredient);
            addIngredientView(ingredient);
            svIngredient.setQuery("", false);
            rcvIngredientSearch.setVisibility(View.GONE);
        });
        searchIngredientAdapter.setCurrentLanguage(currentLanguage);
        rcvIngredientSearch.setAdapter(searchIngredientAdapter);
    }

    private void initData() {
        initLanguage();
        initMealType();
        initAllIngredients();
        initRandomIngredients();
        initSuggestListIngredientLayout();
        initMealTypeRcv();
    }

    private void initLanguage() {
        currentLanguage = LocaleHelper.getCurrentLanguage();
    }

    private void initMealTypeRcv() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SuggestMealTypeAdapter mealTypeAdapter = new SuggestMealTypeAdapter(mealTypes, requireContext(), mealType -> {
            Intent intent = new Intent(getActivity(), ListRecipeActivity.class);
            intent.putExtra("mealType", mealType.getName());
            intent.putExtra("ingredients", getSearchIngredient());
            startActivity(intent);
        });
        rcvMealType.setAdapter(mealTypeAdapter);
        rcvMealType.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initSuggestListIngredientLayout() {
        for (IngredientLocale ingredient : searchIngredient) {
            addIngredientView(ingredient);
        }
    }

    private void addIngredientView(IngredientLocale ingredient) {
        View viewItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_ingredient_suggestion, layoutIngredientSuggest, false);
        TextView textView = viewItem.findViewById(R.id.tv_suggest_ingredient);
        ImageView imageView = viewItem.findViewById(R.id.img_remove_ingredient_suggestion);
        String ingredientName;
        if (LocaleHelper.LANG_KR.equals(currentLanguage)) {
            ingredientName = ingredient.getKrName();
        } else {
            ingredientName = ingredient.getEnName();
        }
        textView.setText(StringUtil.toCaptalizedString(ingredientName));
        imageView.setOnClickListener(view -> {
            layoutIngredientSuggest.removeView(viewItem);
            searchIngredient.remove(ingredient);
        });
        layoutIngredientSuggest.addView(viewItem);
    }

    private void initMealType() {
        mealTypes = new ArrayList<>();
        mealTypes.add(new MealType("main course", R.drawable.main_course, R.string.main_course));
        mealTypes.add(new MealType("soup", R.drawable.soup, R.string.soup));
        mealTypes.add(new MealType("snack", R.drawable.snack, R.string.snack));
        mealTypes.add(new MealType("appetizer", R.drawable.appetizer, R.string.appetizer));
        mealTypes.add(new MealType("dessert", R.drawable.dessert, R.string.dessert));
        mealTypes.add(new MealType("salad", R.drawable.salad, R.string.salad));
        mealTypes.add(new MealType("drink", R.drawable.drink, R.string.drink));
        mealTypes.add(new MealType("bread", R.drawable.bread, R.string.bread));
        mealTypes.add(new MealType("breakfast", R.drawable.breakfast, R.string.breakfast));
        mealTypes.add(new MealType("side dish", R.drawable.side_dish, R.string.side_dish));
        mealTypes.add(new MealType("sauce", R.drawable.sauce, R.string.sauce));
        mealTypes.add(new MealType("fingerfood", R.drawable.fingerfood, R.string.fingerfood));
    }

    private void closeKeyboard() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.closeKeyboard();
        }
    }

    private void initRandomIngredients() {
        searchIngredient = listIngredient.getRandomMainIngredients();
    }

    private void initAllIngredients() {
        listIngredient = ListIngredient.getInstance();
        listIngredient.setContext(requireContext());
        allIngredientsLocale = listIngredient.getAllIngredient();
    }

    private String getSearchIngredient() {
        StringBuilder sb = new StringBuilder();
        for (IngredientLocale ingredient : searchIngredient) {
            sb.append(ingredient.getEnName()).append(",");
        }
        String str = sb.toString();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void bindingAction() {
        layoutMain.setOnClickListener(view -> closeKeyboard());
        cvFindRecipe.setOnClickListener(this::onCvFindRecipeClicked);
        svIngredient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rcvIngredientSearch.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().equals("")) {
                    rcvIngredientSearch.setVisibility(View.GONE);
                } else {
                    filterList(newText);
                }
                return false;
            }
        });
        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void onCvFindRecipeClicked(View view) {
        Intent intent = new Intent(requireContext(), ListRecipeActivity.class);
        intent.putExtra("ingredients", getSearchIngredient());
        startActivity(intent);
    }

    private void filterList(String newText) {
        List<IngredientLocale> filterIngredientList = new ArrayList<>();
        for (IngredientLocale ingredient : allIngredientsLocale) {
            if (LocaleHelper.LANG_KR.equals(currentLanguage)) {
                if (ingredient.getKrName().toLowerCase().contains(newText.toLowerCase())) {
                    filterIngredientList.add(ingredient);
                }
            } else {
                if (ingredient.getEnName().toLowerCase().contains(newText.toLowerCase())) {
                    filterIngredientList.add(ingredient);
                }
            }
        }
        if (filterIngredientList.isEmpty()) {
            rcvIngredientSearch.setVisibility(View.GONE);
        } else {
            rcvIngredientSearch.setVisibility(View.VISIBLE);
            searchIngredientAdapter.setCurrentLanguage(currentLanguage);
            Collections.sort(filterIngredientList, (i1, i2) -> {
                if (currentLanguage.equals(LocaleHelper.LANG_EN)) {
                    return i1.getEnName().length() - i2.getEnName().length();
                } else {
                    return i1.getKrName().length() - i2.getKrName().length();
                }
            });
            searchIngredientAdapter.setIngredientList(filterIngredientList);
        }
    }
}