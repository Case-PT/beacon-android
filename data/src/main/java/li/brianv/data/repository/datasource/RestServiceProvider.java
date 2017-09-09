package li.brianv.data.repository.datasource;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import li.brianv.data.retrofit.RestService;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RestServiceProvider {

    private RestService restService;

    @Inject
    RestServiceProvider() {

    }

    public RestService getRestService() {
        if (restService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pennapps-1504932917428.appspot.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            restService = retrofit.create(RestService.class);
        }
        return restService;
    }


}
