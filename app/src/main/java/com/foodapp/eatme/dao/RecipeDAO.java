package com.foodapp.eatme.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.foodapp.eatme.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Query("DELETE FROM recipe where id = :id")
    void delete(int id);

    @Query("SELECT * FROM recipe WHERE id = :id")
    public Recipe getItemById(Long id);

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipe();
}
