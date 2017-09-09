package li.brianv.rescueme.view.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import li.brianv.rescueme.R;
import li.brianv.rescueme.di.components.MainComponent;
import li.brianv.rescueme.presenter.MapPresenter;
import li.brianv.rescueme.view.MapView;

public class MapFragment extends BaseFragment implements MapView, OnMapReadyCallback {
    private static final String LOG_TAG = MapFragment.class.getSimpleName();

    @Inject
    MapPresenter mapPresenter;

    private GoogleMap googleMap;
    private List<LatLng> rescueLocations;

    private Unbinder unbinder;

    @Override
    public void onResume() {
        super.onResume();
        mapPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapPresenter.destroy();
    }

    public MapFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMap();
    }

    private void initMap() {
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mapPresenter.setView(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng houston = new LatLng(29.7604, -95.3698);
        addMarker(houston, "Marker in Houston");
        styleMap();
    }

    private void addMarker(LatLng markerPosition, String title) {
        googleMap.addMarker(new MarkerOptions().position(markerPosition)
                .title(title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(markerPosition));
    }

    private void styleMap() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this.getContext(), R.raw.style_json));

            if (!success) {
                Log.e(LOG_TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(LOG_TAG, "Can't find style. Error: ", e);
        }
    }

    @Override
    public void updateHeatMap(LatLng rescueLocation) {
        if (rescueLocations == null)
            rescueLocations = new ArrayList<>();
        rescueLocations.add(rescueLocation);
        TileProvider heatMapTileProvider = new HeatmapTileProvider.Builder()
                .data(rescueLocations)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        if (googleMap != null)
            googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatMapTileProvider));
    }
}
