package pl.laskowski.marcin.compass.ui.utils;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class AnimUtils {

    private AnimUtils() {
    }

    // To ensure animation rotation is done in the shortest direction
    // Return value of this function should be assigned to target view's rotation
    public static float getImaginaryOldRotation(float oldRotation, float newRotation) {
        if (oldRotation > newRotation && (oldRotation - newRotation) > 180) {
            return oldRotation - 360;
        } else if (oldRotation < newRotation && (newRotation - oldRotation) > 180) {
            return oldRotation + 360;
        } else {
            return oldRotation;
        }
    }

}
