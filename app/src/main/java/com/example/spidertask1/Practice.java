package com.example.spidertask1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Practice extends AppCompatActivity {
private double vel_prac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        EditText velocityInput= (EditText) findViewById(R.id.velocityTaken);
        EditText calculatedLorentzFactor= (EditText) findViewById(R.id.calculatedFactor);
        TextView ansDisplay= (TextView) findViewById(R.id.correctAnsDisplay);
        Button check = (Button) findViewById(R.id.checkBtn);
        DecimalFormat df= new DecimalFormat("####.######");
        df.setRoundingMode(RoundingMode.CEILING);
        check.setOnClickListener(new View.OnClickListener() {
//         final RelativeLayout relativeLayout=findViewById(R.id.change_color);
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
               vel_prac = parseDouble(velocityInput.getText().toString());


                if(calculatedLorentzFactor.getText().toString().isEmpty() || (velocityInput.getText().toString().isEmpty()))
                {
                    toastMsg("Invalid Input");
                    ansDisplay.setText("Invalid Input");
                }
//                if(velocityInput.getText().toString().length()==0)
//                {
//                    toastMsg("Invalid Input");
//                    ansDisplay.setText("Invalid Input");
//                }
                else if( Double.parseDouble(df.format(1 / sqrt(1 - (vel_prac * vel_prac / (9 * pow(10, 16)))))) == parseDouble(calculatedLorentzFactor.getText().toString()))
                {
/*
                    findViewById(R.id.change_color).setBackgroundResource(R.color.correct_ans);
                    relativeLayout.setBackgroundResource(R.color.correct_ans);
*/
                    toastMsg("Correct! ;)");
                    ansDisplay.setVisibility(View.INVISIBLE);
//                    check.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.correct_ans)));
                    check.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }

                else
                {
//                    findViewById(R.id.change_color).setBackgroundResource(R.color.incorrect_ans);
                    Vibrator vibrateOnIncorrectAns= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    {
                        vibrateOnIncorrectAns.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else
                    {
                        vibrateOnIncorrectAns.vibrate(500);
                    }
//                    relativeLayout.setBackgroundResource(R.color.incorrect_ans);
                   toastMsg("Incorrect :(");
                    check.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
//                    check.setBackgroundTint
                    ansDisplay.setText("Correct Ans is \n"+df.format(1 / sqrt(1 - (vel_prac * vel_prac / (9 * pow(10, 16))))));
                    ansDisplay.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    public void toastMsg(String s){
        Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        toast.show();
    }
}