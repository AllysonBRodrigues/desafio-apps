package allyson.com.br.infogloboapp.apresentacao.Conteudo;

import android.os.Bundle;

import java.util.List;

import allyson.com.br.infogloboapp.data.api.Repository;
import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by allys on 18/03/2017.
 * Interfaces de comunicaçao entre a camada de apresentação e a camada de view
 */

interface ConteudoContrato {
    interface Apresentacao {
        void bind(View view, Repository repository);

        void carregarConteudo();
    }

    interface View {
        void atualizarView(List<Conteudo> conteudos);

        void erro();

        void checkInstanceState(Bundle instanceState);
    }
}
