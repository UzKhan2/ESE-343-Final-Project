package com.example.fitpeak;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager sensorManager;
    private boolean running = false;
    private TextView stepsTV;
    private FloatingActionButton fab;
    float steps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepsTV = findViewById(R.id.idTVSteps);
        fab = findViewById(R.id.idFAB);
        stepsTV.setText(String.valueOf(steps));

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(running)
                {
                    running = false;
                    fab.setImageResource(R.drawable.ic_play);
                    Toast.makeText(StepCounterActivity.this, "Counter Paused..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    running = true;
                    fab.setImageResource(R.drawable.ic_pause);
                    Toast.makeText(StepCounterActivity.this, "Counter Started..", Toast.LENGTH_SHORT).show();
                    startCounting();
                }
            }
        });
    }

    private void startCounting()
    {
        running = true;
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(sensor!=null)
        {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

        }
        else
        {
            Toast.makeText(this, "Sensor not found..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if(running)
        {
            steps = steps + sensorEvent.values[0];
            stepsTV.setText(String.valueOf(steps));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){}
}