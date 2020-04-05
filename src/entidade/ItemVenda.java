package entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.BD;
import util.Data;

public class ItemVenda {

    private int id_itemVenda;
    private Venda venda;
    private Herbicida herbicida;
    private int quantidade;
    private float subtotal;
    private float valor_unitario;

    public void setId_itemVenda(int id_itemVenda) {
        this.id_itemVenda = id_itemVenda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setHerbicida(Herbicida herbicida) {
        this.herbicida = herbicida;
    }

    public Venda getVenda() {
        return venda;
    }

    public Herbicida getHerbicida() {
        return herbicida;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getId_itemVenda() {
        return id_itemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public String inserirItem(ItemVenda item_venda) {

        String sql = "INSERT INTO tab_Itemvenda(venda_id, herbicida_id, valor_unitario, quantidade, subtotal) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, item_venda.getVenda().getSequencial());
            comando.setInt(2, item_venda.getHerbicida().getId());
            comando.setFloat(3, (float) item_venda.getValor_unitario());
            comando.setInt(4, item_venda.getQuantidade());
            comando.setFloat(5, (float) item_venda.getSubtotal());

            comando.execute();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao inserir estoque no banco de dados";
        }
        return null;
    }

    public static ItemVenda consultarItemVenda(int sequencial_item) {

        String sql = "SELECT venda_id, herbicida_id, quantidade, valor_unitario, valor_totalItem FROM Item_venda WHERE Sequencial = ?";

        int venda = -1, sequencial_herbicidas = 0, quantidade = -1, precisa_nfe = -1;
        float valor_unitario = 0, valor_totalItem = 0;
        Herbicida herbicida;
        ResultSet lista_resultados;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, sequencial_item + "");
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                venda = lista_resultados.getInt("venda_id");

                sequencial_herbicidas = lista_resultados.getInt("herbicida_id");
                quantidade = lista_resultados.getInt("quantidade");
                valor_unitario = lista_resultados.getFloat("valor_unitario");
                valor_totalItem = lista_resultados.getFloat("valor_totalItem");
                precisa_nfe = lista_resultados.getInt("precisa_nfe");
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();

        }
        return null;

    }

    public static Vector<Visão<Integer>> getVisõesFiltradas(int pagamento, String sexo_cliente, int formulacao) {
        String sql = " select tab_vendas.id_venda, tab_vendas.forma_pagamento as Forma_pagamento, clientes.Nome,  "
                + "clientes.Sexo, herbicida.formulacao, group_concat(' ', herbicida.nome, ' Formulacao: ', Case herbicida.formulacao When '1' Then 'Po' When '2' Then 'Liquido' When '3' Then 'Granulado' End) as produtos_venda, "
                + "tab_vendas.valor_total, tab_vendas.precisa_nfe from tab_vendas, herbicida, clientes, tab_itemvenda where tab_itemvenda.venda_id =  tab_vendas.id_venda  And herbicida.id = tab_itemvenda.herbicida_id and Clientes.Cpf = Tab_vendas.cliente_id ";

        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();

        ResultSet lista_resultados;

        String formulacao_tipo = null;
        String mensagem_nfe = null;
        String mensagem_categoria = null;

        if (pagamento > 0) {
            sql += " AND tab_vendas.forma_pagamento = " + pagamento;
        }

        if (formulacao > 0) {
            sql += " AND herbicida.formulacao = " + formulacao + "";
        }

        if (!sexo_cliente.equals("x")) {
            sql += " AND clientes.sexo = '" + sexo_cliente + "'";
        }

        sql += "  Group by tab_vendas.id_venda ";
        try {
            int i = 0;
            try (PreparedStatement comando = BD.conexão.prepareStatement(sql)) {
                lista_resultados = comando.executeQuery();
                while (lista_resultados.next()) {
                    String sexo_cliente1;
                    if (lista_resultados.getString("clientes.Sexo").equals("f")) {
                        sexo_cliente1 = "Sexo: Feminino";
                    } else {
                        sexo_cliente1 = "Sexo: Masculino";
                    }
                    //verifica se precisa nfe
                    if (lista_resultados.getInt("tab_vendas.precisa_nfe") == 0) {
                        mensagem_nfe = " Com Emissão de Nota Fiscal ";
                    } else {
                        mensagem_nfe = " Sem Emissao de Nota Fiscal ";
                    }
                    if (lista_resultados.getString("herbicida.formulacao").equals("0")) {
                        mensagem_categoria = "Formulacao em Po";
                    }
                    if (lista_resultados.getString("herbicida.formulacao").equals("1")) {
                        mensagem_categoria = "Formulacao Liquida";
                    }
                    if (lista_resultados.getString("herbicida.formulacao").equals("2")) {
                        mensagem_categoria = "Formulacoa modo granulado";
                    }

                    if (lista_resultados.getString("tab_vendas.forma_pagamento").equals("1")) {
                        mensagem_categoria = "Pagamento em Dinheiro";
                    }
                    if (lista_resultados.getString("tab_vendas.forma_pagamento").equals("2")) {
                        mensagem_categoria = "Pagamento em Cartao_Credito";
                    }
                    if (lista_resultados.getString("tab_vendas.forma_pagamento").equals("3")) {
                        mensagem_categoria = "Pagamento em Cartao_Debito";
                    }
                    i++;
                    visões.addElement(new Visão<Integer>(
                            lista_resultados.getInt("tab_vendas.id_venda"),
                            " [ " + i + " ]" + "     VENDA Nº : "
                            + lista_resultados.getString("tab_vendas.id_venda") + " - "
                            + mensagem_categoria + " -  CLIENTE: "
                            + lista_resultados.getString("clientes.Nome") + "   -   "
                            + sexo_cliente1 + " - "
                            + "" + " HERBICIDAS ADQUIRIDOS: [ "
                            + lista_resultados.getString("produtos_venda") + " ]   -   "
                            //  + lista_resultados.getString("precisa_nfe")
                            //   + lista_resultados.getString("tab_itemvenda.valor_unitario") + " - "
                            + " TOTAL VENDA R$: "
                            + lista_resultados.getString("tab_vendas.valor_total")));
                          //  + mensagem_nfe));
                }
                lista_resultados.close();
                comando.close();
            }
        } catch (SQLException exceção_sql) {
            JOptionPane.showMessageDialog(null, "erro: " + exceção_sql);
        }

        return visões;

    }
}
