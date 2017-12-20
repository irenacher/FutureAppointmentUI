package com.example.s0282656.futureappointmentui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class DescriptionView extends LinearLayout {
    private String mVisitType;
    private String mVisitDate;
    private String mVisitTime;
    private String mVisitDuration;
    private String mVisitBeginButtonTitle;
    int mTypeTextSize;
    int mTimeTextSize;
    int mRegularTextSize;
    int mButtonsTextColor;
    int mLabelsTextColor;

//    private float mExampleDimension = 0;
//    private Drawable mExampleDrawable;
//
//    private TextPaint mTextPaint;
//    private float mTextWidth;
//    private float mTextHeight;

    public DescriptionView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.description_view, this);
        setupFonts(context);
//        init(null, 0);
    }

    public DescriptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.description_view, this);
        setupFonts(context);
//        init(attrs, 0);
    }

    public DescriptionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.description_view, this);
        setupFonts(context);
    }

    private void setupFonts(Context context){
        final Typeface tf1 = SHCCustomFont.fromString("sanspro_semibold", context);
        final Typeface tf2 = SHCCustomFont.fromString("sanspro_regular", context);

        TextView typeTV = findViewById(R.id.type_textview);
        TextView timeTV = findViewById(R.id.time_textview);
        typeTV.setTypeface(tf1);
        timeTV.setTypeface(tf1);

        TextView dateTV = findViewById(R.id.date_textview);
        TextView durationTV = findViewById(R.id.duration_textview);
        dateTV.setTypeface(tf2);
        durationTV.setTypeface(tf2);

    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.AppointmentViews, defStyle, 0);

        mVisitType = a.getString(R.styleable.AppointmentViews_visitType);
        mVisitDate = a.getString(R.styleable.AppointmentViews_visitDate);
        mVisitTime = a.getString(R.styleable.AppointmentViews_visitTime);
        mVisitDuration = a.getString(R.styleable.AppointmentViews_visitDuration);
        mVisitBeginButtonTitle = a.getString(R.styleable.AppointmentViews_visitBeginButtonTitle);

        mButtonsTextColor = a.getColor(R.styleable.AppointmentViews_buttonsTextColor, getResources().getColor(android.R.color.holo_blue_dark));
        mLabelsTextColor = a.getColor(R.styleable.AppointmentViews_labelsTextColor, getResources().getColor(android.R.color.holo_blue_dark));

        mTypeTextSize = a.getDimensionPixelSize(R.styleable.AppointmentViews_visitTypeTextSize, 0);
        mTimeTextSize = a.getDimensionPixelSize(R.styleable.AppointmentViews_visitTimeTextSize, 0);
        mRegularTextSize = a.getDimensionPixelSize(R.styleable.AppointmentViews_regularTextSize, 0);
        mButtonsTextColor = a.getDimensionPixelSize(R.styleable.AppointmentViews_buttonsTextSize, 0);

    }
}
