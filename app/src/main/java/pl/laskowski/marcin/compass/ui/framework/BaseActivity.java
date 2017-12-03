package pl.laskowski.marcin.compass.ui.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import pl.laskowski.marcin.compass.App;
import pl.laskowski.marcin.compass.dependency.AppComponent;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public abstract class BaseActivity<Presenter extends BasePresenter>
        extends AppCompatActivity
        implements BaseUi {

    @LayoutRes
    private final int layout;
    protected Presenter presenter;

    public BaseActivity(@LayoutRes int layout) {
        this.layout = layout;
    }

    protected abstract Presenter providePresenter(AppComponent component);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpContent();
        presenter = providePresenter(component());
    }

    private void setUpContent() {
        setContentView(layout);
        ButterKnife.bind(this);
    }

    private AppComponent component() {
        return ((App) getApplication()).getComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

}
