package com.example.fitpeak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SendOff extends AppCompatActivity
{
    private Button pushUpsBtn;
    private Button heartBtn;
    private Button recipeBtn;
    private Button plankBtn;
    private Button runningBtn;
    private Button bmiBtn;
    //private ImageButton calenderBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_off);

        pushUpsBtn = findViewById(R.id.pushUpsBtn);
        heartBtn = findViewById(R.id.heartBtn);
        recipeBtn = findViewById(R.id.recipeBtn);
        plankBtn = findViewById(R.id.plankBtn);
        runningBtn = findViewById(R.id.runningBtn);
        bmiBtn = findViewById(R.id.bmiBtn);
        //calenderBtn = findViewById(R.id.calenderBtn);


        pushUpsBtn.setOnClickListener(view ->
        {
                Intent intent = new Intent(SendOff.this, PushUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        });

        runningBtn.setOnClickListener(view ->
        {
            Intent intent = new Intent(SendOff.this, Running.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        });

        recipeBtn.setOnClickListener(view ->
        {
            Intent intent = new Intent(SendOff.this, fitFood.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        });



        plankBtn.setOnClickListener(view ->
        {
            Intent intent = new Intent(SendOff.this, PlankingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        });

        bmiBtn.setOnClickListener(view ->
        {
            Intent intent = new Intent(SendOff.this, BMI.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        });

        heartBtn.setOnClickListener(view ->
        {
            Intent intent = new Intent(SendOff.this, heartrate.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        });

//        calenderBtn.setOnClickListener(view ->
//        {
//            Intent intent = new Intent(SendOff.this, AttendanceMain.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//        });
    }
}