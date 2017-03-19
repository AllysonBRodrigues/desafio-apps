package allyson.com.br.infogloboapp.apresentacao.Reportagem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import allyson.com.br.infogloboapp.data.api.Repository;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.util.Desserialize;
import allyson.com.br.infogloboapp.util.IOUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by allys on 18/03/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Desserialize.class)
public class ReportagemApresentacaoTest {

    private ReportagemContrato.Apresentacao apresentacao;

    @Mock
    private ReportagemContrato.View view;

    @Mock
    private Repository repository;

    @Mock
    private Conteudo conteudo;

    String conteudoJson;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        apresentacao = new ReportagemApresentacao();
        apresentacao.bind(view);
        conteudoJson = IOUtils.readStringFromResourcePath("conteudo.json");
    }

    @Test
    public void bind() throws Exception {

    }

    @Test
    public void carregarConteudo() throws Exception {
        //setup
        PowerMockito.mockStatic(Desserialize.class);
        when(Desserialize.desserializeConteudo(conteudoJson, Conteudo.class)).thenReturn(conteudo);

        //run
        apresentacao.carregarConteudo(conteudoJson);

        //when
        verify(view).atualizarView(conteudo);
    }

}