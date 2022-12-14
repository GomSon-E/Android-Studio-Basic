package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
//    MainHandler handler;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start(); // start -> run
            }
        });
        handler = new Handler();
    }
    class BackgroundThread extends Thread {
        int value = 0;
        public void run() {
            for (int i = 0; i < 100; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                value += 1;
                Log.d("Thread", "value : " + value);

//                방법 1. ERROR
//                textView.setText("값 : " + value);

//                방법 2. 코드가 길어짐
//                Message message = handler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("value", value);
//                message.setData(bundle);
//
//                handler.sendMessage(message);

//              방법 3.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : " + value);
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : " + value);
                    }
                }, 5000);
            }
        }
    }

//    class MainHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//            Bundle bundle = msg.getData();
//            int value = bundle.getInt("value");
//            textView.setText("값 : " + value);
//        }
//    }
}