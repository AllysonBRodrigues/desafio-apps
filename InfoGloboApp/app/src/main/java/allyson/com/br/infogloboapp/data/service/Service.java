package allyson.com.br.infogloboapp.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allys on 18/03/2017.
 */

public class Service {

    private static Gson gsonBuilder() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    private static Retrofit retrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder()))
                .build();
    }

    public static  <T> T getService(Class<T> tClass, String url) {
        return retrofitBuilder(url).create(tClass);
    }

}
