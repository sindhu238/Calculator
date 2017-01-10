package com.example.calculator;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * CustomButtom class is used to have a custom font for buttons
 * @author Sindhu
 * @since 9-1-2017
 */


public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    /*
    Sets custom font to - Roboto-Light.ttf using @link FontType
     */
    private void applyCustomFont(Context context) {
        Typeface customFont = FontType.getType("Roboto-Light.ttf", context);
        setTypeface(customFont);
        setTextColor(ContextCompat.getColor(context, R.color.gray));
    }
}