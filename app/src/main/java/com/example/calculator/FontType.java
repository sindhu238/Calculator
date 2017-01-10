package com.example.calculator;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by venkateswara on 09/01/17.
 */

public class FontType {
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

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
