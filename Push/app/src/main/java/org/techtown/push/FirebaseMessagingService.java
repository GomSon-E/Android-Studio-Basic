package org.techtown.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    public FirebaseMessagingService() {
    }

    @Override
    // 등록 ID가 갱신되었을 때 호출됨 <- 네트워크 환경이나 단말정보가 갱신될 때 등록 ID가 갱신됨
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d("Service", "onNewToken 호출됨 : " + token);
    }

    @Override
    // 상대방으로부터 push 메시지를 받았을 때 호출됨
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.d("Service", "onMessageReceived 호출됨");

        String from = message.getFrom(); // 상대방의 아이디
        Map<String, String> data = message.getData(); // 상대방이 보낸 데이터
        String contents = data.get("contents");

        sendToActivity(from, contents);
    }

    public void sendToActivity(String from, String contents) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|intent.FLAG_ACTIVITY_CLEAR_TOP);
        getApplicationContext().startActivity(intent);
    }
}