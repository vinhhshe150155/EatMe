package com.foodapp.eatme.dao;

import androidx.room.TypeConverter;

import com.foodapp.eatme.model.AnalyzedInstruction;
import com.foodapp.eatme.model.Nutrition;
import com.foodapp.eatme.model.extend.ExtendedIngredient;
import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.WinePairing;
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
    public String toObjectNutrition(NutriExtend nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<NutriExtend>() {
        }.getType();
        return gson.toJson(nutrition, type);
    }

    @TypeConverter
    public NutriExtend fromObjectNutrition(String nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<NutriExtend>() {
        }.getType();
        return gson.fromJson(nutrition, type);
    }

    @TypeConverter
    public String fromObjectExtendedIngredient(List<ExtendedIngredient> ingredients) {
        if (ingredients == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ExtendedIngredient>>() {
        }.getType();
        return gson.toJson(ingredients, type);
    }

    @TypeConverter
    public List<ExtendedIngredient> toObjectExtendedIngredient(String ingredient) {
        if (ingredient == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ExtendedIngredient>>() {
        }.getType();
        return gson.fromJson(ingredient, type);
    }

    @TypeConverter
    public String toObjectWineParing(WinePairing winePairing) {
        if (winePairing == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<WinePairing>() {
        }.getType();
        return gson.toJson(winePairing, type);
    }

    @TypeConverter
    public WinePairing fromObjectEWineParing(String winePairing) {
        if (winePairing == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<WinePairing>() {
        }.getType();
        return gson.fromJson(winePairing, type);
    }

    @TypeConverter
    public String toObjectNutrion(Nutrition nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Nutrition>() {
        }.getType();
        return gson.toJson(nutrition, type);
    }

    @TypeConverter
    public Nutrition fromObjectNutrion(String nutrition) {
        if (nutrition == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Nutrition>() {
        }.getType();
        return gson.fromJson(nutrition, type);
    }
}
