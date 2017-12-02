package pl.laskowski.marcin.compass.ui.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.laskowski.marcin.compass.ui.utils.AnimUtils.*;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ImaginaryRotationTest {

    @Test
    public void shouldReturnSameValueForSmallRotation() {
        float oldRotation = 90f;
        float newRotation = 100f;
        assertEquals(90f, getImaginaryOldRotation(oldRotation, newRotation), Float.MIN_VALUE);
    }

    @Test
    public void shouldReturnImaginaryValueForClockwiseRotation() {
        float oldRotation = 359f;
        float newRotation = 0f;
        assertEquals(-1f, getImaginaryOldRotation(oldRotation, newRotation), Float.MIN_VALUE);
    }

    @Test
    public void shouldReturnImaginaryValueForCounterClockwiseRotation() {
        float oldRotation = 0f;
        float newRotation = 359f;
        assertEquals(360f, getImaginaryOldRotation(oldRotation, newRotation), Float.MIN_VALUE);
    }

}
