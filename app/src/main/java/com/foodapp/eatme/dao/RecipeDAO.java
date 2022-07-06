package com.foodapp.eatme.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.foodapp.eatme.model.extend.RecipeExtend;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecipeExtend recipe);

    @Update
    void update(RecipeExtend recipe);

    @Query("DELETE FROM recipe WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM recipe WHERE id = :id")
    RecipeExtend getItemById(Long id);

    @Query("SELECT * FROM recipe")
    List<RecipeExtend> getAllRecipe();
}
