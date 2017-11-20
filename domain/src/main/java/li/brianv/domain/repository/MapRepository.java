package li.brianv.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import li.brianv.domain.MyLocation;

public interface MapRepository {
    Observable<List<MyLocation>> rescueLocations();
    String getUserLocation();

}
