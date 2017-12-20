package com.example.s0282656.futureappointmentui;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by s0282656 on 12/20/17.
 */

public class WorkflowStepStatus {
    private static final int STATUS_NOT_STARTED = 0;
    private static final int STATUS_IN_PROGRESS = 1;
    private static final int STATUS_COMPLETED = 2;

    public int getStatus() {
        return mStatus;
    }

    int mStatus;

    public WorkflowStepStatus(@StatusDef int status){
        mStatus = status;
    }
    public void setStatus(@StatusDef int status){
        mStatus = status;
    }

    @IntDef({STATUS_NOT_STARTED, STATUS_IN_PROGRESS , STATUS_COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusDef {}
}
