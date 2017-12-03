package pl.laskowski.marcin.compass;

import android.app.Application;

import pl.laskowski.marcin.compass.dependency.AppComponent;
import pl.laskowski.marcin.compass.dependency.AppModule;
import pl.laskowski.marcin.compass.dependency.DaggerAppComponent;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpComponent();
    }

    private void setUpComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
    }

    public AppComponent getComponent() {
        return component;
    }

    public void setComponent(AppComponent component) {
        this.component = component;
    }

}
