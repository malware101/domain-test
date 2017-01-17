package android.domain.com.testapplication.presenter;

import android.domain.com.testapplication.model.MapSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mala on 17/1/17.
 */

public interface ServiceInterface {

    @GET("/searchservice.svc/mapsearch")
    Call<MapSearchModel> getSearchListings(@Query("mode") String mode, @Query("sub") String suburb, @Query("pcode") int pcode, @Query("state") String state);
}
