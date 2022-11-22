package org.techtown.pushsend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText input1;
    TextView output1;

    static RequestQueue requestQueue;
    static String regId = "cAB35lhNR-2v4FSKl3bRqc:APA91bHZnFOQj9SFkli-Sfuvnsk2sXLVzFbK8De0_SYCUkI6t9AiWIgFMjLvFIW81xWH2jEhRodTuE8tMO8fc2Ftc2pfN_kEnHYJEpT6aRH3XSWfvk4ofJfAlfGmF1Qk7PSwohLw5v1u"; // 수신할 사람의 등록 ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = input1.getText().toString();
                send(input);
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public void send(String input) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray(); // 한번에 여러 ID, 여러 수신자에게 데이터를 전송할 수 있으므로 Array 사용
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                // 요청을 위한 정보
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,

                // 응답
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },

                // 에러가 생길 때
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=AAAAB3Pdvcc:APA91bHew7SQrE0pajDn64L7LeEzvx8BwjQjrBQqizRm2qSEKg0qyhVIhAPzv1M1B4A-hH7Qv36-adPbHN3TVSgnka-0eK2GcUo8lmTeHjmDLiFufXzv8fiRfVcTiKj3nE8YdVO9viv6");
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        request.setShouldCache(false); // 한번 요청한 정보가 캐쉬로 나오면 요청한 정보가 갱신이 되지 않는 문제가 발생할 수 있어 false
        requestQueue.add(request); // 자동으로 알아서 요청하고 받음
        println("요청 보냄");
    }

    public void println(String message) {
        output1.append(message + "\n");
    }
}