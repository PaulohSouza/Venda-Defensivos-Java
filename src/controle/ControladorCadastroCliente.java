package controle;

import interfaces.JanelaCadastroCliente;
import entidade.Cliente;
import entidade.Visão;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;


public class ControladorCadastroCliente {

    public ControladorCadastroCliente() {
        new JanelaCadastroCliente(this).setVisible(true);
    }

    public String inserirCliente(Cliente cliente) {
        Cliente cliente1 = Cliente.buscarCliente(cliente.getCpf());
        if (cliente1 == null) {
            return Cliente.inserirCliente(cliente);
        } else {
            return "CPF de cliente já cadastrado";
        }
    }

    public String alterarCliente(Cliente cliente) {
        Cliente cliente1 = Cliente.buscarCliente(cliente.getCpf());  
        if (cliente1 != null) {
            return Cliente.alterarCliente(cliente);
        } else {
            return "CPF de cliente não cadastrado";
        }
    }

    public String removerCliente(String cpf) {
        Cliente cliente1 = Cliente.buscarCliente(cpf);
        if (cliente1 != null) {
            return Cliente.removerCliente(cpf);
        } else {
            return "CPF de cliente não cadastrado";
        }
    }
}
