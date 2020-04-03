
package entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import persistencia.BD;


public class VendaMudanca {

    private int sequencial;
    private Herbicida herbicida;
    private int cod_venda;
    private int quantidade;
    private float valor_unitario;
    private float valor_total;
    
    public VendaMudanca(int sequencial, int cod_venda, Herbicida herbicida, int quantidade,
            float valor_unitario, float valor_total)
    { 
        this.sequencial = sequencial;
        this.cod_venda= cod_venda;
        this.herbicida= herbicida;
        this.quantidade = quantidade;
        this.valor_unitario = valor_unitario;
        this.valor_total = valor_total;
        
    }

    public int getSequencial() {
        return sequencial;
    }

    public Herbicida getHerbicida() {
        return herbicida;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public void setHerbicida(Herbicida herbicida) {
        this.herbicida = herbicida;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }
    
    
  
public Visão<Integer> getVisão() {
        return new Visão<Integer>(sequencial, sequencial + " - "+ herbicida.getNome()+ " - " + String.valueOf(valor_total));
              
    }
    
    //Inserir Item da Venda
    public static String inserirEstoque(VendaMudanca item_venda) {
        String sql = "INSERT INTO Item_venda(id_venda, id_herbicida, quantidade, valor_unitario, valor_total) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, item_venda.getCod_venda() + "");
            comando.setString(2, item_venda.getHerbicida().getId() + "");
            comando.setInt(3, item_venda.getQuantidade());
            comando.setFloat(4, item_venda.getValor_unitario());
            comando.setFloat(5, item_venda.getValor_total());
            
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao inserir estoque no banco de dados";
        }
        //pegar o ID no Banco
        sql = "SELECT MAX(Sequencial) AS Sequencial FROM item_venda";
        ResultSet lista_resultados;
        int sequencial_item = -1;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                sequencial_item = lista_resultados.getInt("Sequencial");
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao buscar sequencial da estoque no banco de dados";
        }
        if (sequencial_item > -1) {
            item_venda.setSequencial(sequencial_item);
        }

        return null;
    }
    
    public static VendaMudanca consultarItemVenda(int sequencial_item) {
        
       String sql = "SELECT venda_id, herbicida_id, quantidade, valor_unitario, valor_totalItem FROM Item_venda WHERE Sequencial = ?";
       
        int venda =-1, sequencial_herbicidas = 0, quantidade =-1, precisa_nfe = -1;
        float valor_unitario = 0, valor_totalItem= 0;
      
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
            return null;
        }
        //Verifica se o produto e o fornecedor existe no BD
        if((venda==-1 || sequencial_herbicidas==-1))return null;
      
        //Pegando Produto
        sql = "SELECT codigo_barras,nome, empresa, quantidade_estoque, formulacao, precisa_registro FROM herbicida WHERE Sequencial = ?";
        Herbicida herbicida = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, sequencial_herbicidas + "");
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                herbicida = new Herbicida (sequencial_herbicidas,
                lista_resultados.getString("nome"),
                lista_resultados.getString("codigo_barras"),
                lista_resultados.getString("empresa"),
                lista_resultados.getFloat("valor_unitario"),
                lista_resultados.getInt("quantidade_estoque"),
                Herbicida.Formulacao.values()[lista_resultados.getInt("Formulacao")],
                lista_resultados.getBoolean("precisa_registro"));
            }
           /*     public ItemVenda(int sequencial, int cod_venda, Herbicida herbicida, int quantidade,
            float valor_unitario, float valor_total)
            */
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return null;
        }
        
         
        return new VendaMudanca(sequencial_item, venda, herbicida, quantidade, valor_unitario, valor_totalItem);
    }
    
    /*
    public static String alterarEstoque(ItemVenda item_venda) {
        //Busca se o herbicidajá está cadastrado no estoque
        String sql1 = "SELECT id from herbicida WHERE id = ?";
        int sequencial_herbicidas = -1;
        ResultSet lista_resultados;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql1);
            comando.setString(1, item_venda.getHerbicida().getId()+ "");
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                sequencial_herbicidas = lista_resultados.getInt("Id");
            }
            lista_resultados.close();   
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return null;
        }
        
        if(sequencial_herbicidas!=-1)return "Herbicida Não cadastrado";

        
        String sql = "UPDATE item_venda SET HerbicidaId = ?,FornecedorID = ? "
                + "WHERE Sequencial = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, estoque.getHerbicida().getSequencial()+"");
            comando.setString(2, estoque.getFornecedor().getCnpj()); 
            comando.setString(3, estoque.getSequencial() + "");
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao tentar alterar estoque no banco de dados";
        }
        return null;
    }
    */
    public static String removerItemVenda(int sequencial_item) {
        String sql = "DELETE FROM item_venda WHERE Sequencial = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, sequencial_item + "");
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao tentar excluir estoque do banco de dados";
        }
        return null;
    }
    
    //Verifica se o herbicidanão está em nenhum estoque
    public static Boolean foiEstocado(int sequencial_herbicida) {
        String sql = "SELECT HerbicidaId FROM estoque";
        ResultSet lista_resultados;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                if (lista_resultados.getInt("HerbicidaId") == sequencial_herbicida) {
                    return true;
                }
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
        return false;
    }
    
    
    
    //Método GetVisao
    public static Vector<Visão<Integer>> getVisões() {   
        String sql = "SELECT estoque.Sequencial, estoque.HerbicidaId,estoque.FornecedorId,"+
                " cliente.cnpj, cliente.nome," + 
                " herbicida.Sequencial, herbicida.codigo_barras, herbicida.nome"+
                " FROM estoque,cliente,herbicida"+
                " WHERE estoque.HerbicidaId = herbicida.Sequencial AND estoque.FornecedorId = cliente.cnpj";
        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                
                visões.addElement(new Visão<Integer>(
                        lista_resultados.getInt("estoque.Sequencial"),
                        + lista_resultados.getInt("estoque.Sequencial") + " - "
                        + lista_resultados.getString("cliente.cnpj") + " - "
                        + lista_resultados.getString("cliente.nome")     
                        + " Fornece o herbicidacom ID " + lista_resultados.getString("herbicida.Sequencial") + " - "
                        + lista_resultados.getString("herbicida.nome") 
                ));
            }
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
        return visões;
    }
    
    
    //metodo do filtro
    public static Vector<Visão<Integer>> getVisõesFiltradas(int comprar, int categoria, String nome_cliente, int contrato){
        String sql = "SELECT herbicida.Sequencial, herbicida.codigo_barras, herbicida.nome, herbicida.quantidade, herbicida.marca, herbicida.valorUnitario, herbicida.valorTotal, herbicida.Categoria, herbicida.Comprar," + 
                " cliente.cnpj, cliente.nome, cliente.tipoContrato,"+
                " estoque.Sequencial, estoque.HerbicidaId, estoque.FornecedorId"+
                " FROM herbicida,cliente,estoque"
                +" WHERE estoque.HerbicidaID = herbicida.Sequencial AND estoque.FornecedorID = cliente.cnpj";
        
        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();
        ResultSet lista_resultados;
        
        String contrato_bd = null;
        String mensagem_comprar = null;
        String mensagem_categoria = null;
        if(contrato == 0){
            contrato_bd = "Contrato";
            sql += " AND cliente.tipoContrato = 'c'";
        }else{
            if(contrato == 1){
                contrato_bd = "Licitação";
                sql += " AND cliente.tipoContrato = 'l'";
            }
        }
        
        if(categoria>-1){
            sql += " AND herbicida.Categoria = '"+categoria+"'"; 
        }
        
        if(!nome_cliente.isEmpty())
        {
            sql += " AND cliente.nome LIKE '"+ nome_cliente + "%'";
        }
        
        if(comprar == 1)
        {
            sql+= " AND herbicida.comprar = 1";
        }
        if(comprar == 0)
             sql+= " AND herbicida.comprar = 0";
        
        

        try
        {
             PreparedStatement comando = BD.conexão.prepareStatement(sql);
             lista_resultados = comando.executeQuery();
                while (lista_resultados.next()) {
                        if(lista_resultados.getString("cliente.tipoContrato").equals("c"))
                            contrato_bd = "Contrato";
                        else
                            contrato_bd = "Licitação";       
                        //verifica se precisa comprar
                        if(lista_resultados.getInt("herbicida.comprar")==1)
                            mensagem_comprar = " Precisa Comprar ";
                        else
                            mensagem_comprar = " Não Precisa Comprar";
                        
                        //verifica categoria        
                            if(lista_resultados.getString("herbicida.Categoria").equals("0")) mensagem_categoria = "Alimentício";
                            if(lista_resultados.getString("herbicida.Categoria").equals("1")) mensagem_categoria = "Limpeza";
                            if(lista_resultados.getString("herbicida.Categoria").equals("2")) mensagem_categoria = "Embalagem";
                            if(lista_resultados.getString("herbicida.Categoria").equals("3")) mensagem_categoria = "Higiene";
                            if(lista_resultados.getString("herbicida.Categoria").equals("4")) mensagem_categoria = "Informática";
                        
                    visões.addElement(new Visão<Integer>(                     
                            lista_resultados.getInt("estoque.Sequencial"), 
                            lista_resultados.getInt("estoque.Sequencial") +  " - "
                            + lista_resultados.getString("herbicida.nome")+ " - "        
                            +lista_resultados.getString("cliente.nome")+
                                    " - "
                            + contrato_bd + " - "
                            + mensagem_comprar + " - " 
                            + mensagem_categoria ) 
                    );
                }
            lista_resultados.close();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
        }
        
        
        
        return visões;
    
    }
}
