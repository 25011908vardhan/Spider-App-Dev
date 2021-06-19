package com.example.spidertask1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView answer= (TextView) findViewById(R.id.ans);
        Button calcBtn= (Button) findViewById(R.id.Calculate);
        EditText velInput= (EditText)findViewById(R.id.vel);
        Button prac_session= (Button) findViewById(R.id.prac_sessionBtn);
        Button spiFactorCalc= (Button) findViewById(R.id.spiFactor_CalcBtn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(velInput.getText().toString().length()==0)
                {
                    answer.setText("0");
                }

                else if( Double.parseDouble(velInput.getText().toString())>=3*pow(10,8))
                {
                    Toast invalid= Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT);
                    invalid.show();
                }

                else{
                    double vel = Double.parseDouble(velInput.getText().toString());

                double lorentz_factor= 1/sqrt(1-(vel*vel/(9*pow(10,16))));
                    DecimalFormat df= new DecimalFormat("######.######");
                    df.setRoundingMode(RoundingMode.CEILING);

                    lorentz_factor = Double.parseDouble(df.format(lorentz_factor));



                answer.setText(String.valueOf(lorentz_factor));
            }
            }
        });

        prac_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        spiFactorCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2ndActivity();
            }
        });
        }
        public void open2ndActivity(){
        Intent intent2= new Intent(getApplicationContext(), SpiFactorActivity.class);
        startActivity(intent2);
        }

        public void openNewActivity(){
            Intent intent= new Intent(this, Practice.class);
            startActivity(intent);
    }

    }
