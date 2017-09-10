package li.brianv.rescueme.presenter;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.util.List;

import javax.inject.Inject;

import li.brianv.domain.Location;
import li.brianv.domain.interactor.DefaultObserver;
import li.brianv.domain.interactor.GetRescueLocations;
import li.brianv.rescueme.mapper.LatLngMapper;
import li.brianv.rescueme.view.MapView;

public class MapPresenter implements Presenter {
    private MapView mapView;
    private final GetRescueLocations getRescueLocations;

    @Inject
    public MapPresenter(GetRescueLocations getRescueLocations) {
        this.getRescueLocations = getRescueLocations;
    }

    public void setView(@NonNull MapView view) {
        this.mapView = view;
    }

    @Override
    public void resume() {
        getRescueLocations.execute(new RescueLocationObserver(), null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getRescueLocations.dispose();
        this.mapView = null;
    }

    private class RescueLocationObserver extends DefaultObserver<List<Location>> {
        @Override
        public void onNext(List<Location> locations) {
            mapView.updateHeatMap(Stream.of(locations)
                    .map(LatLngMapper::getLatLang)
                    .toList());
        }
    }
}