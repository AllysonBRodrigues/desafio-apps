package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 * Interfaces de comunicaçao entre a camada de apresentação e a camada de view
 */

interface ReportagemContrato {

    interface Apresentacao {
        void bind(View view);

        void carregarConteudo(String strConteudo);
    }

    interface View {
        void atualizarView(Conteudo conteudos);
    }


}
