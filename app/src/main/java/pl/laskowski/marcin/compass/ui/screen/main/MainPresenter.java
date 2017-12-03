package pl.laskowski.marcin.compass.ui.screen.main;

import pl.laskowski.marcin.compass.dependency.AppComponent;
import pl.laskowski.marcin.compass.domain.interactor.CompassInteractor;
import pl.laskowski.marcin.compass.ui.framework.BasePresenter;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenter
        extends BasePresenter<MainUi> {

    private final CompassInteractor interactor;

    public MainPresenter(MainUi ui, AppComponent component) {
        super(ui);
        this.interactor  = component.compassInteractor();
    }

}
