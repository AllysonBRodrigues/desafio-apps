package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import android.os.Bundle;

import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by allys on 18/03/2017.
 */

public interface ReportagemContrato {

    interface Apresentacao {
        void bind(View view);
        void carregarConteudo(Bundle bundle);
    }

    interface View {
        void atualizarView(Conteudo conteudos);
        void erro();
    }


}
