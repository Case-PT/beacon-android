package li.brianv.data.repository;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
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
    Task<Location> loc;

    @Inject
    MapDataRepository(Context context) {
        aContext = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(aContext);

        if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                Log.d("HELP", "access fine location permission not granted!");
            if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                Log.d("HELP", "access coarse location permission not granted!");
        }
        loc = mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    userLocation = task.getResult();
                } else {
                    Log.d("LOCATE", "Failed to get location.");
                }
            }
        });
    }

    public String getUserLocation() {

        Log.d("LOCATE", "permission granted!");

        if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                Log.d("HELP", "access fine location permission not granted!");
            if (ActivityCompat.checkSelfPermission(aContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                Log.d("HELP", "access coarse location permission not granted!");
        }
        Task<Location> loc = mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    userLocation = task.getResult();
                } else {
                    Log.d("LOCATE", "Failed to get location.");
                }
            }
        });

        String coordinates = (userLocation.getLatitude() + ", " + userLocation.getLongitude());
        Log.d("LOCATE", "Coordinates: " + coordinates);
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
                        return list;
                    } else
                        return Collections.emptyList();
                });
    }
}
