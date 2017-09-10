package li.brianv.rescueme.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
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
public class MainActivity extends BaseActivity implements HasComponent<MainComponent>, NavigationView.OnNavigationItemSelectedListener {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
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

        setupToolbar();

        // Setup menu navigation with toolbar (This constructor will use the onOptionsItemSelected() method for onClicks()
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };
        drawerToggle.syncState();

        // Prevent swiping from right
        this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
        this.drawerLayout.addDrawerListener(drawerToggle);

        // Prevent main content from dimming while drawer is open
        this.drawerLayout.setScrimColor(Color.TRANSPARENT);

        // Default selected item to schedule
        this.navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.END))
                    onBackPressed();
                else
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Don't do anything if this is the current item
        if (item.isChecked())
            return false;

//        mainToolbar.setTitle(item.getTitle());

        switch (item.getItemId()) {
            case R.id.item_maps:
                break;
            case R.id.item_near:
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) || drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
        } else
            super.onBackPressed();
    }

    private void setupToolbar() {
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
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
