package com.example.s0282656.futureappointmentui;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by s0282656 on 12/20/17.
 */

public class VisitType {

    private static final int NEW_PATIENT_VISIT = 0;
    private static final int PHONE_VISIT = 1;
    private static final int VIDEO_VISIT = 2;

    private int mType;

    public VisitType(@VisitTypeDef int visitType){
        mType = visitType;
    }
    public int getType() {
        return mType;
    }

    @IntDef({NEW_PATIENT_VISIT, PHONE_VISIT , VIDEO_VISIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VisitTypeDef {}
}
