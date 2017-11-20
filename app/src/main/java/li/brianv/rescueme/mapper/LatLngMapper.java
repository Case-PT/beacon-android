package li.brianv.rescueme.mapper;

import com.google.android.gms.maps.model.LatLng;

import li.brianv.domain.MyLocation;

public class LatLngMapper {
    public static LatLng getLatLang(MyLocation location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
