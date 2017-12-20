package com.example.s0282656.futureappointmentui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class TrackerView extends LinearLayout implements View.OnClickListener {
    ArrayList<WorkflowStepDescriptor> myDescriptors;
    ArrayList<View> dividers = new ArrayList<>();


    int [] viewCoords = new int[2];
    private OnStepButtonTouchedListener mListener;


    public TrackerView(Context context) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public TrackerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public TrackerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setDescriptors(ArrayList<WorkflowStepDescriptor> descriptors, OnStepButtonTouchedListener listener){
        if(!descriptors.isEmpty()) {
            this.myDescriptors = descriptors;
            this.mListener = listener;

            renderWorkflowSteps();
        }
    }

    private void renderWorkflowSteps(){

        int stepsCount = myDescriptors.size();

        for(WorkflowStepDescriptor descriptor : myDescriptors){

            View v = LayoutInflater.from(getContext()).inflate(R.layout.workflow_tracker_step, null);
            ImageButton imageButton = v.findViewById(R.id.step_button);
            imageButton.setImageResource(descriptor.iconId);
            if(descriptor.status_completed){
                imageButton.setBackgroundResource(R.drawable.round_button_shape_completed);
            }

            imageButton.setEnabled(true);
            imageButton.setOnClickListener(this);
            imageButton.setTag(descriptor.stepIndex);
            addView(v);


            if(stepsCount > 1 && descriptor.stepIndex < (stepsCount-1)) {
                View dummy = new View(getContext());
                LayoutParams dummyparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10);
                dummyparams.topMargin = 55;
//                dummyparams.gravity = Gravity.CENTER_VERTICAL;
                dummyparams.weight = 1;
                dummy.setLayoutParams(dummyparams);
                if(descriptor.status_completed){
                    dummy.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
                    dummy.setBackgroundColor(getResources().getColor(R.color.color_divider));
                }
                dividers.add(descriptor.stepIndex, dummy);
                addView(dummy);
            }

        }
    }

    @Override
    public void onClick(View v) {
        ImageButton imageButton = (ImageButton)v;

        // cannot do this since this will change solid color for ALL buttons that have the same drawable background, not just this one
//        GradientDrawable background = (GradientDrawable) imageButton.getBackground();
//        background.setStroke(10, getResources().getColor(R.color.colorAccent));

//        Drawable dr = getResources().getDrawable(R.drawable.round_button_shape_completed);
        imageButton.setBackgroundResource(R.drawable.round_button_shape_completed);

        int tag = (int)imageButton.getTag();
        if(tag < dividers.size()) {
            View divider = dividers.get((int) imageButton.getTag());
            divider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

        v.getLocationInWindow(viewCoords);
        Log.d("loc.x", Integer.toString(viewCoords[0]));
        Log.d("loc.y", Integer.toString(viewCoords[1]));

        if(this.mListener != null){
            mListener.onButtonTouched(tag, v);
        }
    }

    public interface OnStepButtonTouchedListener {

        void onButtonTouched(int index, View anchor );
    }



}
