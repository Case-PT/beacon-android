package li.brianv.rescueme.mapper;

import com.google.android.gms.maps.model.LatLng;

import li.brianv.domain.Location;

public class LatLngMapper {
    public static LatLng getLatLang(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
