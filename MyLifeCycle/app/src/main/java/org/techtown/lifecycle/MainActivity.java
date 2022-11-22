package org.techtown.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Log.d("Main", "onCreate 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Main", "onDestroy 호출됨");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Main", "onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Main", "onStop 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveState();

        Log.d("Main", "onPause 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadState();

        Log.d("Main", "onResume 호출됨");
    }

    public void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // 접근 범위 지정
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", editText.getText().toString()); // 입력상자에 입력된 데이터를 가져옴
        editor.commit(); // 가져온 데이터를 파일로 저장함
    }

    public void loadState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (pref != null) {
            String name = pref.getString("name", "");
            editText.setText(name); // name을 입력상자에 할당함
        }
    }
}