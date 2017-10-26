package li.brianv.domain.interactor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.executor.PostExecutionThread;
import li.brianv.domain.executor.ThreadExecutor;

public class GetTestMessage extends UseCase<String, Void> {
    @Inject
    GetTestMessage(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    Observable<String> buildUseCaseObservable(Void aVoid) {
        return Observable.interval(4000, TimeUnit.MILLISECONDS).map(
                new io.reactivex.functions.Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return aLong + "";
                    }
                });
    }
}
