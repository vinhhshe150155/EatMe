package com.vinhhs.afood.util;

public class StringUtil {
    public static String toCaptalizedString(String message){
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for(int i = 0; i < charArray.length; i++) {
            if(Character.isLetter(charArray[i])) {
                if(foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }
            else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        return message;
    }
}
