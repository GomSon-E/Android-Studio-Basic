package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate 호출됨");  // 디버깅메시지를 정의함
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { // 서비스를 시작하는 용도 보다는 데이터를 전달하기 위한 목적으로 많이 사용이 됨
        Log.d(TAG, "onStartCommand 호출됨");

        if (intent != null) {
            processCommand(intent);
        } else {
            return Service.START_STICKY; // 자동으로 재시작해라
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command : " + command + ", name : " + name);

        try {
            Thread.sleep(5000); // 5초 동안 휴식
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|  // 화면이 하나의 앱에서 계속 연속적으로 보이는 것처럼 동작하면서 Task라는 것을 계속 만듦
                Intent.FLAG_ACTIVITY_SINGLE_TOP| // 이미 MainActivity가 만들어져있으면 그걸 계속 보여줌
                Intent.FLAG_ACTIVITY_CLEAR_TOP); // 여러 개가 쌓여있으면 제일 상단의 것을 없앰
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service");
        startActivity(showIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy 호출됨");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}