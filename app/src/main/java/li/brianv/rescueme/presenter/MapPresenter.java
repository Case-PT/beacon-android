package li.brianv.rescueme.presenter;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import li.brianv.rescueme.view.MapView;

public class MapPresenter implements Presenter {
    private MapView mapView;

    @Inject
    public MapPresenter() {
    }

    public void setView(@NonNull MapView view) {
        this.mapView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
//        this.getUserDetailsUseCase.dispose();
        this.mapView = null;
    }
}