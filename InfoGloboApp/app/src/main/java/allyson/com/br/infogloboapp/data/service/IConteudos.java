package allyson.com.br.infogloboapp.data.service;

 /**
 * Created by Allyson Rodrigues on 14/03/2017.
 * Interface de medotos do retrofit
 */

import java.util.List;

import allyson.com.br.infogloboapp.model.ConteudoResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IConteudos {

    @GET("capa.json")
    Call<List<ConteudoResponse>> getConteudos();

}
