package allyson.com.br.infogloboapp.apresentacao.conteudo;

import java.util.List;

import allyson.com.br.infogloboapp.data.api.Repository;
import allyson.com.br.infogloboapp.model.ConteudoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allys on 18/03/2017.
 */

public class ConteudoApresentacao implements ConteudoContrato.Apresentacao {
    ConteudoContrato.View view;
    Repository manager;

    @Override
    public void bind(ConteudoContrato.View view, Repository manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public void carregarConteudo() {
        manager.requestConteudos(new Callback<List<ConteudoResponse>>() {
            @Override
            public void onResponse(Call<List<ConteudoResponse>> call, Response<List<ConteudoResponse>> response) {
                view.atualizarView(response.body().get(0).getConteudos());
            }

            @Override
            public void onFailure(Call<List<ConteudoResponse>> call, Throwable t) {
                t.printStackTrace();
                view.erro();
            }
        });
    }
}
