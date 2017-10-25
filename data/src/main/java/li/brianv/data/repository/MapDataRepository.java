package li.brianv.data.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.Location;
import li.brianv.domain.repository.MapRepository;

public class MapDataRepository implements MapRepository {

    @Inject
    MapDataRepository() {

    }

    @Override
    public Observable<List<Location>> rescueLocations() {
        return Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(intVal -> {
                    if(intVal < 100) {
                        List<Location> list = new ArrayList<>();
                        list.add(generateLocation(29.9604, -95.7698));
                        list.add(generateLocation(28.5383, -81.3792));
                        list.add(generateLocation(26.1524, -80.5373));
                        list.add(generateLocation(22.8781, 87.6298));
                        list.add(generateLocation(29.0299, 111.6961));
                        return list;
                    } else
                        return Collections.emptyList();
                });
    }

    private Location generateLocation(double lat, double lon) {
        double latInc = Math.random();
        double longInc = Math.random();
        if (latInc < 0.5) {
            latInc *= 0.3 * Math.random();
        }
        if (longInc > 0.5)
            longInc *= 0.3 * Math.random();
        lat -= latInc;
        lon -= longInc;
        return new Location(lat, lon);
    }
}
