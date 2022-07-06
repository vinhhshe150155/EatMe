package com.foodapp.eatme.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.foodapp.eatme.model.extend.RecipeExtend;

@TypeConverters({DataConverter.class})
@Database(entities = {RecipeExtend.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase recipeDatabase;
    public static final String DATABASE_NAME = "recipeDatabase.db";

    public abstract RecipeDAO recipeDAO();

    public static RecipeDatabase getInstance(Context context) {
        if (recipeDatabase == null) {
            recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return recipeDatabase;
    }
}
