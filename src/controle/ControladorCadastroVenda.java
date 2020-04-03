
package controle;

import entidade.ItemVenda;
import interfaces.JanelaCadastroVenda;
import entidade.Venda;

public class ControladorCadastroVenda {

   public ControladorCadastroVenda(){
       new JanelaCadastroVenda(this).setVisible(true);
   }
 
   public String inserirVenda(Venda venda) {

        Venda venda1 = Venda.buscarVenda(venda.getSequencial());
        if (venda1 == null) {
            return Venda.inserirVenda(venda);
        } else {
            return "Venda já cadastrado";
        }
     
   }
   
public String inserirItemVenda(Venda venda, ItemVenda itemvenda) {
        Venda venda1 = Venda.buscarVenda(venda.getSequencial());
        ItemVenda item1 = null;
        if (venda1 == null) {
            return item1.inserirItem(itemvenda);
        } else {
            return "Erro Repetição de venda";
        }
  }   
}

