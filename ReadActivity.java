package com.example.fitpeak;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    private RelativeLayout readProgressLayout;
    private ProgressBar readProgressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ReadRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        readProgressLayout = findViewById(R.id.readProgressLayout);
        readProgressBar = findViewById(R.id.readProgressBar);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        ArrayList<Book> bookList = new ArrayList<>();

        String url = "https://script.google.com/macros/s/AKfycbyXe3cAdQ6tmIEvH9MKRxGyY-yuuWzCMYsKkZyK9qLb_0_STbWaJOqqbZx5oa9E1p6q/exec";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        readProgressLayout.setVisibility(View.GONE);
                        readProgressBar.setVisibility(View.GONE);
                        try {
                            JSONArray data = response.getJSONArray("bookList");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject bookJasonObject = data.getJSONObject(i);
                                Book bookObject = new Book(
                                        bookJasonObject.getString("itemName"),
                                        bookJasonObject.getString("itemAuthor"),
                                        bookJasonObject.getInt("itemPrice")
                                );
                                bookList.add(bookObject);
                            }
                            recyclerAdapter = new ReadRecyclerAdapter(ReadActivity.this, bookList);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerView.setLayoutManager(layoutManager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        readProgressLayout.setVisibility(View.GONE);
                        readProgressBar.setVisibility(View.GONE);
                        Toast.makeText(ReadActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public java.util.Map<String, String> getHeaders() {
                try {
                    return super.getHeaders();
                } catch (AuthFailureError e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
