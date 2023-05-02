package com.example.fitpeak;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity
{
    private RelativeLayout writeProgressLayout;
    private ProgressBar writeProgressBar;
    private EditText edtName, edtActivity, edtQuantity;
    private Button btnSaveToDrive;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        writeProgressLayout = findViewById(R.id.writeProgressLayout);
        writeProgressBar = findViewById(R.id.writeProgressBar);
        edtName = findViewById(R.id.edtName);
        edtActivity = findViewById(R.id.edtActivity);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnSaveToDrive = findViewById(R.id.btnSaveToDrive);

        writeProgressLayout.setVisibility(View.GONE);
        writeProgressBar.setVisibility(View.GONE);

        btnSaveToDrive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (edtName.getText().toString().isEmpty() || edtActivity.getText().toString().isEmpty() || edtQuantity.getText().toString().isEmpty())
                {
                    Toast.makeText(WriteActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                } else
                {
                    writeProgressLayout.setVisibility(View.VISIBLE);
                    writeProgressBar.setVisibility(View.VISIBLE);

                    String url = "https://script.google.com/macros/s/AKfycbyXe3cAdQ6tmIEvH9MKRxGyY-yuuWzCMYsKkZyK9qLb_0_STbWaJOqqbZx5oa9E1p6q/exec";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            Toast.makeText(WriteActivity.this, response, Toast.LENGTH_SHORT).show();
                            writeProgressLayout.setVisibility(View.GONE);
                            writeProgressBar.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(com.android.volley.VolleyError error)
                        {
                            Toast.makeText(WriteActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            writeProgressLayout.setVisibility(View.GONE);
                            writeProgressBar.setVisibility(View.GONE);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError 
                        {
                            Map<String, String> params = new HashMap<>();
                            params.put("personName", edtName.getText().toString());
                            params.put("personActivity", edtActivity.getText().toString());
                            params.put("activityData", edtQuantity.getText().toString());
                            return params;
                        }
                    };
                    Volley.newRequestQueue(WriteActivity.this).add(stringRequest);
                }
            }
        });
    }
}
