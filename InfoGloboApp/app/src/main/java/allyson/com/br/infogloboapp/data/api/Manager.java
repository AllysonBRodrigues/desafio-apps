package allyson.com.br.infogloboapp.data.api;

import java.util.List;

import allyson.com.br.infogloboapp.data.service.IConteudos;
import allyson.com.br.infogloboapp.data.service.Service;
import allyson.com.br.infogloboapp.model.ConteudoResponse;
import allyson.com.br.infogloboapp.util.Url;
import retrofit2.Callback;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 * Implementação da interface Repository para comunicação com o webservice
 */

public class Manager implements Repository {
    @Override
    public void requestConteudos(Callback<List<ConteudoResponse>> callback) {
        Service.getService(IConteudos.class, Url.PRINCIPAL_URL).getConteudos().enqueue(callback);
    }
}
