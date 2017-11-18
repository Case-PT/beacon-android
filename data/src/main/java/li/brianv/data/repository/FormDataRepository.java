package li.brianv.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import li.brianv.data.repository.datasource.RestServiceProvider;
import li.brianv.domain.Form;
import li.brianv.domain.repository.FormRepository;

public class FormDataRepository implements FormRepository {
    private static final String LOG_TAG = FormDataRepository.class.getSimpleName();

    private final RestServiceProvider restServiceProvider;

    @Inject
    FormDataRepository(RestServiceProvider restServiceProvider) {
        this.restServiceProvider = restServiceProvider;
    }

    @Override
    public Observable<Object> submitForm(Form form) {
        return restServiceProvider
                .getRestService()
                .submitForm(form);
    }
}
