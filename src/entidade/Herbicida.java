package entidade;

import interfaces.JanelaVendaDefensivos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import persistencia.BD;

public class Herbicida {

    /*
    
     public static Produto buscarItem(int sequencial){
    String sql = "SELECT codigo_barras,nome, quantidade, marca,valorUnitario,valorTotal,Categoria,Comprar FROM produto WHERE Sequencial = ?";
    ResultSet lista_resultados = null;
    Produto item = null;
    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        comando.setString(1, sequencial + "");
        lista_resultados = comando.executeQuery();
        while (lista_resultados.next()) {
            item = new Produto (sequencial,
            lista_resultados.getString("codigo_barras"),
            lista_resultados.getString("nome"),
            Categoria.values()[lista_resultados.getInt("Categoria")],
            lista_resultados.getString("marca"),
            lista_resultados.getInt("quantidade"),
            lista_resultados.getFloat("valorUnitario"),
            lista_resultados.getFloat("valorTotal"),
             lista_resultados.getBoolean("Comprar"));
        }
        lista_resultados.close();
        comando.close();
    } catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        item = null;
    }
    return item;
 }
     */
    public static Herbicida buscarHerbicida(int id) {

        String sql = "SELECT nome, codigo_barras, empresa, valor_unitario, quantidade_estoque, formulacao, precisa_registro from herbicida where id = ?";
        ResultSet lista_resultados = null;
        Herbicida item = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, id + "");
            //  comando.setInt(1, id);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                item = new Herbicida(id,
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("codigo_barras"),
                        lista_resultados.getString("empresa"),
                        lista_resultados.getFloat("valor_unitario"),
                        lista_resultados.getInt("quantidade_estoque"),
                        Formulacao.values()[lista_resultados.getInt("formulacao")],
                        lista_resultados.getBoolean("precisa_registro"));
            }

            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            item = null;
        }
        return item;
    }

    //Método para dar baixar no Estoque Herbicida;
    public void baixarEstoque(int id, int qtd_novo) {
        try {
            String sql = "Update Herbicida SET quantidade_estoque = ? where id = ?";
            PreparedStatement comando = BD.conexão.prepareStatement(sql);

            comando.setInt(1, qtd_novo);
            comando.setInt(2, id);

            comando.execute();
            comando.close();
           // System.out.println("Baixa no Estoque do produto " + id + " igual a " + qtd_novo);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    public int RetornaEstoqueAtual(int id) {

        try {
            int qtd = 0;

            String sql = "Select quantidade_estoque from herbicida where id = ? ";
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, id);

            ResultSet lista_resultados = comando.executeQuery();

            if (lista_resultados.next()) {//Instanciar Herbicida;
                qtd = (lista_resultados.getInt("quantidade_estoque"));

            }
            return qtd;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return 0;
        }

    }

    public static String inserirHerbicida(Herbicida herbicida) {

        String sql = "INSERT INTO Herbicida(nome, codigo_barras, empresa, valor_unitario, quantidade_estoque, formulacao, precisa_registro)"
                + " VALUES (?,?,?,?,?,?,?)";
        String mensagem_erro = null;
        int precisa_inscricao_estadual = herbicida.getPrecisa_registro() ? 1 : 0;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, herbicida.getNome());
            comando.setString(2, herbicida.getCodig_barras());
            comando.setString(3, herbicida.getEmpresa());
            comando.setString(4, herbicida.getValor_unitario() + "");
            comando.setInt(5, herbicida.getQuantidade_estoque());;
            comando.setString(6, herbicida.getFormulacao().ordinal() + "");
            comando.setInt(7, precisa_inscricao_estadual);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            mensagem_erro = "Erro na Inserção do Herbicida no BD";
        }
        //pegar o ID do Banco
        sql = "SELECT MAX(id) AS id FROM Herbicida";
        ResultSet lista_resultados;
        int id_produto = -1;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                id_produto = lista_resultados.getInt("Id");
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            mensagem_erro = "Erro ao buscar codigo do Herbicida no banco de dados";
        }
        if (id_produto > -1) {
            herbicida.setId(id_produto);
        }
        return mensagem_erro;
    }

    public static String alterarHerbicida(Herbicida herbicida) {

        String sql = "UPDATE Herbicida SET nome = ?, codigo_barras =?, empresa = ?, valor_unitario = ?, quantidade_estoque = ?, formulacao = ?, "
                + " precisa_registro = ? WHERE id = ?";
        int precisa_registro = herbicida.getPrecisa_registro() ? 1 : 0;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, herbicida.getNome());
            comando.setString(2, herbicida.getCodig_barras());
            comando.setString(3, herbicida.getEmpresa());
            comando.setFloat(4, herbicida.getValor_unitario());
            comando.setInt(5, herbicida.getQuantidade_estoque());
            comando.setInt(6, herbicida.getFormulacao().ordinal());
            comando.setInt(7, precisa_registro);
            comando.setInt(8, herbicida.getId());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao alterar herbicida no BD.";
        }

        return null;
    }

    public static String removerHerbicida(int id) {
        String sql3 = "SELECT herbicida_id FROM tab_itemvenda WHERE herbicida_id = ?";
        ResultSet lista_resultado;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql3);
            comando.setInt(1, id);
            lista_resultado = comando.executeQuery();
            while (lista_resultado.next()) {
                if (lista_resultado.getInt("herbicida_id") == id) {
                    return "Não é possível remover, pois existe uma Venda Cadastrada com este Produto";
                }
            }
            lista_resultado.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao tentar conferir se existe vendas cadastradadas por esse CPF";
        }

        String sql = "DELETE FROM Herbicida WHERE id = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, id);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao remover Herbicida do BD.";
        }

        return null;
    }

    public static Vector<Visão<Integer>> getVisões() {
        String sql = "SELECT id, nome, valor_unitario FROM herbicida";
        ResultSet lista_resultados = null;
        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                visões.addElement(new Visão(
                        lista_resultados.getInt("id"),
                        lista_resultados.getString("id") + " - "
                        + lista_resultados.getString("nome") + " - Valor R$: "
                        + lista_resultados.getString("valor_unitario")));
            }
            lista_resultados.close();
            comando.close();

        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
        return visões;
    }

    public static List<Herbicida> BuscarHerbicidaPorNome(String nome) {

        List<Herbicida> lista = new ArrayList<>();
        String sql = "Select * from herbicida where nome like ? ";

        Herbicida herbicida = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, nome);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                herbicida = new Herbicida(
                        lista_resultados.getInt("id"),
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("codigo_barras"),
                        lista_resultados.getString("empresa"),
                        lista_resultados.getFloat("valor_unitario"),
                        lista_resultados.getInt("quantidade_estoque"),
                        Formulacao.values()[lista_resultados.getInt("formulacao")],
                        lista_resultados.getBoolean("precisa_registro"));
                lista.add(herbicida);
            }
            lista_resultados.close();
            comando.close();
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public static List<Herbicida> listarHerbicida() {

        List<Herbicida> lista = new ArrayList<>();
        String sql = "SELECT * from herbicida";
        Herbicida herbicida = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                herbicida = new Herbicida(
                        lista_resultados.getInt("id"),
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("codigo_barras"),
                        lista_resultados.getString("empresa"),
                        lista_resultados.getFloat("valor_unitario"),
                        lista_resultados.getInt("quantidade_estoque"),
                        Formulacao.values()[lista_resultados.getInt("formulacao")],
                        lista_resultados.getBoolean("precisa_registro"));
                lista.add(herbicida);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public enum Formulacao {
        Po, Liquido, Granulado;
    };

    public static final Formulacao[] formulacoes = Formulacao.values();

    private Formulacao formulacao;
    private int id, quantidade_estoque;
    private String nome, codig_barras, empresa;
    private float valor_unitario;
    private boolean precisa_registro;

    ;
  
    //Construtor
    public Herbicida(int id, String nome, String codigo_barras, String empresa, float valor_unitario, int quantidade_estoque, Formulacao formulacao, boolean precisa_registro) {
        this.id = id;
        this.nome = nome;
        this.codig_barras = codigo_barras;
        this.empresa = empresa;
        this.valor_unitario = valor_unitario;
        this.quantidade_estoque = quantidade_estoque;
        this.formulacao = formulacao;
        this.precisa_registro = precisa_registro;
    }

    @Override
    public String toString() {
        return "Herbicida{" + "id=" + id + ", quantidade_estoque=" + quantidade_estoque + ", nome=" + nome + ", codig_barras=" + codig_barras + ", empresa=" + empresa + ", formulacao=" + formulacao + ", precisa_registro=" + precisa_registro + ", valor_unitario=" + valor_unitario + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodig_barras(String codig_barras) {
        this.codig_barras = codig_barras;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setFormulacao(Formulacao formulacao) {
        this.formulacao = formulacao;
    }

    public void setPrecisa_registro(boolean precisa_registro) {
        this.precisa_registro = precisa_registro;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public String getNome() {
        return nome;
    }

    public String getCodig_barras() {
        return codig_barras;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Formulacao getFormulacao() {
        return formulacao;
    }

    public boolean getPrecisa_registro() {
        return precisa_registro;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public Visão<Integer> getVisão() {
        return new Visão<Integer>(id, id + " - " + nome);
    }

}
