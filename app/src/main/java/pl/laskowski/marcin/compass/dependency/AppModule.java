package pl.laskowski.marcin.compass.dependency;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pl.laskowski.marcin.compass.App;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Context providesContext() {
        return app;
    }

}
