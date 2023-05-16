package com.example.fitpeak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class AddressActivity extends AppCompatActivity
{
    EditText enteredAddress;
    String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        enteredAddress = findViewById(R.id.editTextAddress);
        userInput = String.valueOf(enteredAddress.getText());
    }

    public void saveAddress()
    {
        String url = "https://script.google.com/macros/s/AKfycbyyZSDn9KZo0n8BHpdbA2Cl-zNQIZDrKWBb0OBFY8jOPxsfzH6WWVu7FZGmibpr_JqQ/exec";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(AddressActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(com.android.volley.VolleyError error)
            {
                Toast.makeText(AddressActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("personActivity", userInput);
                return params;
            }
        };
        Volley.newRequestQueue(AddressActivity.this).add(stringRequest);
    }

    public void returnToMain(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}