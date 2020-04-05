
package controle;

import entidade.ItemVenda;
import interfaces.JanelaCadastroVenda;
import entidade.Venda;
import interfaces.JanelaGerenciamentoVenda;

public class ControladorGerenciamentoVenda {

   public ControladorGerenciamentoVenda(){
       new JanelaGerenciamentoVenda(this).setVisible(true);
   }
}

