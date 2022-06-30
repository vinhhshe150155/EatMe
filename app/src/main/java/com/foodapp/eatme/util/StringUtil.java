package com.foodapp.eatme.util;

public class StringUtil {
    public static final String RAPID_API_KEY = "X-RapidAPI-Key: 615484898amsh2a40c4a2d671eb4p1b0769jsn1ee636a8de0b";
    public static final String SPOONACULAR_API_KEY = "06db6a70a29742b8b187b820d47fb805";
    public static final String RAPID_API_HOST = "X-RapidAPI-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
    public static final String GSO_KEY = "164966780531-nd5bgk2sfjde7s6u1f8qjvi4ut0pkj6h.apps.googleusercontent.com";

    public static String toCaptalizedString(String message) {
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        return message;
    }
}
