package entidade;

import javax.swing.JOptionPane;
import util.WebServiceCep;

public class Endereço {

    private String rua, número, complemento, bairro, cidade, Cep, estado;

    public Endereço(String rua, String número, String complemento, String bairro, String cidade, String Cep, String estado) {
        this.rua = rua;
        this.número = número;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.Cep = Cep;
        this.estado = estado;
    }

    public static Endereço buscaCep(String cep) {

        try {

            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            Endereço obj = new Endereço(" ", " ", " ", " ", " ", " ", " ");

            if (webServiceCep.wasSuccessful()) {
                obj.setRua(webServiceCep.getLogradouroFull());
                obj.setCidade(webServiceCep.getCidade());
                obj.setBairro(webServiceCep.getBairro());
                obj.setEstado(webServiceCep.getUf());
                return obj;
            } else {
                JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode() + " CEP NÃO ENCONTRADO");
                //  JOptionPane.showMessageDialog(null, "Retorno Servidor de Endereços: " + webServiceCep.getResultText());

                return null;
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro numero: " + erro);
        }
        return null;
    }

    public String getLogradouro() {
        return rua;
    }

    public void setLogradouro(String logradouro) {
        this.rua = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getNúmero() {
        return número;
    }

    public void setNúmero(String número) {
        this.número = número;
    }

    public String toString() {
        String info = rua + " - " + número;

        if (complemento != null) {
            info += " - " + complemento;
        }

        info += " -Bairro: " + bairro + " - " + cidade + "\nCEP: " + Cep;

        return info;
    }
}
