package li.brianv.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.domain.Form;
import li.brianv.domain.repository.FormRepository;


public class FormDataRepository implements FormRepository {

    @Inject
    FormDataRepository() {

    }

    @Override
    public Observable<Void> submitForm(Form form) {
        return null;
    }
}
