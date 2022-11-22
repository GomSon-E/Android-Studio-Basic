package org.techtown.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toastView = Toast.makeText(getApplicationContext(), "토스트 메시지 입니다", Toast.LENGTH_LONG);
                // 사용자 지정 위치
                toastView.setGravity(Gravity.TOP|Gravity.LEFT, 200, 800);
                toastView.show();
            }
        });
    }
}