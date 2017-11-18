package li.brianv.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.Location;
import li.brianv.domain.executor.PostExecutionThread;
import li.brianv.domain.executor.ThreadExecutor;
import li.brianv.domain.repository.MapRepository;

public class GetRescueLocations extends UseCase<List<Location>, Void> {

    private final MapRepository mapRepository;

    @Inject
    GetRescueLocations(MapRepository mapRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mapRepository = mapRepository;
    }

    @Override
    Observable<List<Location>> buildUseCaseObservable(Void aVoid) {
        return mapRepository.rescueLocations();
    }
}
