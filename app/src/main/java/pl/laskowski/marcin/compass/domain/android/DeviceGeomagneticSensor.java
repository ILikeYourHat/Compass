package pl.laskowski.marcin.compass.domain.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

import pl.laskowski.marcin.compass.domain.GeomagneticSensor;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class DeviceGeomagneticSensor
        implements GeomagneticSensor, SensorEventListener {

    private final SensorManager sensorManager;
    private final Sensor magnetometer;
    private final List<Listener> listeners = new ArrayList<>();

    public DeviceGeomagneticSensor(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager == null) throw new RuntimeException("SensorManager unavailable in this device");
        this.magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void addListener(Listener listener) {
        synchronized (this) {
            if (listeners.isEmpty()) startUpdates();
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(Listener listener) {
        synchronized (this) {
            listeners.remove(listener);
            if (listeners.isEmpty()) stopUpdates();
        }
    }

    private void startUpdates() {
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    private void stopUpdates() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = event.values[0];
        for (Listener listener : listeners) {
            listener.onRotationChanged(degree);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

}
