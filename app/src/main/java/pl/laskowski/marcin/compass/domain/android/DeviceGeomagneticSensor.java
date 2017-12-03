package pl.laskowski.marcin.compass.domain.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Objects;

import pl.laskowski.marcin.compass.domain.utils.AngleUtils;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class DeviceGeomagneticSensor
        implements GeomagneticSensor, SensorEventListener {

    private static final float MATRIX_CHANGE_RATIO = 0.7f;
    private static final int RESULT_MATRIX_SIZE = 3;

    private final SensorManager sensorManager;
    private final Sensor magnetometer;
    private final Sensor accelerometer;

    private float[] accelerometerMatrix = new float[RESULT_MATRIX_SIZE];
    private float[] magneticFieldMatrix = new float[RESULT_MATRIX_SIZE];

    private Listener listener;

    public DeviceGeomagneticSensor(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager);
        this.magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void startUpdates() {
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void stopUpdates() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public synchronized void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                updateMatrix(accelerometerMatrix, event.values);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                updateMatrix(magneticFieldMatrix, event.values);
                break;
        }
        updateRotation();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    private void updateMatrix(float[] oldValues, float[] newValues) {
        float oldValuesRatio = 1 - MATRIX_CHANGE_RATIO;
        for (int i = 0; i < RESULT_MATRIX_SIZE; i++) {
            oldValues[i] = oldValuesRatio * oldValues[i] + MATRIX_CHANGE_RATIO * newValues[i];
        }
    }

    private void updateRotation() {
        float R[] = new float[9];
        boolean success = SensorManager.getRotationMatrix(R, null, accelerometerMatrix, magneticFieldMatrix);
        if (success) {
            float orientation[] = new float[3];
            SensorManager.getOrientation(R, orientation);
            float degree = AngleUtils.toNormalizedDegrees(orientation[0]);
            notifyNewValue(degree);
        }
    }

    private void notifyNewValue(float degree) {
       if (listener != null) listener.onRotationChanged(degree);
    }

}
