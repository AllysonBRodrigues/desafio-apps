package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import android.os.Bundle;

import com.google.gson.Gson;

import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 */

public class ReportagemApresentacao implements ReportagemContrato.Apresentacao {

    private ReportagemContrato.View view;


    @Override
    public void bind(ReportagemContrato.View view) {
        this.view = view;
    }

    @Override
    public void carregarConteudo(Bundle bundle) {
        Gson gson = new Gson();
        Conteudo conteudo = gson.fromJson(bundle.getString("reportagem"), Conteudo.class);
        view.atualizarView(conteudo);
    }
}
