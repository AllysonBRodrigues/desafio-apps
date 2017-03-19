package allyson.com.br.infogloboapp.util;

import com.google.gson.Gson;

/**
 * Created by allys on 18/03/2017.
 */

public class Desserialize {
    public static <T> T desserializeConteudo(String serilized,Class<T> tClass) {
        return new Gson().fromJson(serilized, tClass);
    }
}
