package li.brianv.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import li.brianv.domain.Location;

public interface MapRepository {
    Observable<List<Location>> rescueLocations();

}
