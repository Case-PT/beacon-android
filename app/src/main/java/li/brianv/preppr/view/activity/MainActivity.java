package li.brianv.preppr.view.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import li.brianv.preppr.R;
import li.brianv.preppr.di.HasComponent;
import li.brianv.preppr.di.components.DaggerMainComponent;
import li.brianv.preppr.di.components.MainComponent;
import li.brianv.preppr.view.fragment.FormFragment;
import li.brianv.preppr.view.fragment.MapFragment;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {
    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.mapContainer, new MapFragment());
            addFragment(R.id.formContainer, new FormFragment());
        }
    }

    private void initializeInjector() {
        this.mainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public MainComponent getComponent() {
        return mainComponent;
    }
}
