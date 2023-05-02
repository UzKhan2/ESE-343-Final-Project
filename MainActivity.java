package com.example.fitpeak;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button btnRead;
    private Button btnWrite;
    private Button btnLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        btnLeader = findViewById(R.id.btnLeader);

        btnRead.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReadActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
            finish();
        });

        btnWrite.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WriteActivity.class);
            startActivity(intent);
        });

        btnLeader.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LeaderBoards.class);
            startActivity(intent);
        });
    }
}
