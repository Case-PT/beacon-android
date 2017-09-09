package li.brianv.rescueme.view.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import li.brianv.rescueme.R;
import li.brianv.rescueme.di.HasComponent;
import li.brianv.rescueme.di.components.DaggerMainComponent;
import li.brianv.rescueme.di.components.MainComponent;
import li.brianv.rescueme.view.fragment.FormFragment;
import li.brianv.rescueme.view.fragment.MapFragment;

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
