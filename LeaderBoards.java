package com.example.fitpeak;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class LeaderBoards extends AppCompatActivity
{
    public Vector<Integer> values = new Vector<Integer>();
    private RelativeLayout readProgressLayout;
    private ProgressBar readProgressBar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ReadRecyclerAdapter recyclerAdapter;
    private ArrayList<Book> fitList = new ArrayList<>();

    TextView first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);
        sixth = findViewById(R.id.sixth);
        seventh = findViewById(R.id.seventh);
        eighth = findViewById(R.id.eighth);
        ninth = findViewById(R.id.ninth);
        tenth = findViewById(R.id.tenth);

        layoutManager= new LinearLayoutManager(this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://script.google.com/macros/s/AKfycbyXe3cAdQ6tmIEvH9MKRxGyY-yuuWzCMYsKkZyK9qLb_0_STbWaJOqqbZx5oa9E1p6q/exec",
                null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONArray data = response.getJSONArray("fitList");
                            for(int i=0; i<data.length(); i++)
                            {
                                JSONObject bookJsonObject = data.getJSONObject(i);
                                values.add(bookJsonObject.getInt("activityData"));
                                Book bookObject = new Book(
                                        bookJsonObject.getString("personName"),
                                        bookJsonObject.getString("personActivity"),
                                        bookJsonObject.getInt("activityData")
                                );

                                fitList.add(bookObject);
                            }

                            Collections.sort(values, Collections.reverseOrder());
                            if (values.size() >= 1) {
                                first.setText("First Place " + values.get(0));
                            }

                            if (values.size() >= 2) {
                                second.setText("Second Place " + values.get(1));
                            }

                            if (values.size() >= 3) {
                                third.setText("Third Place " + values.get(2));
                            }

                            if (values.size() >= 4) {
                                fourth.setText("Fourth Place " + values.get(3));
                            }

                            if (values.size() >= 5) {
                                fifth.setText("Fifth Place " + values.get(4));
                            }

                            if (values.size() >= 6) {
                                sixth.setText("Sixth Place " + values.get(5));
                            }

                            if (values.size() >= 7) {
                                seventh.setText("Seventh Place " + values.get(6));
                            }

                            if (values.size() >= 8) {
                                eighth.setText("Eighth Place " + values.get(7));
                            }

                            if (values.size() >= 9) {
                                ninth.setText("Ninth Place " + values.get(8));
                            }

                            if (values.size() >= 10) {
                                tenth.setText("Tenth Place " + values.get(9));
                            }



                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(LeaderBoards.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error)
                    {
                        readProgressLayout.setVisibility(View.GONE);
                        readProgressBar.setVisibility(View.GONE);
                        Toast.makeText(LeaderBoards.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }
}