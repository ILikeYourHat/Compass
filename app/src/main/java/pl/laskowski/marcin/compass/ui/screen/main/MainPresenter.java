package pl.laskowski.marcin.compass.ui.screen.main;

import pl.laskowski.marcin.compass.dependency.AppComponent;
import pl.laskowski.marcin.compass.domain.GeomagneticSensor;
import pl.laskowski.marcin.compass.domain.interactor.CompassInteractor;
import pl.laskowski.marcin.compass.ui.framework.BasePresenter;
import pl.laskowski.marcin.compass.ui.utils.AnimUtils;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenter
        extends BasePresenter<MainUi>
        implements GeomagneticSensor.Listener {

    private final CompassInteractor interactor;

    public MainPresenter(MainUi ui, AppComponent component) {
        super(ui);
        this.interactor  = component.compassInteractor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        interactor.addListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        interactor.removeListener(this);
    }

    @Override
    public void onRotationChanged(float degrees) {
        prepareNeedleForRotation(degrees);
        ui.animateNeedleRotation(degrees);
    }

    private void prepareNeedleForRotation(float rotation) {
        float oldRotation = ui.getCurrentNeedleRotation();
        float fixedRotation = AnimUtils.getImaginaryOldRotation(oldRotation, rotation);
        if (oldRotation != fixedRotation){
            ui.setNeedleRotation(fixedRotation);
        }
    }

}
