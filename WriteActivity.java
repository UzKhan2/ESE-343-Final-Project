package com.example.fitpeak;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.RatingBar;
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

public class WriteActivity extends AppCompatActivity {

    private RelativeLayout writeProgressLayout;
    private ProgressBar writeProgressBar;
    private EditText edtBookName, edtBookAuthor, edtBookPrice;
    private RatingBar ratingBar;
    private Button btnSaveToDrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        writeProgressLayout = findViewById(R.id.writeProgressLayout);
        writeProgressBar = findViewById(R.id.writeProgressBar);
        edtBookName = findViewById(R.id.edtBookName);
        edtBookAuthor = findViewById(R.id.edtBookAuthor);
        edtBookPrice = findViewById(R.id.edtBookPrice);
        //ratingBar = findViewById(R.id.ratingBar);
        btnSaveToDrive = findViewById(R.id.btnSaveToDrive);

        writeProgressLayout.setVisibility(View.GONE);
        writeProgressBar.setVisibility(View.GONE);

        btnSaveToDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtBookName.getText().toString().isEmpty() || edtBookAuthor.getText().toString().isEmpty() ||
                        edtBookPrice.getText().toString().isEmpty() /*|| ratingBar.getRating() == 0.0f*/) {
                    Toast.makeText(WriteActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                } else {
                    writeProgressLayout.setVisibility(View.VISIBLE);
                    writeProgressBar.setVisibility(View.VISIBLE);

                    String url = "https://script.google.com/macros/s/AKfycbyXe3cAdQ6tmIEvH9MKRxGyY-yuuWzCMYsKkZyK9qLb_0_STbWaJOqqbZx5oa9E1p6q/exec";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(WriteActivity.this, response, Toast.LENGTH_SHORT).show();
                            writeProgressLayout.setVisibility(View.GONE);
                            writeProgressBar.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(com.android.volley.VolleyError error) {
                            Toast.makeText(WriteActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            writeProgressLayout.setVisibility(View.GONE);
                            writeProgressBar.setVisibility(View.GONE);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("bookName", edtBookName.getText().toString());
                            params.put("bookAuthor", edtBookAuthor.getText().toString());
                            params.put("bookPrice", edtBookPrice.getText().toString());
                            //params.put("bookRating", String.valueOf(ratingBar.getRating()));
                            return params;
                        }
                    };

                    Volley.newRequestQueue(WriteActivity.this).add(stringRequest);
                }
            }
        });
    }
}
