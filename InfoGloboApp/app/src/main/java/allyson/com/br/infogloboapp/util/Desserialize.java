package allyson.com.br.infogloboapp.util;

import com.google.gson.Gson;

/**
 * Created by Allyson Rodrigues on 18/03/2017.
 * Classe para metodos de desserialização
 */

public class Desserialize {

    /**
     * Desserializa uma string json para a quese que for informada
     *
     * @param serilized string json para ser desserializada
     * @param tClass    classe para qual se deseja a desserialização
     * @param <T>       classe para qual se deseja a desserialização
     * @return classe informada poavoada com o conteudo que foi informado na string serilized
     */
    public static <T> T desserializeConteudo(String serilized, Class<T> tClass) {
        return new Gson().fromJson(serilized, tClass);
    }
}
