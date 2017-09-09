package li.brianv.rescueme.view;

import com.google.android.gms.maps.model.LatLng;

public interface MapView {
    void updateHeatMap(LatLng rescueLocation);
}
