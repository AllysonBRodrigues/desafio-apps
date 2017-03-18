package allyson.com.br.infogloboapp.apresentacao.conteudo;

import android.os.Bundle;

import java.util.List;

import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by allys on 18/03/2017.
 */

public interface ConteudoContrato {
    interface Apresentacao {
        void bind(View view);

        void carregarConteudo();
    }

    interface View {
        void atualizarView(List<Conteudo> conteudos);
        void erro();
        void checkInstanceState(Bundle instanceState);
    }
}
