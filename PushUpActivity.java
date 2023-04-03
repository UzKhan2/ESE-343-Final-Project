package com.example.pushupandplankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PushUpActivity extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor prox;
    int onFloor;
    int pushUpCounter;
    TextView pushView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        pushView = findViewById(R.id.countDisplay);
        onFloor = 0;
        pushUpCounter = 0;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        prox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, prox, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float count = event.values[0];
        TextView textView = findViewById(R.id.countDisplay);
        if(count < 5 && onFloor == 0)
        {
            onFloor = 1;
        }
        else if(count > 9.5 && onFloor == 1)
        {
            onFloor = 0;
            pushUpCounter++;
            pushView.setText(""+pushUpCounter+"");
        }
        else{
            //do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public void saveResult(View view)
    {
        //Save pushUpCounter to database (Uzair handling this)
    }

    public void returnToMain(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
