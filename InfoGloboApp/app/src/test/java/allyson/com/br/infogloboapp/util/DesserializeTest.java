package allyson.com.br.infogloboapp.util;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import allyson.com.br.infogloboapp.model.Conteudo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by allys on 18/03/2017.
 */
public class DesserializeTest {

    String conteudoJson;

    @Before
    public void setUp() {
        try {
            conteudoJson = IOUtils.readStringFromResourcePath("conteudo.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void desserializeConteudo() throws Exception {
        Conteudo conteudo = Desserialize.desserializeConteudo(conteudoJson, Conteudo.class);
        assertNotNull(conteudo);
        assertEquals("O GLOBO", conteudo.getAutores().get(0));
    }

}