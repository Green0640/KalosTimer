package com.example.kalostimer;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    long baseTime = 150;
    Timer timer;
    TimerTask timerTask;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("DefaultLocale")
        @Override
        public void handleMessage(Message msg) {
            TextView timerScreen = (TextView) findViewById(R.id.timerText);
            timerScreen.setText(String.format("%02d:%02d", baseTime / 60, baseTime % 60));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView timerScreen = (TextView) findViewById(R.id.timerText);
        Button resetButton = (Button) findViewById(R.id.reset);
        Button resetAndContinueButton = (Button) findViewById(R.id.resetandcontinue);
        Button startButton = (Button) findViewById(R.id.start);
        Button bindButton = (Button) findViewById(R.id.bind);
        Button lucidButton = (Button) findViewById(R.id.LucidBind);
        Button wonseButton = (Button) findViewById(R.id.wonseBind);
        Button ruleButton = (Button) findViewById(R.id.rule);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        handler.sendMessage(new Message());
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime = 150;
                handler.sendMessage(new Message());
                if(timer != null && timerTask != null) {
                    timer.cancel();
                    timerTask.cancel();
                    timer = null;
                    timerTask = null;
                }
            }
        });
        resetAndContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime = 150;
                handler.sendMessage(new Message());
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer == null && timerTask == null) {
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            if(baseTime > 0) {
                                baseTime--;
                                handler.sendMessage(new Message());
                            } else {
                                v.vibrate(new long[]{200,200,200,200,200}, -1);
                            }
                        }
                    };
                    timer.schedule(timerTask,0,1000);
                }
            }
        });
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime += 10;
                handler.sendMessage(new Message());
            }
        });
        lucidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime += 9;
                handler.sendMessage(new Message());
            }
        });
        wonseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime += 5;
                handler.sendMessage(new Message());
            }
        });
        ruleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseTime += 30;
                handler.sendMessage(new Message());
            }
        });
    }
}