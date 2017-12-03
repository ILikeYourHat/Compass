package pl.laskowski.marcin.compass.ui.screen.main;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import butterknife.BindView;
import pl.laskowski.marcin.compass.R;
import pl.laskowski.marcin.compass.dependency.AppComponent;
import pl.laskowski.marcin.compass.domain.GeomagneticSensor;
import pl.laskowski.marcin.compass.ui.framework.BaseActivity;
import pl.laskowski.marcin.compass.ui.utils.AnimUtils;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainUi, GeomagneticSensor.Listener {

    @BindView(R.id.activityMain_vArrow)
    View vArrow;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected MainPresenter providePresenter(AppComponent component) {
        return new MainPresenter(this, component);
    }

    @Override
    public void onRotationChanged(float rotation) {
        prepareArrowForRotation(rotation);
        vArrow.animate()
                .setInterpolator(new DecelerateInterpolator())
                .rotation(rotation);
    }

    private void prepareArrowForRotation(float rotation) {
        float oldRotation = vArrow.getRotation();
        float fixedRotation = AnimUtils.getImaginaryOldRotation(oldRotation, rotation);
        if (oldRotation != fixedRotation) vArrow.setRotation(fixedRotation);
    }

}
