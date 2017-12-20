package com.example.s0282656.futureappointmentui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class ProviderView extends LinearLayout {


    public ProviderView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.provider_view, this);
        setupFonts(context);
//        init(null, 0);
    }

    public ProviderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.provider_view, this);
        setupFonts(context);
//        init(attrs, 0);
    }

    public ProviderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.provider_view, this);
        setupFonts(context);
//        init(attrs, defStyle);
    }

    private void setupFonts(Context context) {
        final Typeface tf1 = SHCCustomFont.fromString("sanspro_semibold", context);
        final Typeface tf2 = SHCCustomFont.fromString("sanspro_regular", context);

        TextView providerTV = findViewById(R.id.provider_textview);
        providerTV.setTypeface(tf1);

        TextView departmentTV = findViewById(R.id.department_textview);
        TextView streetTV = findViewById(R.id.street_textview);
        TextView suiteTV = findViewById(R.id.suite_textview);
        TextView cityTV = findViewById(R.id.city_textview);
        TextView phoneTV = findViewById(R.id.phone_textview);
        TextView arrivalTV = findViewById(R.id.autoarrival_textview);
        departmentTV.setTypeface(tf2);
        streetTV.setTypeface(tf2);
        suiteTV.setTypeface(tf2);
        cityTV.setTypeface(tf2);
        phoneTV.setTypeface(tf2);
        arrivalTV.setTypeface(tf2);
    }

//    private void init(AttributeSet attrs, int defStyle) {
//        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(
//                attrs, R.styleable.ProviderView, defStyle, 0);
//
//        mExampleString = a.getString(
//                R.styleable.ProviderView_exampleString);
//        mExampleColor = a.getColor(
//                R.styleable.ProviderView_exampleColor,
//                mExampleColor);
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.ProviderView_exampleDimension,
//                mExampleDimension);
//
//        if (a.hasValue(R.styleable.ProviderView_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.ProviderView_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }
//
//        a.recycle();
//
//        // Set up a default TextPaint object
//        mTextPaint = new TextPaint();
//        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
//    }


}
