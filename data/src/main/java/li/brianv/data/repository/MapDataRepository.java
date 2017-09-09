package li.brianv.data.repository;

import java.util.List;

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
        return null;
    }
}
