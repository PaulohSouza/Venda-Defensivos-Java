package controle;

import entidade.Herbicida;
import interfaces.JanelaCadastroHerbicida;

public class ControladorCadastroHerbicida {

    public ControladorCadastroHerbicida() {
        new JanelaCadastroHerbicida(this).setVisible(true);
    }

    public String inserirHerbicida(Herbicida herbicida) {
        Herbicida herbicida1 = Herbicida.buscarHerbicida(herbicida.getId());
        if (herbicida1 == null) {

            return Herbicida.inserirHerbicida(herbicida);
        } else {
            return " Herbicida já cadastrado";
        }
    }

    public String alterarHerbicida(Herbicida herbicida) {
        Herbicida herbicida1 = Herbicida.buscarHerbicida(herbicida.getId());
        if (herbicida1 != null) {
            return Herbicida.alterarHerbicida(herbicida);
        } else {
            return "Login de herbicida não cadastrado";
        }
    }

    public String removerHerbicida(int id) {
        Herbicida herbicida1 = Herbicida.buscarHerbicida(id);
        if (herbicida1 != null) {
            return Herbicida.removerHerbicida(id);
        } else {
            return "CPF de herbicida não cadastrado";
        }
    }
}
