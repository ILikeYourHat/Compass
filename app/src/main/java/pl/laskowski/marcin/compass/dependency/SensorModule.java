package pl.laskowski.marcin.compass.dependency;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.laskowski.marcin.compass.domain.android.GeomagneticSensor;
import pl.laskowski.marcin.compass.domain.android.DeviceGeomagneticSensor;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

@Module
public class SensorModule {


    @Provides
    @Singleton
    public GeomagneticSensor provideGeomagneticSensor(Context context) {
        return new DeviceGeomagneticSensor(context);
    }

}
