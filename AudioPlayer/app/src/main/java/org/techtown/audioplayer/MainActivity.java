package org.techtown.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫번째 버튼
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        // 두번째 버튼
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

        // 세번째 버튼
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });

        // 네번째 버튼
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });
    }

    public void playAudio() {
        showToast("음악 파일 재생");

        // play 버튼을 여러번 누르는 경우를 고려
        killPlayer();

        player = new MediaPlayer();
        try {
            player.setDataSource(url);
            player.prepare(); // 파일의 앞부분을 불러들여 파일을 재생할 준비를 함 (정상적인 파일인지 등을 확인함)
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void killPlayer() {
        if (player != null) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopAudio() {
        showToast("음악 파일 재생 중지");

        if (player != null) {
            try {
                player.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseAudio() {
        showToast("음악 파일 재생 일시정지");

        if (player != null) {
            try {
                position = player.getCurrentPosition();
                player.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resumeAudio() {
        showToast("음악 파일 재시작");

        if (player != null && !player.isPlaying()) {
            try {
                player.start();
                player.seekTo(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}