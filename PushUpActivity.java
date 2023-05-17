package com.example.fitpeak;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitpeak.MainActivity;

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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class PushUpActivity extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor light;
    int onFloor;

    int test = 0;
    float luxVal = 0;
    int pushUpCounter;
    TextView pushView;
    String enteredName = MainActivity.getEnteredName();

    @Override
    protected void onCreate(Bundle savedInstanceState)// sensor is in middle
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        pushView = findViewById(R.id.countDisplay);
        onFloor = 0;
        pushUpCounter = 0;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
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
        if (test < 1)
        {
            luxVal = event.values[0];
            test++;
        }
        if(count < 30 && onFloor == 0) // Its dark and you're above
        {
            onFloor = 1;
        }
        else if (count > 30 && onFloor == 1)
        {
            onFloor = 0;
            pushUpCounter++;
            pushView.setText(String.valueOf(pushUpCounter));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void saveResult(View view)
    {
            String url = "https://script.google.com/macros/s/AKfycbyXe3cAdQ6tmIEvH9MKRxGyY-yuuWzCMYsKkZyK9qLb_0_STbWaJOqqbZx5oa9E1p6q/exec";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    Toast.makeText(PushUpActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(com.android.volley.VolleyError error)
                {
                    Toast.makeText(PushUpActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<>();
                    params.put("personName", enteredName);
                    params.put("personActivity", "Push-Ups");
                    params.put("activityData", String.valueOf(pushUpCounter));
                    return params;
                }
            };
            Volley.newRequestQueue(PushUpActivity.this).add(stringRequest);
    }

    public void leaderBoards(View view)
    {
        Intent intent = new Intent(this, LeaderBoards.class);
        startActivity(intent);
    }

    public void returnToMain(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
