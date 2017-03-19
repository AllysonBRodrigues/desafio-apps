package allyson.com.br.infogloboapp.util;

import org.joda.time.DateTime;

/**
 * Created by Allyson Rodrigues on 19/03/2017.
 * Métodos uteis para manipulação de datas
 */

public class Datas {

    /**
     * Recebe um objeto data time, deixa no formato desejado e retorna a string formatada
     *
     * @param data objeto DateTime com a data que deseja ser formatada
     * @return retorna uma string com uma data no formato dd/MM/yyyy hh:mi
     */
    public static String formatarData(DateTime data) {
        String dia = Integer.parseInt(data.dayOfMonth().getAsString()) < 10 ? "0" + data.dayOfMonth().getAsString() : data.dayOfMonth().getAsString();
        String mes = Integer.parseInt(data.monthOfYear().getAsString()) < 10 ? "0" + data.monthOfYear().getAsString() : data.monthOfYear().getAsString();
        String hora = Integer.parseInt(String.valueOf(data.getHourOfDay())) < 10 ? "0" + String.valueOf(data.getHourOfDay()) : String.valueOf(data.getHourOfDay());
        String minuto = Integer.parseInt(String.valueOf(data.getMinuteOfHour())) < 10 ? "0" + String.valueOf(data.getMinuteOfHour()) : String.valueOf(data.getMinuteOfHour());
        return dia + "/" + mes + "/" + data.year().getAsString() + " " + hora + ":" + minuto;
    }

}
