package org.techtown.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView output1;
    SensorManager manager;
    List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output1 = findViewById(R.id.output1);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSensorList();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFirstSensor();
            }
        });
    }

    public void getFirstSensor() {
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                println("Sensor timestamp : " + sensorEvent.timestamp);

                for (int i = 0; i < sensorEvent.values.length; i++) {
                    println("    value #" + i + " : " + sensorEvent.values[i]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        },
        sensors.get(0),
        SensorManager.SENSOR_DELAY_UI);
    }

    public void getSensorList() {
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = manager.getSensorList(Sensor.TYPE_ALL);

        int index = 0;
        for (Sensor sensor : sensors) {
            println("#" + index + " : " + sensor.getName());
            index += 1;
        }
    }

    public void println(String message) {
        output1.append(message + "\n");
    }
}