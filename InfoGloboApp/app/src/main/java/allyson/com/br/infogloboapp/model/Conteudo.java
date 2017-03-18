package allyson.com.br.infogloboapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by allys on 14/03/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conteudo {

    @SerializedName("autores")
    @Expose
    private List<String> autores = null;
    @SerializedName("informePublicitario")
    @Expose
    private Boolean informePublicitario;
    @SerializedName("subTitulo")
    @Expose
    private String subTitulo;
    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("videos")
    @Expose
    private List<Object> videos = null;
    @SerializedName("atualizadoEm")
    @Expose
    private Date atualizadoEm;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("publicadoEm")
    @Expose
    private Date publicadoEm;
    @SerializedName("secao")
    @Expose
    private Secao secao;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlOriginal")
    @Expose
    private String urlOriginal;
    @SerializedName("imagens")
    @Expose
    private List<Imagens> imagens = null;


    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public boolean isInformePublicitario() {
        return informePublicitario;
    }

    public void setInformePublicitario(boolean informePublicitario) {
        this.informePublicitario = informePublicitario;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    public Date getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Date atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublicadoEm() {
        return publicadoEm;
    }

    public void setPublicadoEm(Date publicadoEm) {
        this.publicadoEm = publicadoEm;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public List<Imagens> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagens> imagens) {
        this.imagens = imagens;
    }
}
