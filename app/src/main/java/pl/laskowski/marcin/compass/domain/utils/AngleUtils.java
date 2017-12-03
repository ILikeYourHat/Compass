package pl.laskowski.marcin.compass.domain.utils;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class AngleUtils {

    private AngleUtils() {
    }

    public static float toNormalizedDegrees(double radians) {
        float degrees = (float) Math.toDegrees(radians);
        return (degrees + 360) % 360;
    }

}
