package entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import persistencia.BD;
import util.WebServiceCep;

public class Cliente {
    
    public static Cliente buscarCliente(String CPF){
        
        String sql = "SELECT Nome, NascimentoData, RG, Sexo, Email, Telefone, Celular,Rua, Numero, Complemento, Bairro,"
                + "Cidade, Cep, Estado FROM Clientes, Endereço WHERE Cpf = ? AND id_endereço = Cod_endereço";
        
        Cliente cliente = null;
        Endereço endereço;
        ResultSet lista_resultados = null;
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, CPF);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                
                cliente = new Cliente(
                    CPF,
                    lista_resultados.getString("Nome"),
                    lista_resultados.getString("Rg"),
                    lista_resultados.getString("NascimentoDATA"),
                    lista_resultados.getString("Sexo"),
                    lista_resultados.getString("Email"),
                    lista_resultados.getString("Telefone"),
                    lista_resultados.getString("Celular"),
                    endereço = new Endereço(
                        lista_resultados.getString("Rua"),
                        lista_resultados.getString("Numero"),
                        lista_resultados.getString("Complemento"),
                        lista_resultados.getString("Bairro"),
                        lista_resultados.getString("Cep"),
                        lista_resultados.getString("Cidade"),
                        lista_resultados.getString("Estado"))); 
                
            }
            lista_resultados.close();
            comando.close();
           
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            cliente = null;
        }
        return cliente;
    }
    
    public static String inserirCliente(Cliente cliente){
        
        String sql2 = "INSERT INTO Endereço(Rua, Numero, Complemento, Bairro, Cep, Cidade, Estado)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql2);
            comando.setString(1, cliente.getEndereço().getRua());
            comando.setString(2, cliente.getEndereço().getNúmero() + "");
            comando.setString(3, cliente.getEndereço().getComplemento());
            comando.setString(4, cliente.getEndereço().getBairro());
            comando.setString(5, cliente.getEndereço().getCep());
            comando.setString(6, cliente.getEndereço().getCidade());
            comando.setString(7, cliente.getEndereço().getEstado());
            comando.executeUpdate();
            comando.close();
           
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao inserir tabela Endereço no BD. Tente outra vez.";
        }
        
        String sql3 = "SELECT id_endereço FROM Endereço WHERE id_endereço = (SELECT MAX(id_endereço)FROM Endereço)";
        ResultSet lista_resultados = null;
        String id_endereço = null;
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql3);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                id_endereço = lista_resultados.getInt("id_endereço") + "";
            }
            lista_resultados.close();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao capturar chave primária da tabela Endereços";
        }
        
        String sql = "INSERT INTO Clientes(Cpf, Nome, Rg, NascimentoDATA, Sexo, Email, Telefone, Celular, cod_endereço)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, cliente.getCpf());
            comando.setString(2, cliente.getNome());
            comando.setString(3, cliente.getRg());
            comando.setString(4, cliente.getNascimentoData());
            comando.setString(5, cliente.getSexo());
            comando.setString(6, cliente.getEmail());
            comando.setString(7, cliente.getTelefone());
            comando.setString(8, cliente.getCelular());
            comando.setString(9, id_endereço); 
            comando.executeUpdate();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao inserir na tabela Clientes no BD.";
        }
        return null;
    }
    
    public static String alterarCliente(Cliente cliente){
        String sql = "UPDATE Clientes SET Nome = ?, Rg =?, NascimentoData = ?, Sexo = ?, Email = ?, Telefone = ?, Celular = ?"
                + "WHERE CPF = ?";
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getRg());
            comando.setString(3, cliente.getNascimentoData());
            comando.setString(4, cliente.getSexo());
            comando.setString(5, cliente.getEmail());
            comando.setString(6, cliente.getTelefone());
            comando.setString(7, cliente.getCelular());
            comando.setString(8, cliente.getCpf());
            comando.executeUpdate();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao alterar cliente no BD.";
        }
        
        String sql2 = "SELECT Cod_endereço FROM Endereço, clientes WHERE Cpf = ?"
                + "AND id_Endereço = Cod_endereço";
        String cod_endereço = null;
        ResultSet lista_resultados = null;
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql2);
            comando.setString(1, cliente.getCpf());
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                cod_endereço = lista_resultados.getInt("Cod_endereço") + "";
            }
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
        }
        
        String sql3 = "UPDATE Endereço SET Rua = ?, Numero = ?, Complemento = ?,"
                + "Bairro = ?, Cidade = ?, Cep = ?, Estado = ? WHERE id_endereço = ?";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql3);
            comando.setString(1, cliente.getEndereço().getRua());
            comando.setString(2, cliente.getEndereço().getNúmero() + "");
            comando.setString(3, cliente.getEndereço().getComplemento());
            comando.setString(4, cliente.getEndereço().getBairro());
            comando.setString(5, cliente.getEndereço().getCep());
            comando.setString(6, cliente.getEndereço().getCidade());
            comando.setString(7, cliente.getEndereço().getEstado());
            comando.setString(8, cod_endereço);
            comando.executeUpdate();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
        }
        return null;
    }
    
    public static String removerCliente(String CPF){
        String sql1 = "SELECT Cod_endereço FROM  Clientes WHERE Cpf = ?";
        ResultSet lista_resultados = null;
        String cod_endereço = null;
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql1);
            comando.setString(1, CPF);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                cod_endereço = lista_resultados.getInt("Cod_endereço") + "";
            }
            lista_resultados.close();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao tentar obter ID da tabela de Clientes no BD.";
        }
        
        String sql2 = "DELETE FROM Clientes WHERE Cpf = ?";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql2);
            comando.setString(1, CPF);
            comando.executeUpdate();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao remover Cliente do BD.";
        }
        
        String sql3 = "DELETE FROM Endereço WHERE id_endereço = ?";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql3);
            comando.setString(1, cod_endereço);
            comando.executeUpdate();
            comando.close();
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro ao tentar excluir o endereço do cliente do BD.";
        }
        return null;
    }
    
    public static Vector<Visão<String>> getVisões(){
        String sql = "SELECT CPF, Nome FROM Clientes";
        ResultSet lista_resultados = null;
        Vector<Visão<String>> visões = new Vector<Visão<String>>();
        
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                visões.addElement(new Visão(
                        lista_resultados.getString("CPF"),
                        lista_resultados.getString("CPF") + " - "
                        +lista_resultados.getString("Nome")));
            }
            lista_resultados.close();
            comando.close();
            
        }catch(SQLException exceção_sql){
            exceção_sql.printStackTrace();
        }
        return visões;
    }
    
 
    private String cpf, nome, rg, nascimentoData, sexo, telefone, celular, email;
    private Endereço endereço;
    
    public Cliente(String cpf, String nome, String rg, String nascimentoData, String sexo, String email, String telefone, String celular, Endereço endereço){
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.nascimentoData = nascimentoData;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.endereço = endereço;
    }

    public String getRg() {
        return rg;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public Visão<String> getVisão(){
        return new Visão<String>(cpf, cpf + " - " + nome);
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String Cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimentoData() {
        return nascimentoData;
    }

    public void setNascimentoData(String nascimentoData) {
        this.nascimentoData = nascimentoData;
    }

    public Endereço getEndereço() {
        return endereço;
    }

    public void setEndereço(Endereço endereço) {
        this.endereço = endereço;
    }
    
    public String toString(){
        return "CPF: " + cpf + "\nNome: " + nome + "\nData de nascimento: " + nascimentoData 
                + "\nSexo: " + sexo + "\nTelefone: " + telefone + "\nEndereço: " + endereço.toString();
    }
    
}
