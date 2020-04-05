package entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import persistencia.BD;
import util.Data;


public class Venda{

public int ultima_venda;    

 public static String inserirVenda(Venda venda){
     
     try {
     String sql= "Insert into tab_vendas(cliente_id, forma_pagamento, data_venda, valor_produtos, valor_desconto, valor_total, precisa_nfe) values (?,?,?,?,?,?,?)";
     PreparedStatement comando = BD.conexão.prepareStatement(sql);
     comando.setString(1, venda.getCpf_cliente());
     comando.setString(2, venda.getPagamento_tipo().ordinal() + "");
     comando.setString(3, venda.getData_venda());
     comando.setFloat(4, venda.getValor_produtos());
     comando.setFloat(5, venda.getValor_desconto());
     comando.setFloat(6, venda.getValor_total());
     comando.setBoolean(7, venda.getPrecisa_nfe());

     comando.execute();
     comando.close();

             JOptionPane.showMessageDialog(null, "Venda Registrada com Sucesso");
     } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null, "Erro: " + erro);
     }
     
    return null; 
  }
 
//Pegar Id da ultima Venda
 public int RetornaUltimaVenda(){
    
        int id_venda = -1;
        try{
            String sql3 = "SELECT MAX(id_venda) as id_venda FROM tab_vendas";
            ResultSet lista_resultados = null;
            PreparedStatement comando = BD.conexão.prepareStatement(sql3);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                id_venda = lista_resultados.getInt("id_venda");  
            }
            lista_resultados.close();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return -1;
        }
        ultima_venda = id_venda;
   
        return id_venda;
 }
 
  
    public static String RemoverVenda(int Codigovenda) {
       String sql1 = "DELETE FROM tab_itemvenda Where venda_id = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql1);
            comando.setInt(1, Codigovenda);
            comando.execute();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();     
        } 
        
        String sq2 = "DELETE FROM tab_vendas WHERE id_venda = ? ";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sq2);
            comando.setInt(1, Codigovenda);
            comando.execute();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro na Remoção da Venda no BD";
        } 
       return null;
    }
    
    public static Venda buscarVenda(int sequencial) {
        String sql = "SELECT cliente_id, data_venda,forma_pagamento, valor_produtos, valor_desconto, valor_total, precisa_nfe from tab_vendas where id_venda = ?";
        ResultSet lista_resultados = null;
        Venda venda = null;
        
        
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial);
            lista_resultados = comando.executeQuery();
       
            while (lista_resultados.next()) {
                venda = new Venda(sequencial,
                        lista_resultados.getString("cliente_id"),
                        lista_resultados.getString("data_venda"),
                        Venda.PagamentoTipo.values()[lista_resultados.getInt("forma_pagamento")],   
                        lista_resultados.getFloat("valor_produtos"),
                        lista_resultados.getFloat("valor_desconto"),
                        lista_resultados.getFloat("valor_total"),
                        lista_resultados.getBoolean("precisa_nfe"));
            }

            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            venda = null;
        }
        return venda;
    }

 
    public static String alterarVenda(Venda venda) {
        String sql = "UPDATE tab_vendas SET Cliente_id = ?, valor_desconto = ?, precisa_nfe = ? ";
              
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, venda.getCpf_cliente());
            comando.setFloat(2, venda.getValor_desconto());
            comando.setBoolean(3, venda.getPrecisa_nfe());

            comando.executeUpdate();
            comando.close();
            return null;
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro na Alteração do Cliente no BD";
        }
    }
    

    public static Vector<Visão<Integer>> getVisões() {
        String sql = "SELECT id_venda, cliente_id, data_venda, forma_pagamento, valor_produtos, valor_desconto, valor_total, precisa_nfe FROM tab_vendas ";

        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();
        ResultSet lista_resultados = null;
        int sequencial;
        String precisa_nfe1 = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                if(lista_resultados.getBoolean("precisa_nfe") == true){
                    precisa_nfe1= "Impresso nota Fiscal";
                }else{
                    precisa_nfe1= "Sem Nota Fiscal";
                } 
                String aux_data = lista_resultados.getString(String.valueOf("data_venda"));
                Data data = Data.toData(aux_data);
                String codigo_venda = lista_resultados.getString(String.valueOf("id_venda"));
                visões.addElement(new Visão<Integer>(
                        lista_resultados.getInt("id_venda"), 
                        codigo_venda + ": " 
                        + "         Cliente:  " + lista_resultados.getString("cliente_id") 
                        + "             Data:  " + data
                        + "            Desconto:  " + lista_resultados.getFloat("valor_desconto")
                        + "            Valor_total:  " + lista_resultados.getFloat("valor_total")
                        + "              Precisa Nfe:  " + precisa_nfe1
                ));
            }
            lista_resultados.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
        return visões;
    }

    public enum PagamentoTipo {

        Dinheiro, Cartao_credito, Cartao_Debito
    };

    private int sequencial;
    private String cpf_cliente;
    private String data_venda;
    PagamentoTipo pagamento_tipo;
    private float valor_total, valor_desconto, valor_produtos;
    private boolean precisa_nfe;
  //  public TipoPagamento tipo_pagamento;
   // public PrazoPagamento prazo_pagamento;

    public Venda(int sequencial, String cpf_cliente, String data_venda, PagamentoTipo pagamento_tipo,
            float valor_produtos, float valor_desconto, float valor_total, boolean precisa_nfe) {

        this.sequencial = sequencial;
        this.cpf_cliente = cpf_cliente;
        this.data_venda = data_venda;
        this.pagamento_tipo = pagamento_tipo;
        this.valor_total = valor_total;
        this.valor_desconto = valor_desconto;
        this.valor_produtos = valor_produtos;
        this.precisa_nfe = precisa_nfe;
     //   this.tipo_pagamento = tipo_pagamento;
       // this.prazo_pagamento = prazo_pagamento;

    }

    @Override
    public String toString() {
        return "Venda{" + "sequencial=" + sequencial + ", cpf_cliente=" + cpf_cliente + ", data_venda=" + data_venda + ", pagamento_tipo=" + pagamento_tipo + ", valor_total=" + valor_total + ", valor_desconto=" + valor_desconto + ", valor_produtos=" + valor_produtos + ", precisa_nfe=" + precisa_nfe + '}';
    }


    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public void setPrecisa_nfe(boolean precisa_nfe) {
        this.precisa_nfe = precisa_nfe;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public void setPagamento_tipo(PagamentoTipo pagamento_tipo) {
        this.pagamento_tipo = pagamento_tipo;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public void setValor_desconto(float valor_desconto) {
        this.valor_desconto = valor_desconto;
    }

    public void setValor_produtos(float valor_produtos) {
        this.valor_produtos = valor_produtos;
    }

    public int getSequencial() {
        return sequencial;
    }

    public boolean getPrecisa_nfe() {
        return precisa_nfe;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public String getData_venda() {
        return data_venda;
    }

    public PagamentoTipo getPagamento_tipo() {
        return pagamento_tipo;
    }

    public float getValor_total() {
        return valor_total;
    }

    public float getValor_desconto() {
        return valor_desconto;
    }

    public float getValor_produtos() {
        return valor_produtos;
    }



    public Visão<Integer> getVisão() {
        return new Visão<Integer>(sequencial, cpf_cliente + " - (" + valor_total + ")");
    }

}
