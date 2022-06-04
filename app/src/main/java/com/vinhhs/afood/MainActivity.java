package com.vinhhs.afood;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vinhhs.afood.adapter.SearchIngredientAdapter;
import com.vinhhs.afood.adapter.SuggestMealTypeAdapter;
import com.vinhhs.afood.foodinterface.IClickItemIngredient;
import com.vinhhs.afood.fragment.HomeFragment;
import com.vinhhs.afood.fragment.ListRecipeFragment;
import com.vinhhs.afood.model.ApiFoodResponse;
import com.vinhhs.afood.model.Ingredient;
import com.vinhhs.afood.model.MealType;
import com.vinhhs.afood.util.ListIngredient;
import com.vinhhs.afood.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_LIST_RECIPE = 1;
    private static final int FRAGMENT_SIGNOUT = 2;
    private int currentFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.app_name,  R.string.app_name);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.home).setChecked(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    private void initUI() {
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.list_saved){
            if(currentFragment != FRAGMENT_LIST_RECIPE){
                replaceFragment(new ListRecipeFragment());
                currentFragment = FRAGMENT_LIST_RECIPE;
            }
        }else if(id == R.id.signout){
            if(currentFragment != FRAGMENT_SIGNOUT){
                replaceFragment(new ListRecipeFragment());
                currentFragment = FRAGMENT_LIST_RECIPE;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_replace, fragment);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}