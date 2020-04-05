package controle;

import entidade.ItemVenda;
import interfaces.JanelaCadastroVenda;
import entidade.Venda;
import interfaces.JanelaGerenciamentoVenda;

public class ControladorGerenciamentoVenda {

    public ControladorGerenciamentoVenda() {
        new JanelaGerenciamentoVenda(this).setVisible(true);
    }

    public String removerVenda(int id) {
        Venda venda1 = Venda.buscarVenda(id);
        if (venda1 != null) {
            return venda1.RemoverVenda(id);
        } else {
            return "Venda não encontrada";
        }
    }
}
