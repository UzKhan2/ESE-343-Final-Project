package com.example.pushupandplankingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button1 = this.findViewById(R.id.PUButton);
//        button1.setOnClickListener(
//                new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View view){dispatchTakePictureIntent();}
//                }
//        );
    }

    public void PushUps(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, PushUpActivity.class);
        startActivity(intent);
    }

    public void Planking(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, PlankingActivity.class);
        startActivity(intent);
    }
}
