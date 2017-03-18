package allyson.com.br.infogloboapp.data.api;

import java.util.List;

import allyson.com.br.infogloboapp.model.Url;
import allyson.com.br.infogloboapp.data.service.Service;
import allyson.com.br.infogloboapp.data.service.IConteudos;
import allyson.com.br.infogloboapp.model.ConteudoResponse;
import retrofit2.Callback;

/**
 * Created by allys on 18/03/2017.
 */

public class Manager implements Repository {
    @Override
    public void requestConteudos(Callback<List<ConteudoResponse>> callback) {
        Service.getService(IConteudos.class, Url.PRINCIPAL_URL).getConteudos().enqueue(callback);
    }
}
