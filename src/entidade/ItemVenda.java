
package entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
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

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }
    

    public int getId_itemVenda() {
        return id_itemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public Herbicida getHerbicida() {
        return herbicida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }


    @Override
    public String toString() {
        return "ItemVenda{" + "id=" + id_itemVenda + ", venda=" + venda + ", herbicida=" + herbicida + ", quantidade=" + quantidade + ", subtotal=" + subtotal + '}';
    }
    
  
public Visão<Integer> getVisão() {
        return new Visão<Integer>(id_itemVenda, id_itemVenda + " - "+ herbicida.getNome()+ " - " + String.valueOf(subtotal));
              
    }


 //Inserir Item da Venda
    public String inserirItem(ItemVenda item_venda) {

        String sql = "INSERT INTO tab_Itemvenda(venda_id, herbicida_id, valor_unitario, quantidade, subtotal) VALUES(?,?,?,?,?)";
        try {
                
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            System.out.println("Montou a Sql");
//            System.out.println(venda.ultima_venda + "");
            System.out.println(item_venda.getHerbicida().getId() + "");
            System.out.println(item_venda.getQuantidade() + ""); 
            System.out.println(item_venda.getValor_unitario()+ ""); 
             
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

    /*
  
    public static ItemVenda consultarItemVenda(int sequencial_item) {
        
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
          
            lista_resultados.close();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return null;
        }
        
         
        return new ItemVenda(sequencial_item, venda, herbicida, quantidade, valor_unitario, valor_totalItem);
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
    /*
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
*/
}