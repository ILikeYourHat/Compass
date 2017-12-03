package pl.laskowski.marcin.compass.ui.screen.main;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import butterknife.BindView;
import pl.laskowski.marcin.compass.R;
import pl.laskowski.marcin.compass.dependency.AppComponent;
import pl.laskowski.marcin.compass.ui.framework.BaseActivity;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainUi {

    @BindView(R.id.activityMain_vNeedle)
    View vNeedle;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected MainPresenter providePresenter(AppComponent component) {
        return new MainPresenter(this, component);
    }

    @Override
    public void animateNeedleRotation(float degrees) {
        vNeedle.animate()
                .setInterpolator(new DecelerateInterpolator())
                .rotation(degrees);
    }

    @Override
    public void setNeedleRotation(float degrees) {
        vNeedle.setRotation(degrees);
    }

    @Override
    public float getCurrentNeedleRotation() {
        return vNeedle.getRotation();
    }

}
