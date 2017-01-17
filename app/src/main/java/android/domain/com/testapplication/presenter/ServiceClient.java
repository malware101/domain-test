package android.domain.com.testapplication.presenter;

import android.domain.com.testapplication.model.MapSearchModel;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mala on 17/1/17.
 */

public class ServiceClient {

    private static final String ROOT_URL = "https://rest.domain.com.au";

    private static ServiceInterface mServiceInterface;

    private static ServiceClient mInstance;

    public static ServiceClient getInstance() {
        if (mInstance == null) {
            initialiseAPI();
        }
        return mInstance;
    }

    public static void initialiseAPI() {
        mInstance = new ServiceClient();
    }

    private ServiceClient() {
        // Initialise Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mServiceInterface = retrofit.create(ServiceInterface.class);
    }

    /**
     * Gets search listing results and returns result in callback
     *
     * @param callback
     */
    public void getSearchListings(final Callback<MapSearchModel> callback) {
        Call<MapSearchModel> call = mServiceInterface.getSearchListings("buy", "Bondi", 2026, "NSW");
        call.enqueue(new Callback<MapSearchModel>() {
            @Override
            public void onResponse(Call<MapSearchModel> call, Response<MapSearchModel> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, null);
                }
            }

            @Override
            public void onFailure(Call<MapSearchModel> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
