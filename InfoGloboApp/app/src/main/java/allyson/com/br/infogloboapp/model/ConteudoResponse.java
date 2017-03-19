package allyson.com.br.infogloboapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Allyson Rodrigues on 14/03/2017.
 * Objeto ConteudoResponse
 */

public class ConteudoResponse {
    @SerializedName("conteudos")
    @Expose
    private List<Conteudo> conteudos = null;
    @SerializedName("produto")
    @Expose
    private String produto;

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
