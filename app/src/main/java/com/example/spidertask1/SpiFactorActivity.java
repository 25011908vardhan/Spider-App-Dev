package com.example.spidertask1;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static java.time.LocalDateTime.now;

public class SpiFactorActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spi_factor);
        TextView ans= (TextView) findViewById(R.id.ansSpiFact);
//        Timer update= new Timer();
//        update.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
////                Date today = new Date();
////                Calendar current= GregorianCalendar.getInstance(today);
////                current.setTime(today);
////                int hour= current.get(Calendar.HOUR);
////                int min= current.get(Calendar.MINUTE);
////                int seconds=current.get(Calendar.SECOND);
//                int hour= now().getHour();
//                int min=now().getMinute();
//                int seconds=now().getSecond();
//                int hourFactorial=1;
//                for(int i=hour;i>=1;i--)
//                    hourFactorial *= hour;
//
//                double spiFactor= hourFactorial/(Math.pow(min,3)+seconds);
//                ans.setText(String.valueOf(spiFactor));
//            }
//        },0,1000);
        Thread t= new Thread(){
            @Override
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int hour= now().getHour();
                int min=now().getMinute();
                int seconds=now().getSecond();
                int hourFactorial=1;
                for(int i=hour;i>=1;i--)
                    hourFactorial *= hour;

                double spiFactor= hourFactorial/(Math.pow(min,3)+seconds);
                ans.setText(String.valueOf(spiFactor));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();



//         int current_hour= current.getHours();
//        DateTimeFormatter spiFormat= DateTimeFormatter.ofPattern("HH:mm:ss");
//        String current_time= LocalDateTime.now()
    }
}