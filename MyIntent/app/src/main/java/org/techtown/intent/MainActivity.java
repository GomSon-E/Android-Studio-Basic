package org.techtown.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    // Menu activity에서 Main activity로 되돌아 올 때 자동으로 실행됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 화면마다 다른 requestCode를 통해 어떤 화면인지 구분할 수 있음
        if(requestCode == 101) {
            if(data != null) {
                String name = data.getStringExtra("name");
                if (name != null) {
                    Toast.makeText(this, "응답으로 받은 데이터 : "+ name, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}