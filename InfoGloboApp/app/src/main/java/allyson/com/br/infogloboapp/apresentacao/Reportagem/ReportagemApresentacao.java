package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.util.Desserialize;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 * Implementação da camada de apresentação
 */

class ReportagemApresentacao implements ReportagemContrato.Apresentacao {

    private ReportagemContrato.View view;


    @Override
    public void bind(ReportagemContrato.View view) {
        this.view = view;
    }

    /**
     * Recebe um string json que contem o conteudo da reportagem desserializa e envia novamente para
     * a tela
     *
     * @param strConteudo string serializada json
     */
    @Override
    public void carregarConteudo(String strConteudo) {
        Conteudo conteudo = Desserialize.desserializeConteudo(strConteudo, Conteudo.class);
        view.atualizarView(conteudo);
    }
}
