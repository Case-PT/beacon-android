package li.brianv.data.retrofit;

import io.reactivex.Observable;
import li.brianv.domain.Form;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestService {
    @POST("add_report")
    Observable<Object> submitForm(@Body Form form);
}
