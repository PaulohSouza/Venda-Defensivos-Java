package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;    

public class BD {

    static final String URL_BD = "jdbc:mysql://localhost:3306/defensivosdb?useTimezone=true&serverTimezone=UTC&useSSL=false";
    static final String USUÁRIO = "root";
    static final String SENHA = "admin";
     public static Connection conexão = null;
    public  static Statement comando = null;

    public static void criaConexãoComando() {
        try {
            conexão = DriverManager.getConnection(URL_BD, USUÁRIO, SENHA);
            comando = conexão.createStatement();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
    }

    public static void fechaComandoConexão() {
        try {
            comando.close();
            conexão.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
        }
    }
}