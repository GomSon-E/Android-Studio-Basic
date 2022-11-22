package org.techtown.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 이 화면이 띄워지는 시점에 main activity에서 보낸 intent를 받게 됨
        Intent intent = getIntent();
        processIntent(intent);
    }
    public void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras(); // -> 그 안에 있는 번들 객체를 참조할 수 있게됨
            SimpleData data = bundle.getParcelable("data");
            if (data != null) {
                Toast.makeText(this, "전달받은 객체" + data.code + ", " + data.message, Toast.LENGTH_LONG).show();
            }
        }
    }
}