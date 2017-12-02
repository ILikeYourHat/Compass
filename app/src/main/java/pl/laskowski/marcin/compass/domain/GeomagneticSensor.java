package pl.laskowski.marcin.compass.domain;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public interface GeomagneticSensor {

    void addListener(Listener listener);

    void removeListener(Listener listener);

    interface Listener {
        void onRotationChanged(float degrees);
    }

}
