package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import android.os.Bundle;

import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by allys on 18/03/2017.
 */

public interface ReportagemContrato {

    interface Apresentacao {
        void bind(View view);
        void carregarConteudo(String strConteudo);
    }

    interface View {
        void atualizarView(Conteudo conteudos);
    }


}
