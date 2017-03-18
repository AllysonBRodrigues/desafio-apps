package allyson.com.br.infogloboapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by allys on 14/03/2017.
 */

public class ConteudoResponse {
    @SerializedName("conteudos")
    @Expose
    private List<Conteudo> conteudos = null;

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }
}
