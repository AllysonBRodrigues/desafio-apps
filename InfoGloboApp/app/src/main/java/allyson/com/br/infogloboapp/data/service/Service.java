package allyson.com.br.infogloboapp.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 * Serviço de comunicação com o retrofit
 */

public class Service {

    /**
     * Configuração do formato de data para desserialização do Gson
     * @return retorna o Gson com a configuração de data
     */
    private static Gson gsonBuilder() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    /**
     * Criação do Builder do retrofit para comunicação com o webservice
     * @param url URL base de comunicação do webservice
     * @return retorna um objeto retrofit para comunicação
     */
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
