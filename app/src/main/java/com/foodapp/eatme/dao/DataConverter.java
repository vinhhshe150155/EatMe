package com.foodapp.eatme.dao;

import androidx.room.TypeConverter;

import com.foodapp.eatme.model.AnalyzedInstruction;
import com.foodapp.eatme.model.Nutrition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter
    public String fromRecipeList(List<String> cursines) {
        if (cursines == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.toJson(cursines, type);
    }

    @TypeConverter
    public List<String> toCountryLangList(String cursineString) {
        if (cursineString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(cursineString, type);
    }

    @TypeConverter
    public String fromAnalyzedInstructionList(List<AnalyzedInstruction> analyzedInstructions) {
        if (analyzedInstructions == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AnalyzedInstruction>>() {
        }.getType();
        return gson.toJson(analyzedInstructions, type);
    }

    @TypeConverter
    public List<AnalyzedInstruction> toAnalyzedInstructionList(String analyzedInstructionString) {
        if (analyzedInstructionString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AnalyzedInstruction>>() {
        }.getType();
        return gson.fromJson(analyzedInstructionString, type);
    }

    @TypeConverter
    public String fromObjectNutrtion(Nutrition nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Nutrition>() {
        }.getType();
        return gson.toJson(nutrition, type);
    }

    @TypeConverter
    public Nutrition toObjectNutrtion(String nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Nutrition>() {
        }.getType();
        return gson.fromJson(nutrition, type);
    }
}
