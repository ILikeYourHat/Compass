package pl.laskowski.marcin.compass.domain.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.laskowski.marcin.compass.domain.GeomagneticSensor;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class CompassInteractor implements GeomagneticSensor.Listener {

    private final GeomagneticSensor sensor;
    private final List<GeomagneticSensor.Listener> listeners = new ArrayList<>();

    @Inject
    public CompassInteractor(GeomagneticSensor sensor) {
        this.sensor = sensor;
        sensor.setListener(this);
    }

    @Override
    public void onRotationChanged(float degrees) {
        for (GeomagneticSensor.Listener listener : listeners) {
            listener.onRotationChanged(degrees);
        }
    }

    public void addListener(GeomagneticSensor.Listener listener) {
        synchronized (this) {
            if (listeners.isEmpty()) sensor.startUpdates();
            listeners.add(listener);
        }
    }

    public void removeListener(GeomagneticSensor.Listener listener) {
        synchronized (this) {
            listeners.remove(listener);
            if (listeners.isEmpty()) sensor.stopUpdates();
        }
    }

}
