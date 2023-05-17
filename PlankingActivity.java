package com.example.fitpeak;

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
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlankingActivity extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor light;
    int onFloor;
    int test = 0;
    float luxVal = 0;
    boolean firstTime;
    boolean firstTry;

    // Number of seconds displayed on the stopwatch.
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    private boolean wasRunning;
    String enteredName = MainActivity.getEnteredName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planking);
        onFloor = 0;
        firstTime=true;
        firstTry = true;

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (savedInstanceState != null)
        {

            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState.getInt("seconds");
            running
                    = savedInstanceState.getBoolean("running");
            wasRunning
                    = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        if (wasRunning)
        {
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
        if (test < 1)
        {
            luxVal =event.values[0];
            test++;
        }
        if(count < luxVal && onFloor == 0 && firstTry)
        {
            onFloor = 1;
            running = false;
            firstTry = false;
        }
        else if(count < luxVal && onFloor == 0)
        {
            onFloor = 1;
            running = false;
            firstTime = false;
        }
        else if(count > luxVal && onFloor == 1)
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
    public void onAccuracyChanged(Sensor sensor, int i){}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
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
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

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
        String url = "https://script.google.com/macros/s/AKfycbw_a6P-kbgLk0gTG-9in5RL1ZUaOnQ9Wj3bye6zod5-BOAqvKkVDMk-Co0P3cvITNUe/exec";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(PlankingActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(com.android.volley.VolleyError error)
            {
                Toast.makeText(PlankingActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("personName", enteredName);
                params.put("personActivity", "Planks");
                params.put("activityData", String.valueOf(seconds));
                return params;
            }
        };
        Volley.newRequestQueue(PlankingActivity.this).add(stringRequest);
    }

    public void leaderBoards(View view)
    {
        Intent intent = new Intent(this, LeaderBoards.class);
        startActivity(intent);
    }

    public void returnToMain(View view)
    {
        seconds = 0;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}