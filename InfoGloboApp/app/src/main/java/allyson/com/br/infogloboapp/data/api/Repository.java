package allyson.com.br.infogloboapp.data.api;

import java.util.List;

import allyson.com.br.infogloboapp.model.ConteudoResponse;
import retrofit2.Callback;

/**
 * Created by allys on 18/03/2017.
 */

public interface Repository {

    void requestConteudos(Callback<List<ConteudoResponse>> callback);
    //
}
