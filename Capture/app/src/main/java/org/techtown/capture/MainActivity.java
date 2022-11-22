package org.techtown.capture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener{
    CameraSurfaceView cameraSurfaceView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        FrameLayout container = findViewById(R.id.container);
        cameraSurfaceView = new CameraSurfaceView(this);
        container.addView(cameraSurfaceView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        showToast("권한 거부 : " + permissions.length);
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        showToast("권한 허용 : " + permissions.length);
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    public void capture() {
        cameraSurfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                // 사진이 찍히면 사진을 byte array로 전달해줌
                // 전달 받은 byte array를 bitmap 객체로 바꿈 -> 화면에 표시할 수 있게 됨
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, databaseList().length);
                // 메모리에 만든 bitmap 객체를 imageView에 보여줌
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}