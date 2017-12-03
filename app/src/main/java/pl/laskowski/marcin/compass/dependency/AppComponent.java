package pl.laskowski.marcin.compass.dependency;

import javax.inject.Singleton;

import dagger.Component;
import pl.laskowski.marcin.compass.domain.interactor.CompassInteractor;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

@Singleton
@Component(modules = {
        AppModule.class,
        SensorModule.class
})
public interface AppComponent {

    CompassInteractor compassInteractor();

}
