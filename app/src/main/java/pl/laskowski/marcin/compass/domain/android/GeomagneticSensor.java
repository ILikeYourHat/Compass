package pl.laskowski.marcin.compass.domain.android;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public interface GeomagneticSensor {

    void startUpdates();

    void stopUpdates();

    void setListener(Listener listener);

    interface Listener {
        void onRotationChanged(float degrees);
    }

}
