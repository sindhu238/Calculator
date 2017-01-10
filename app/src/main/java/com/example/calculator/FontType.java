package com.example.calculator;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * FontType Class is used for getting the required custom font
 *
 * @author Sindhu
 * @since 9-1-2017
 */

public class FontType {
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    /*
    Gets the custom font required from assests folder
     */
    public static Typeface getType(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
