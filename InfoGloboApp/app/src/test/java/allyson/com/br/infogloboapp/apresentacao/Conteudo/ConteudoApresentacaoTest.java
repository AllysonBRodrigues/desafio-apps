package allyson.com.br.infogloboapp.apresentacao.Conteudo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import allyson.com.br.infogloboapp.data.api.Repository;
import allyson.com.br.infogloboapp.model.ConteudoResponse;
import allyson.com.br.infogloboapp.util.IOUtils;
import retrofit2.Callback;

import static org.mockito.Mockito.verify;

/**
 * Created by allys on 18/03/2017.
 */
public class ConteudoApresentacaoTest {
    @Mock
    private ConteudoContrato.View view;
    @Mock
    private Repository manager;

    private ConteudoContrato.Apresentacao apresentacao;

    @Mock
    private Callback<List<ConteudoResponse>> cb;

    @Mock
    private List<ConteudoResponse> response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        apresentacao = new ConteudoApresentacao();
        apresentacao.bind(view, manager);

    }


    @Test
    public void bindNotNull() throws Exception {
        Assert.assertNotNull(((ConteudoApresentacao) apresentacao).view);
        Assert.assertNotNull(((ConteudoApresentacao) apresentacao).manager);
    }

    @Test
    public void carregarConteudoError() throws Exception {
        final Type listType = new TypeToken<ArrayList<ConteudoResponse>>() {
        }.getType();
        final List<ConteudoResponse> response1 = new Gson().fromJson(IOUtils.readStringFromResourcePath("response.json"), listType);

        Mockito.doAnswer(new Answer<List<ConteudoResponse>>() {
            @Override
            public List<ConteudoResponse> answer(InvocationOnMock invocation) throws Throwable {
                view.atualizarView(response1.get(0).getConteudos());
                return response1;
            }
        }).when(manager).requestConteudos(cb);

        manager.requestConteudos(cb);

        verify(view).atualizarView(response1.get(0).getConteudos());
    }

}