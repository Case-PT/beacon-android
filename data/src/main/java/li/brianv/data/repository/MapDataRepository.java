package li.brianv.data.repository;

import java.util.ArrayList;
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
        return Observable.interval(5000, TimeUnit.MILLISECONDS)
                .map(intVal -> {
                    List<Location> list = new ArrayList<>();
                    list.add(new Location(29.6604 + Math.random(), -95.3698 + Math.random()));
                    return list;
                });
    }
}
