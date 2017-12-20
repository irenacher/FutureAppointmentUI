package com.example.s0282656.futureappointmentui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button v1 = findViewById(R.id.personal_recurring_visit_button);
        Button v2 = findViewById(R.id.phone_visit_button);
        Button v3 = findViewById(R.id.new_patient_visit_button);
        Button v4 = findViewById(R.id.video_visit_button);
        v1.setTag(1);
        v2.setTag(2);
        v3.setTag(3);
        v4.setTag(4);
    }

    public void buttonClicked(View v){
        int tag = (int)v.getTag();

        switch(tag){
            case 1:
                break;
            case 2:
//                Intent intent1 = new Intent(this, NewPatientVisitActivity.class);
//                startActivity(intent1);
                break;
            case 3:
                Intent intent = new Intent(this, NewPatientVisitActivity.class);
                startActivity(intent);
                break;
            case 4:
                break;
        }
    }
}
