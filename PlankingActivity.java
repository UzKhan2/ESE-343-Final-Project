package com.example.pushupandplankingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class PlankingActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor prox;
    int onFloor;
    boolean firstTime;
    boolean firstTry;

    // Number of seconds displayed on the stopwatch.
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planking);
        onFloor = 0;
        firstTime=true;
        firstTry = true;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        prox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (savedInstanceState != null)
        {
            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, prox, SensorManager.SENSOR_DELAY_NORMAL);
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
        wasRunning = running;
        running = false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float count = event.values[0];
        if(count < 2 && onFloor == 0 && firstTry)
        {
            onFloor = 1;
            running = false;
            firstTry = false;
        }
        else if(count < 2 && onFloor == 0)
        {
            onFloor = 1;
            running = false;
            firstTime = false;
        }
        else if(count > 9 && onFloor == 1)
        {
            onFloor = 0;
            //start timer
            if(firstTime == true)
            {
                running = true;
                runTimer();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    private void runTimer()
    {
        // Get the text view.
        final TextView timeView = findViewById(R.id.timeDisplay);
        // Creates a new Handler
        final Handler handler = new Handler();


        handler.post(new Runnable()
        {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes, and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running)
                {
                    seconds++;
                }
                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void saveResult(View view)
    {
        //Save seconds to database (Uzair handling this)
    }

    public void returnToMain(View view)
    {
        seconds = 0;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
