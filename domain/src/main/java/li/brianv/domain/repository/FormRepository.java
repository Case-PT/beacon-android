package li.brianv.domain.repository;

import io.reactivex.Observable;
import li.brianv.domain.Form;

public interface FormRepository {
    Observable<Object> submitForm(Form form);
}
