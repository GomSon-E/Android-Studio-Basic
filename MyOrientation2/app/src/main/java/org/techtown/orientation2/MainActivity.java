package org.techtown.orientation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 가로 방향이면
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showToast("가로 방향");
        }
        // 세로 방향이면
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showToast("세로 방향");
        }

    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}