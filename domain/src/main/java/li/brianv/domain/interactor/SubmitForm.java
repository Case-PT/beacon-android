package li.brianv.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.Form;
import li.brianv.domain.executor.PostExecutionThread;
import li.brianv.domain.executor.ThreadExecutor;
import li.brianv.domain.repository.FormRepository;

public class SubmitForm extends UseCase<Object, SubmitForm.Params> {

    private final FormRepository formRepository;

    @Inject
    SubmitForm(FormRepository formRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.formRepository = formRepository;
    }

    @Override
    Observable<Object> buildUseCaseObservable(Params params) {
        return formRepository.submitForm(params.form);
    }

    public static final class Params {
        private final Form form;

        private Params(Form form) {
            this.form = form;
        }

        public static Params forForm(Form form) {
            return new Params(form);
        }

    }
}

