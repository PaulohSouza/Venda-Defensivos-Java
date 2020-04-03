package controle;


import entidade.Funcionario;
import entidade.Visão;
import interfaces.JanelaCadastroFuncionario;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;


public class ControladorCadastroFuncionario {

    public ControladorCadastroFuncionario() {
        new JanelaCadastroFuncionario(this).setVisible(true);
    }

    public String inserirFuncionario(Funcionario funcionario) {
        Funcionario funcionario1 = Funcionario.buscarFuncionario(funcionario.getLogin());
        if (funcionario1 == null) {
            return Funcionario.inserirFuncionario(funcionario);
        } else {
            return "Login de funcionario já cadastrado";
        }
    }

    public String alterarFuncionario(Funcionario funcionario) {
        Funcionario funcionario1 = Funcionario.buscarFuncionario(funcionario.getLogin());  
        if (funcionario1 != null) {
            return Funcionario.alterarFuncionario(funcionario);
        } else {
            return "Login de funcionario não cadastrado";
        }
    }

    public String removerFuncionario(String cpf) {
        Funcionario funcionario1 = Funcionario.buscarFuncionario(cpf);
        if (funcionario1 != null) {
            return Funcionario.removerFuncionario(cpf);
        } else {
            return "CPF de funcionario não cadastrado";
        }
    }
}
