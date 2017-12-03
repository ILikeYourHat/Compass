package pl.laskowski.marcin.compass.ui.screen.main;

import pl.laskowski.marcin.compass.ui.framework.BaseUi;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public interface MainUi extends BaseUi {

    void animateNeedleRotation(float degrees);

    void setNeedleRotation(float degrees);

    float getCurrentNeedleRotation();

}
