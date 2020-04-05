package util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data implements Serializable {
    
public static Data toData(String data_string) {
    if (!data_string.matches("\\d{4}-\\d{2}-\\d{2}")) {
        System.out.println("Erro na conversão para data do string: " + data_string);
        return null;
    }
String[] partesString = data_string.split("-");
return new Data(Integer.parseInt (partesString[2]), Integer.parseInt (partesString[1]), Integer.parseInt (partesString[0]));
}
    
    private int dia, mês, ano;

    public Data(int dia, int mês, int ano) {
        this.dia = dia;
        this.mês = mês;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMês() {
        return mês;
    }

    public int getAno() {
        return ano;
    }

    public int calcularIdade() {
        GregorianCalendar data_atualGregorianCalendar = new GregorianCalendar();
        int dia_atual = data_atualGregorianCalendar.get(Calendar.DAY_OF_MONTH);
        int mês_atual = data_atualGregorianCalendar.get(Calendar.MONTH) + 1;
        // GregorianCalendar: mês varia de 0 a 11
        int ano_atual = data_atualGregorianCalendar.get(Calendar.YEAR);
        int idade = ano_atual - ano;
        if ((mês_atual < mês) || ((mês_atual == mês) && (dia_atual < dia))) {
            idade--;
        }
        return idade;
    }
        public static String DataHora(){
        Date data = new Date();
        String data_hora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
        String diasemana = "";
        switch(data.getDay()){
            case 0:
                diasemana = "Domingo";
                break;
            case 1:
                diasemana = "Segunda-Feira";
                break;
            case 2:
                diasemana = "Terça-Feira";
                break;
            case 3:
                diasemana = "Quarta-Feira";
                break;
            case 4:
                diasemana = "Quinta-Feira";
                break;
            case 5:
                diasemana = "Sexta_feira";
                break;
            case 6:
                diasemana = "Sabado";
                break;
            default:
                break;
        }
        
        return diasemana + " "+ data_hora;  
    }

    public int compareTo(Data data) {
        if (ano > data.getAno()) {
            return 1;
        }
        if (ano < data.getAno()) {
            return -1;
        }
        if (mês > data.getMês()) {
            return 1;
        }
        if (mês < data.getMês()) {
            return -1;
        }
        if (dia > data.getDia()) {
            return 1;
        }
        if (dia < data.getDia()) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        String data;
        if (dia < 10) {
            data = "0" + dia;
        } else {
            data = "" + dia;
        }
        if (mês < 10) {
            data += "/0" + mês + "/";
        } else {
            data += "/" + mês + "/";
        }
        return data += ano;
    }

}

