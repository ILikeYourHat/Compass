package pl.laskowski.marcin.compass.ui.framework;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public abstract class BasePresenter<Ui extends BaseUi> {

    protected final Ui ui;

    public BasePresenter(Ui ui) {
        this.ui = ui;
    }

    protected void onStart() {
    }

    protected void onStop() {
    }

}
