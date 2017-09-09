package li.brianv.rescueme.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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

    private SupportMapFragment supportMapFragment;

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
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
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
        LatLng sydney = new LatLng(29.7604, -95.3698);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        List<LatLng> latLngList = new ArrayList<>();
        latLngList.add(new LatLng(29.7604, -95.3698));
        latLngList.add(new LatLng(29.8604, -95.3698));
        latLngList.add(new LatLng(29.8603, -95.3698));
        latLngList.add(new LatLng(29.8602, -95.3698));
        latLngList.add(new LatLng(29.6604, -95.3698));
        TileProvider heatMapTileProvider = new HeatmapTileProvider.Builder()
                .data(latLngList)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatMapTileProvider));
    }
}
