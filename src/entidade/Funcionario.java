package entidade;

import interfaces.JanelaCadastroHerbicida;
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

public class Funcionario {

    public static Funcionario buscarFuncionario(String login) {

        String sql = "SELECT nome, senha, email, cargo, nivel_acesso from funcionario where login = ?";

        Funcionario funcionario = null;
        ResultSet lista_resultados = null;

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, login);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {

                funcionario = new Funcionario(
                        login,
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("senha"),
                        lista_resultados.getString("email"),
                        lista_resultados.getString("cargo"),
                        lista_resultados.getString("nivel_acesso"));

            }
            lista_resultados.close();
            comando.close();

        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            funcionario = null;
        }
        return funcionario;
    }

    public static String inserirFuncionario(Funcionario funcionario) {

        String sql = "INSERT INTO Funcionario(login, nome, email, senha, cargo, nivel_acesso)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, funcionario.getLogin());
            comando.setString(2, funcionario.getNome());
            comando.setString(3, funcionario.getEmail());
            comando.setString(4, funcionario.getSenha());
            comando.setString(5, funcionario.getCargo());
            comando.setString(6, funcionario.getNivel_acesso());

            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao inserir na tabela Clientes no BD.";
        }
        return null;
    }

    public static String alterarFuncionario(Funcionario funcionario) {

        String sql = "UPDATE Funcionario SET nome = ?, email =?, senha = ?, cargo = ?, nivel_acesso = ?"
                + "WHERE login = ?";

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getEmail());
            comando.setString(3, funcionario.getSenha());
            comando.setString(4, funcionario.getCargo());
            comando.setString(5, funcionario.getNivel_acesso());
            comando.setString(6, funcionario.getLogin());
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao alterar cliente no BD.";
        }

        return null;
    }

    public static String removerFuncionario(String login) {

        String sql = "DELETE FROM Funcionario WHERE login = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, login);
            comando.executeUpdate();
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro ao remover Cliente do BD.";
        }

        return null;
    }

    public static Vector<Visão<String>> getVisões() {
        String sql = "SELECT login, nome FROM Funcionario";
        ResultSet lista_resultados = null;
        Vector<Visão<String>> visões = new Vector<Visão<String>>();

        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                visões.addElement(new Visão(
                        lista_resultados.getString("login"),
                        lista_resultados.getString("login") + "-"
                        + lista_resultados.getString("nome")));
            }
            lista_resultados.close();
            comando.close();

        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
        return visões;
    }

    public static List<Funcionario> BuscarFuncionarioPorNome(String nome) {

        List<Funcionario> lista = new ArrayList<>();
        String sql = "Select * from funcionario where nome like ? ";

        Funcionario funcionario = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, nome);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                funcionario = new Funcionario(
                        lista_resultados.getString("login"),
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("email"),
                        lista_resultados.getString("senha"),
                        lista_resultados.getString("cargo"),
                        lista_resultados.getString("nivel_acesso"));

                lista.add(funcionario);
            }

            lista_resultados.close();
            comando.close();
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    public static List<Funcionario> listarFuncionario() {

        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * from funcionario";
        Funcionario funcionario = null;
        ResultSet lista_resultados = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                funcionario = new Funcionario(
                        lista_resultados.getString("login"),
                        lista_resultados.getString("nome"),
                        lista_resultados.getString("senha"),
                        lista_resultados.getString("email"),
                        lista_resultados.getString("cargo"),
                        lista_resultados.getString("nivel_acesso"));
                lista.add(funcionario);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //Logar
    public static int efetualogin(String user, String password) throws ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException, InstantiationException {
        String sql = "Select login, nome, senha, cargo, nivel_acesso from funcionario where login = ? and senha = ?";
        ResultSet lista_resultados = null;
        BD.criaConexãoComando();
        String login1 = "", nome1 = " ", senha1 = "", admin = "", cargo1 = "";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, user);
            comando.setString(2, password);
            lista_resultados = comando.executeQuery();

            while (lista_resultados.next()) {
                login1 = lista_resultados.getString("login");
                nome1 = lista_resultados.getString("nome");
                senha1 = lista_resultados.getString("senha");
                cargo1 = lista_resultados.getString("cargo");
                admin = lista_resultados.getString("nivel_acesso");
            }
            if ((login1.equals(user) && senha1.equals(password))) {
                JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema : " + nome1);
                JanelaVendaDefensivos tela_principal = new JanelaVendaDefensivos(nome1, cargo1, admin);
                tela_principal.setVisible(true);
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na busca de dados -  Erro: " + erro);

        }
        return 1;
    }

    private String login, nome, senha, email, cargo, nivel_acesso;

    public Funcionario(String login, String nome, String senha, String email, String cargo, String nivel_acesso) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cargo = cargo;
        this.nivel_acesso = nivel_acesso;

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setNivel_acesso(String nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNivel_acesso() {
        return nivel_acesso;
    }

    public Visão<String> getVisão() {
        return new Visão<String>(login, login + " - " + nome);
    }

    @Override
    public String toString() {
        return "Funcionario{" + "login=" + login + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", cargo=" + cargo + ", nivel_acesso=" + nivel_acesso + '}';
    }

}
