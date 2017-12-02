package pl.laskowski.marcin.compass.ui.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import pl.laskowski.marcin.compass.R;
import pl.laskowski.marcin.compass.domain.GeomagneticSensor;
import pl.laskowski.marcin.compass.domain.android.DeviceGeomagneticSensor;
import pl.laskowski.marcin.compass.ui.framework.BaseActivity;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainUi, GeomagneticSensor.Listener {

    @BindView(R.id.activityMain_vArrow)
    View vArrow;

    private GeomagneticSensor sensor;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensor = new DeviceGeomagneticSensor(this);
    }

    @Override
    protected MainPresenter providePresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensor.addListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensor.removeListener(this);
    }

    @Override
    public void onRotationChanged(float degrees) {
        Log.d("Tag", "" + degrees);
        vArrow.setRotation(degrees);
    }
}
