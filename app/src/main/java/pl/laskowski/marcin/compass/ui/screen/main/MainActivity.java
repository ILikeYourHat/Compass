package pl.laskowski.marcin.compass.ui.screen.main;

import pl.laskowski.marcin.compass.R;
import pl.laskowski.marcin.compass.ui.framework.BaseActivity;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainUi {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected MainPresenter providePresenter() {
        return new MainPresenter(this);
    }

}
