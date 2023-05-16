package com.example.fitpeak;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.view.View;

        import android.os.Bundle;

public class AttendanceMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addAddress(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
    }

    public void checkAttendance(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AttendanceActivity.class);
        startActivity(intent);
    }
}

