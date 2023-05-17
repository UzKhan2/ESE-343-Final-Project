package com.example.fitpeak;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button btnSend;
    private EditText inputName;
    private static String enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        inputName = findViewById(R.id.inputName);

        btnSend.setOnClickListener(view ->
        {
            if (inputName.getText().toString().isEmpty())
            {
                Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
            } else
            {
                enteredName = inputName.getText().toString();
                Intent intent = new Intent(MainActivity.this, SendOff.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
            }
        });
    }
    public static String getEnteredName() {return enteredName;}
}


