package com.example.s0282656.futureappointmentui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class FutureVisitActivity extends AppCompatActivity  implements TrackerView.OnStepButtonTouchedListener{

    ArrayList<WorkflowStepDescriptor> descriptors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_visit);

        descriptors = new ArrayList<WorkflowStepDescriptor>();
        setupWorkflowTracker();
        Button b = findViewById(R.id.start_step_button);
        b.setText("BEGIN eCHECK-IN");

    }

    private void setupWorkflowTracker(){
        WorkflowStepDescriptor step1 = new WorkflowStepDescriptor(0, R.drawable.ic_conf, false);
        descriptors.add(step1);

        WorkflowStepDescriptor step2 = new WorkflowStepDescriptor(1, R.drawable.ic_e_chk, false);
        descriptors.add(step2);

        WorkflowStepDescriptor step3 = new WorkflowStepDescriptor(2, R.drawable.ic_arv, false);
        descriptors.add(step3);

        TrackerView trackerView = (TrackerView)findViewById(R.id.workflow_steps_view);
        trackerView.setDescriptors(this.descriptors, this);

    }

    public void onButtonTouched(int index, View anchor ){

        // TODO: save step state in the descripor
        WorkflowStepDescriptor descr = descriptors.get(index);
        descr.status_completed = true;
//        FutureVisitActivity.this.showMessageDialog(index, anchor);
    }
}
