package com.example.hashmap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity
{
    TextView textBox,first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth;
    EditText nombre;
    EditText value;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.textBox);
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
        nombre = findViewById(R.id.inputName);
        value = findViewById(R.id.inputValue);
    }
    HashMap<String, Integer> plank = new HashMap<>();

    public void saveResult (View view)
    {
        String input;
        String val;
        int v;
        input = nombre.getText().toString();
        val = value.getText().toString();
        v = convert(val);
        plank.put(input, v);
    }

    public static int convert(String str)
    {
        int val = 0;
        System.out.println("String = " + str);

        // Convert the String
        try {
            val = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {

            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
        return val;
    }

    @SuppressLint("SetTextI18n")
    public void findMax(View view)
    {

        plank.put("bob", 10);
        plank.put("bill", 20);
        plank.put("wdc", 50);
        plank.put("wjdek", 40);
        plank.put("uwhicu", 50);
        plank.put("whid", 60);
        plank.put("wid", 70);
        plank.put("bob", 80);
        plank.put("boke", 890);
        plank.put("woke", 90);
        plank.put("webjh", 200);

        ArrayList<Integer> values = new ArrayList<>(plank.values());
        Collections.sort(values, Collections.reverseOrder());

        for (int i = 0; i < 10 && i < values.size(); i++)
        {
            System.out.println(values.get(i));
        }
        first.setText("First Place" + (Integer.toString(values.get(0))));
        second.setText(Integer.toString(values.get(1)));
        third.setText(Integer.toString(values.get(2)));
        fourth.setText(Integer.toString(values.get(3)));
        fifth.setText(Integer.toString(values.get(4)));
        sixth.setText(Integer.toString(values.get(5)));
        seventh.setText(Integer.toString(values.get(6)));
        eighth.setText(Integer.toString(values.get(7)));
        ninth.setText(Integer.toString(values.get(8)));
        tenth.setText(Integer.toString(values.get(9)));
    }
}
