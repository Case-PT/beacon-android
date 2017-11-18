package li.brianv.rescueme.view;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapView {
    void updateHeatMap(List<LatLng> rescueLocations);
}
