package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.util.Desserialize;

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
    public void carregarConteudo(String strConteudo) {
        Conteudo conteudo = Desserialize.desserializeConteudo(strConteudo, Conteudo.class);
        view.atualizarView(conteudo);
    }
}
