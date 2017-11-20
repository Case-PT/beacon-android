package li.brianv.data.repository;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.MyLocation;
import li.brianv.domain.repository.MapRepository;

public class MapDataRepository implements MapRepository {


    private FusedLocationProviderClient mFusedLocationClient;
    Context aContext;
    android.location.Location userLocation;

    @Inject
    MapDataRepository(Context context) {
        aContext = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(aContext);
    }

    public String getUserLocation() {

        if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }

        Task<Location> loc = mFusedLocationClient.getLastLocation();
        loc.addOnSuccessListener((Activity) aContext, location -> userLocation = loc.getResult());
        String coordinates = (loc.getResult().getLatitude() + ", " + loc.getResult().getLongitude());
        return coordinates;

    }

    private MyLocation generateLocation(double lat, double lon) {
        double latInc = Math.random();
        double longInc = Math.random();
        if (latInc < 0.5) {
            latInc *= 0.3 * Math.random();
        }
        if (longInc > 0.5)
            longInc *= 0.3 * Math.random();
        lat -= latInc;
        lon -= longInc;
        return new MyLocation(lat, lon);
    }

    @Override
    public Observable<List<MyLocation>> rescueLocations() {

        return Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(intVal -> {
                    if (intVal < 100) {
                        List<MyLocation> list = new ArrayList<>();
                        list.add(generateLocation(29.9604, -95.7698));
                        list.add(generateLocation(28.5383, -81.3792));
                        list.add(generateLocation(26.1524, -80.5373));
                        list.add(generateLocation(22.8781, 87.6298));
                        list.add(generateLocation(29.0299, 111.6961));
                        list.add(generateLocation(41.1278, -81.6098));
                        return list;
                    } else
                        return Collections.emptyList();
                });
    }
}
