package com.example.fitpeak;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMI extends AppCompatActivity
{
    EditText weights;
    EditText heights;
    String height, weight, tierMessage;
    int row, col;
    int[][] bmi_scale = {
            {25,27,29,31,34,36,38,40,42,44,46,48,50,52,54,57,59,61,63,65,67,69},
            {24,26,28,30,32,33,36,38,40,43,45,47,49,51,53,55,57,59,61,63,65,67},
            {23,25,27,29,31,32,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65},
            {23,25,26,28,30,32,34,36,38,40,42,44,45,47,49,51,53,55,57,59,61,62},
            {22,24,25,27,29,31,33,35,37,38,40,42,44,46,48,49,51,53,55,57,59,60},
            {21,23,25,27,28,30,32,34,36,37,39,41,43,44,46,48,50,51,53,55,57,59},
            {21,22,24,26,28,29,31,33,34,36,38,40,41,43,45,46,48,50,52,53,55,57},
            {20,22,23,25,27,28,30,32,33,35,37,38,40,42,43,45,47,48,50,52,53,55},
            {19,21,23,24,26,27,29,31,32,34,36,37,39,40,42,44,45,47,49,50,52,53},
            {19,20,22,24,25,27,28,30,31,33,35,36,38,39,41,42,44,46,47,49,50,52},
            {18,20,21,23,24,26,27,29,30,32,34,35,37,38,40,41,43,44,46,47,49,50},
            {18,19,21,22,24,25,27,28,30,31,33,34,36,37,38,40,41,43,44,45,47,49},
            {17,19,20,22,23,24,26,27,29,30,32,33,35,36,37,39,40,42,43,45,46,47},
            {17,18,20,21,22,24,25,27,28,29,31,32,34,35,36,38,39,41,42,43,45,45},
            {16,18,19,20,22,23,24,26,27,29,30,31,33,34,35,37,38,39,41,43,43,45},
            {16,17,19,20,21,22,24,25,26,28,29,30,32,33,34,36,37,38,40,41,42,44},
            {15,17,18,19,21,22,23,24,26,27,28,30,31,32,33,35,36,37,39,40,41,42},
            {15,16,18,19,20,21,23,24,25,26,28,29,30,31,33,34,35,36,38,39,41,41},
            {15,16,17,18,20,21,22,23,24,26,27,28,29,30,32,33,34,35,37,38,39,40},
            {14,15,17,18,19,20,21,23,24,25,26,27,29,30,31,32,33,34,36,37,38,39}};
    TextView bmiScore, scoreTier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        weights = findViewById(R.id.weight);
        heights = findViewById(R.id.height);
        bmiScore = findViewById(R.id.bmi_score);
        scoreTier = findViewById(R.id.tier);
    }

    public void bmiCalc(View view) {
        height = String.valueOf(heights.getText());
        weight = String.valueOf(weights.getText());
        switch(height){
            case "4_10":
                row = 0;
                break;
            case "4_11":
                row = 1;
                break;
            case "5_0":
                row = 2;
                break;
            case "5_1":
                row = 3;
                break;
            case "5_2":
                row = 4;
                break;
            case "5_3":
                row = 5;
                break;
            case "5_4":
                row = 6;
                break;
            case "5_5":
                row = 7;
                break;
            case "5_6":
                row = 8;
                break;
            case "5_7":
                row = 9;
                break;
            case "5_8":
                row = 10;
                break;
            case "5_9":
                row = 11;
                break;
            case "5_10":
                row = 12;
                break;
            case "5_11":
                row = 13;
                break;
            case "6_0":
                row = 14;
                break;
            case "6_1":
                row = 15;
                break;
            case "6_2":
                row = 16;
                break;
            case "6_3":
                row = 17;
                break;
            case "6_4":
                row = 18;
                break;
            case "6_5":
                row = 19;
                break;
            default:
                //impossible
        }
        int w = Integer.parseInt(weight);
        w /= 10;
        switch(w){
            case 12:
                col = 0;
                break;
            case 13:
                col = 1;
                break;
            case 14:
                col = 2;
                break;
            case 15:
                col = 3;
                break;
            case 16:
                col = 4;
                break;
            case 17:
                col = 5;
                break;
            case 18:
                col = 6;
                break;
            case 19:
                col = 7;
                break;
            case 20:
                col = 8;
                break;
            case 21:
                col = 9;
                break;
            case 22:
                col = 10;
                break;
            case 23:
                col = 11;
                break;
            case 24:
                col = 12;
                break;
            case 25:
                col = 13;
                break;
            case 26:
                col = 14;
                break;
            case 27:
                col = 15;
                break;
            case 28:
                col = 16;
                break;
            case 29:
                col = 17;
                break;
            case 30:
                col = 18;
                break;
            case 31:
                col = 19;
                break;
            case 32:
                col = 20;
                break;
            case 33:
                col = 21;
                break;
            default:
                //impossible
        }
        bmiScore.setText(""+bmi_scale[row][col]);
        if(bmi_scale[row][col] < 18.5){
            tierMessage = "You Are: Underweight";
        }
        else if(bmi_scale[row][col] >= 18.5 && bmi_scale[row][col] <= 24.9){
            tierMessage = "You Are: Healthy";
        }
        else if(bmi_scale[row][col] >= 25 && bmi_scale[row][col] <= 29.9){
            tierMessage = "You Are: Overweight";
        }
        else if(bmi_scale[row][col] >= 30 && bmi_scale[row][col] <= 39.9){
            tierMessage = "You Are: Obese";
        }
        else{
            tierMessage = "You Are: Morbidly Obese";
        }
        scoreTier.setText(tierMessage);
    }
}