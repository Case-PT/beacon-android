package li.brianv.data.retrofit;

import java.util.List;

import io.reactivex.Observable;
import li.brianv.domain.Form;
import li.brianv.domain.MyLocation;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestService {
    @POST("manual_report")
    Observable<Object> submitForm(@Body Form form);

    @GET("rescue_locations")
    Observable<List<MyLocation>> rescueLocations();
}
