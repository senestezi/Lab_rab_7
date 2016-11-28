package com.example.bohdanov7;


import android.hardware.SensorEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.content.Context;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    TextView firstTextView;
    TextView secondTextView;
    Button button;

    float maxX = 0, maxY = 0, maxZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        firstTextView = (TextView) findViewById(R.id.textView);
        secondTextView = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                maxX = maxY = maxZ = 0;
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        firstTextView.setText(Float.toString(x) + " " + Float.toString(y) + " " + Float.toString(z));

        boolean needUpdateMaxTextView = false;
        if (x > maxX) {
            maxX = x;
            needUpdateMaxTextView = true;
        }
        if (y > maxY) {
            maxY = y;
            needUpdateMaxTextView = true;
        }
        if (z > maxZ) {
            maxZ = z;
            needUpdateMaxTextView = true;
        }
        if (needUpdateMaxTextView)
            secondTextView.setText(Float.toString(maxX) + " " + Float.toString(maxY) + " " + Float.toString(maxZ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

}
