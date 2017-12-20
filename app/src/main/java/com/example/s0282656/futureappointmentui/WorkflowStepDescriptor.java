package com.example.s0282656.futureappointmentui;

import java.io.Serializable;

/**
 * Created by s0282656 on 12/15/17.
 */

public class WorkflowStepDescriptor  implements Serializable {

    int iconId;
    int stepIndex;
    boolean status_completed;


    public WorkflowStepDescriptor(int index, int resource_id, boolean status){
        this.iconId = resource_id;
        this.stepIndex = index;
        this.status_completed = status;
    }
}
