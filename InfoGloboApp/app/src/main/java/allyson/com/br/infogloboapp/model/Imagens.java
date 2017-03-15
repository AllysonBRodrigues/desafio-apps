package allyson.com.br.infogloboapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Allyson Rodrigues on 14/03/2017.
 */

public class Imagens implements Serializable {

    @SerializedName("autor")
    private String autor;
    @SerializedName("fonte")
    private String fonte;
    @SerializedName("legenda")
    private String legenda;
    @SerializedName("url")
    private String url;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
