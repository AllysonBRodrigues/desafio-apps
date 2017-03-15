package allyson.com.br.infogloboapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by allys on 14/03/2017.
 */

public class Secao implements Serializable {

    @SerializedName("nome")
    private String nome;
    @SerializedName("url")
    private String url;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
