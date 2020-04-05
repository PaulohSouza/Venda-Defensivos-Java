
package interfaces;

import java.awt.Color;
import javax.swing.UIManager;
import java.util.Vector;
import controle.ControladorCadastroHerbicida;
import entidade.Herbicida;
import entidade.Herbicida.Formulacao;
import entidade.Visão;
import java.util.List;
import util.Data;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Utilitarios;


public class JanelaCadastroHerbicida extends javax.swing.JFrame {

    ControladorCadastroHerbicida controlador;
    Vector<Visão<Integer>> herbicidas_cadastrados;
    int id_formulacao = 0;
    
    public JanelaCadastroHerbicida(ControladorCadastroHerbicida controlador) {
       this.controlador = controlador;
        herbicidas_cadastrados = Herbicida.getVisões();
       initComponents();
    }
    @SuppressWarnings("unchecked")
    
   //---------------------Inserção-----------
   private void inserirHerbicida(java.awt.event.ActionEvent evt) {
        Herbicida herbicida = obterHerbicidaInformado();

        String mensagem_erro = null;
        if (herbicida != null) {
            mensagem_erro = controlador.inserirHerbicida(herbicida);
        } else {
            mensagem_erro = "Algum atributo do herbicida não foi informado";
        }
        if (mensagem_erro == null) {
            Visão<Integer> visão = herbicida.getVisão();
            herbicidas_cadastradosComboBox.addItem(visão);
            herbicidas_cadastradosComboBox.setSelectedItem(visão);
            ultimas_insercoesTextArea.append(herbicida.getVisão().toString()+"\n");
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
   
    //---------------------Vião dos Dados-----------
    private Visão<Integer> getVisãoHerbicidasCadastrados(int chave) {
        for (Visão<Integer> visão : herbicidas_cadastrados) {
            if (visão.getChave().equals(chave)) {
                return visão;
            }
        }
        return null;
    }

   //---------------------Obter Dados---------
   private Herbicida obterHerbicidaInformado() {
       
       String auxiliar = "";
       String aux_valor = "";
        //valores tipo String
        String nome = nome_produtoTextField.getText();
        if (nome.isEmpty()) return null;
        
        String codigo_barras = codigo_barrasTextField.getText();
        if (codigo_barras.isEmpty()) return null;
      
        String empresa = empresaTextField.getText();
        if(empresa.isEmpty())return null;
   
        Formulacao formulacao1 = null;  
        if (formulacaoComboBox.getSelectedIndex()>=0){
             formulacao1 = Formulacao.values()[formulacaoComboBox.getSelectedIndex()];
        }else{
            return null;
        }
       
       aux_valor = valor_unitarioTextField.getText();    
        if(aux_valor.isEmpty()) return null;
        
        float valorU = 0F;
        try{
            valorU = Float.valueOf(aux_valor);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Erro ao converter valores");
            return null;
        }
          
        auxiliar = quantidade_estoqueTextField.getText();
        if(auxiliar.isEmpty()) 
            return null;
       
        int estoque = 0;
     
        try{
        estoque = Integer.valueOf(auxiliar);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Erro ao converter valores");
            return null;
        }
        if(estoque==0) return null;
          
        int id;
        if (idTextField.getText().isEmpty()) {
            id = 0;
           
        } else {
            id = Integer.parseInt(idTextField.getText().trim());
  
        }

        Boolean precisa_inscricao_estadual = precisa_ieCheckBox.isSelected();
   
        return new Herbicida(id, nome,codigo_barras, empresa, valorU, estoque, formulacao1, precisa_inscricao_estadual);
    }

    //---------------------Remoção-----------------
    private void removerHerbicida(java.awt.event.ActionEvent evt) {
        Visão<Integer> visão
                = (Visão<Integer>) herbicidas_cadastradosComboBox.getSelectedItem();
        String mensagem_erro = null;
        if (visão != null) {
            mensagem_erro = controlador.removerHerbicida(visão.getChave());
        } else {
            mensagem_erro = "Nenhum Herbicida selecionado";
        }
        if (mensagem_erro == null) {
            herbicidas_cadastrados.remove(visão);
            new Utilitarios().LimpaTela(dados_produtosPanel);
            herbicidas_cadastradosComboBox.setSelectedIndex(-1);
            idTextField.enable(true);
            if (herbicidas_cadastrados.size() >= 1) {
                herbicidas_cadastradosComboBox.setSelectedIndex(0);
                formulacaoComboBox.setSelectedIndex(-1);
            } else {
           }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
       //---------------------Alteração-----------------
    private void alterarHerbicida(java.awt.event.ActionEvent evt) {
       
        Herbicida herbicida = obterHerbicidaInformado();
        String mensagem_erro = null;
        if (herbicida != null) {       
            mensagem_erro = controlador.alterarHerbicida(herbicida);
        } else {
            mensagem_erro = "Erro ao inserir, Algum atributo não foi informado ou não digitado corretamente"; 
        }
            if (mensagem_erro == null) {
            Visão<Integer> visão = getVisãoHerbicidasCadastrados(herbicida.getId());
            if (visão != null) {
                visão.setInfo(herbicida.getVisão().getInfo());
                herbicidas_cadastradosComboBox.updateUI();
                herbicidas_cadastradosComboBox.setSelectedItem(visão);  
            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    //---------------------Consultar----------------
    private void consultarHerbicida(java.awt.event.ActionEvent evt) {
        Visão<Integer> visão = (Visão<Integer>) herbicidas_cadastradosComboBox.getSelectedItem();
        Herbicida herbicida = null;
        String mensagem_erro = null;
        if (visão != null) {
            herbicida = Herbicida.buscarHerbicida(visão.getChave());
    
            if (herbicida == null) {
                mensagem_erro = "Herbicida não cadastrado";
            }
        } else {
            mensagem_erro = "Nenhum herbicida selecionado";
        }
        if (mensagem_erro == null) {
            nome_produtoTextField.setText(herbicida.getNome());
            codigo_barrasTextField.setText(herbicida.getCodig_barras());
            empresaTextField.setText(herbicida.getEmpresa());   
            valor_unitarioTextField.setText(Float.toString(herbicida.getValor_unitario()));
            quantidade_estoqueTextField.setText(Integer.toString(herbicida.getQuantidade_estoque()));
            formulacaoComboBox.setSelectedIndex(herbicida.getFormulacao().ordinal());
            precisa_ieCheckBox.setSelected(herbicida.getPrecisa_registro());
            ultimas_consultasTextArea.append(herbicida.getVisão().toString()+"\n");
            id_formulacao = formulacaoComboBox.getSelectedIndex();
            idTextField.setText(herbicida.getId() + " ");
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //---------------------Listar para J Table -- JList----------------
    public void listar(){
        
        List<Herbicida> lista = Herbicida.listarHerbicida();
        DefaultTableModel dados = (DefaultTableModel) herbicidas_cadastradosTable.getModel();
        dados.setNumRows(0);
        String registro = "";
        
        for(Herbicida c: lista){
            if(c.getPrecisa_registro() == true){
                registro = "Sim";
            }else
                registro = "Nao";
        
        dados.addRow(new Object[]{
            c.getId(),
            c.getNome(),  c.getCodig_barras(), c.getEmpresa(),
            c.getValor_unitario(), c.getQuantidade_estoque(), c.getFormulacao(),
            registro
        });      
    }
 }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dados_produtosPanel = new javax.swing.JPanel();
        nomeLabel = new javax.swing.JLabel();
        nome_produtoTextField = new javax.swing.JTextField();
        usuarioLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        codigo_barrasTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        cargolabel = new javax.swing.JLabel();
        empresaTextField = new javax.swing.JTextField();
        senhaLabel = new javax.swing.JLabel();
        nivelLabel = new javax.swing.JLabel();
        formulacaoComboBox = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        herbicidas_cadastradosComboBox = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        inserirButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();
        alterarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        consultar_realizadasScrollPane = new javax.swing.JScrollPane();
        ultimas_consultasTextArea = new javax.swing.JTextArea();
        Inserscoes_realizadasScrollPane = new javax.swing.JScrollPane();
        ultimas_insercoesTextArea = new javax.swing.JTextArea();
        valor_unitarioTextField = new javax.swing.JTextField();
        senhaLabel1 = new javax.swing.JLabel();
        quantidade_estoqueTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        precisa_ieCheckBox = new javax.swing.JCheckBox();
        consulta_produtoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        herbicidas_cadastradosTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        nome_consultadoTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Usuários - AgroHerbicides");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HERBICIDAS");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        dados_produtosPanel.setBackground(new java.awt.Color(255, 255, 255));

        nomeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeLabel.setText("Produto:");

        nome_produtoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nome_produtoTextField.setToolTipText("");

        usuarioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioLabel.setText("Codigo");

        idTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idTextField.setToolTipText("");
        idTextField.setEnabled(false);

        codigo_barrasTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codigo_barrasTextField.setToolTipText("");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailLabel.setText("Codigo_Barras: ");

        cargolabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargolabel.setText("Empresa:");

        empresaTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        empresaTextField.setToolTipText("");

        senhaLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaLabel.setText("Valor Unitário: ");

        nivelLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivelLabel.setText("Formulação");

        formulacaoComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formulacaoComboBox.setModel(new DefaultComboBoxModel(Herbicida.formulacoes));
        formulacaoComboBox.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Produtos Cadastrados:");

        herbicidas_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        herbicidas_cadastradosComboBox.setModel((new DefaultComboBoxModel (herbicidas_cadastrados)));
        herbicidas_cadastradosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herbicidas_cadastradosComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(herbicidas_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(herbicidas_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        inserirButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inserirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_add.png"))); // NOI18N
        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirButtonActionPerformed(evt);
            }
        });

        consultarButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/magnifier.png"))); // NOI18N
        consultarButton.setText("Consultar");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarButtonActionPerformed(evt);
            }
        });

        alterarButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alterarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_edit.png"))); // NOI18N
        alterarButton.setText("Alterar");
        alterarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarButtonActionPerformed(evt);
            }
        });

        removerButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        removerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_delete.png"))); // NOI18N
        removerButton.setText("Remover");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonActionPerformed(evt);
            }
        });

        limparButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limparButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil_delete.png"))); // NOI18N
        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inserirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consultarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(limparButton)
                .addGap(94, 94, 94))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultarButton)
                    .addComponent(inserirButton)
                    .addComponent(alterarButton)
                    .addComponent(removerButton)
                    .addComponent(limparButton))
                .addContainerGap())
        );

        ultimas_consultasTextArea.setColumns(20);
        ultimas_consultasTextArea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ultimas_consultasTextArea.setLineWrap(true);
        ultimas_consultasTextArea.setRows(7);
        ultimas_consultasTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ultimas Consultas Realizadas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N
        consultar_realizadasScrollPane.setViewportView(ultimas_consultasTextArea);

        ultimas_insercoesTextArea.setColumns(20);
        ultimas_insercoesTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ultimas_insercoesTextArea.setRows(7);
        ultimas_insercoesTextArea.setAutoscrolls(false);
        ultimas_insercoesTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ultimas Inserções Realizadas:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N
        Inserscoes_realizadasScrollPane.setViewportView(ultimas_insercoesTextArea);

        valor_unitarioTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_unitarioTextField.setToolTipText("");

        senhaLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaLabel1.setText("Quantidade:");

        quantidade_estoqueTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantidade_estoqueTextField.setToolTipText("");

        jLabel3.setText("(Em Estoque)");

        precisa_ieCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        precisa_ieCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precisa_ieCheckBox.setSelected(true);
        precisa_ieCheckBox.setText("Produto Registrado no MAPA");
        precisa_ieCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precisa_ieCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dados_produtosPanelLayout = new javax.swing.GroupLayout(dados_produtosPanel);
        dados_produtosPanel.setLayout(dados_produtosPanelLayout);
        dados_produtosPanelLayout.setHorizontalGroup(
            dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                        .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeLabel)
                                    .addComponent(cargolabel)))
                            .addComponent(senhaLabel1)
                            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(usuarioLabel)))
                        .addGap(18, 18, 18)
                        .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome_produtoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                                .addComponent(quantidade_estoqueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(empresaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Inserscoes_realizadasScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_produtosPanelLayout.createSequentialGroup()
                        .addComponent(precisa_ieCheckBox)
                        .addGap(80, 80, 80)))
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                        .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel)
                                    .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(senhaLabel)))
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_produtosPanelLayout.createSequentialGroup()
                                .addComponent(nivelLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigo_barrasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valor_unitarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formulacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(consultar_realizadasScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dados_produtosPanelLayout.setVerticalGroup(
            dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_produtosPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(nome_produtoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(codigo_barrasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargolabel)
                    .addComponent(senhaLabel)
                    .addComponent(valor_unitarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nivelLabel)
                    .addComponent(formulacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senhaLabel1)
                    .addComponent(quantidade_estoqueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(precisa_ieCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(dados_produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Inserscoes_realizadasScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultar_realizadasScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Dados Produtos", dados_produtosPanel);

        consulta_produtoPanel.setBackground(new java.awt.Color(255, 255, 255));

        herbicidas_cadastradosTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        herbicidas_cadastradosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Codigo_barras", "Empresa", "Valor_unitario", "Quantidade", "Formulacao", "Precisa_Registro"
            }
        ));
        herbicidas_cadastradosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                herbicidas_cadastradosTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(herbicidas_cadastradosTable);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Digite o Nome Procurado:");

        nome_consultadoTextField.setToolTipText("");
        nome_consultadoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_consultadoTextFieldActionPerformed(evt);
            }
        });
        nome_consultadoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nome_consultadoTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nome_consultadoTextFieldKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Clique na linha do nome desejado na Tabela para resgatar os dados.");

        javax.swing.GroupLayout consulta_produtoPanelLayout = new javax.swing.GroupLayout(consulta_produtoPanel);
        consulta_produtoPanel.setLayout(consulta_produtoPanelLayout);
        consulta_produtoPanelLayout.setHorizontalGroup(
            consulta_produtoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consulta_produtoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consulta_produtoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addGroup(consulta_produtoPanelLayout.createSequentialGroup()
                        .addGroup(consulta_produtoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(consulta_produtoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nome_consultadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        consulta_produtoPanelLayout.setVerticalGroup(
            consulta_produtoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consulta_produtoPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(consulta_produtoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nome_consultadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta por Nome", consulta_produtoPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // ao abrir a Tela
        formulacaoComboBox.setSelectedIndex(-1);
        listar();
        UIManager.put("herbicidas_cadastradosComboBox.disabledForeground", Color.BLACK);
        UIManager.put("herbicidas_cadastradosComboBox.disabledBackground", Color.WHITE);
        
    }//GEN-LAST:event_formWindowActivated

    private void herbicidas_cadastradosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_herbicidas_cadastradosComboBoxActionPerformed

    }//GEN-LAST:event_herbicidas_cadastradosComboBoxActionPerformed

    private void inserirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirButtonActionPerformed
        inserirHerbicida(evt);
        listar(); //Atualizar JTable
    }//GEN-LAST:event_inserirButtonActionPerformed

    private void consultarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarButtonActionPerformed
        consultarHerbicida(evt);
        idTextField.enable(false); //Impede Edição de Chave  
       
    }//GEN-LAST:event_consultarButtonActionPerformed

    private void herbicidas_cadastradosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_herbicidas_cadastradosTableMouseClicked
       String precisa_ie;
        
       jTabbedPane1.setSelectedIndex(0);
       idTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 0).toString());
       nome_produtoTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 1).toString());
       codigo_barrasTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 2).toString());
       empresaTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 3).toString());
       valor_unitarioTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 4).toString());
       quantidade_estoqueTextField.setText(herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 5).toString());  
       if ((herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 6).toString()).equals("Po")){
       formulacaoComboBox.setSelectedIndex(0);
       }else{
           if((herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 6).toString()).equals("Liquido")){
            formulacaoComboBox.setSelectedIndex(1);
                }else{
                formulacaoComboBox.setSelectedIndex(2); 
           }
       }
            
       precisa_ie = (herbicidas_cadastradosTable.getValueAt(herbicidas_cadastradosTable.getSelectedRow(), 7).toString());
       if(precisa_ie == "Sim"){
           precisa_ieCheckBox.setSelected(true);
       }else{
           precisa_ieCheckBox.setSelected(false);
       }
       //Atualizar Vsao e TextArea com Dados
       Herbicida herbicida = obterHerbicidaInformado();
       Visão<Integer> visão = getVisãoHerbicidasCadastrados(herbicida.getId());
       herbicidas_cadastradosComboBox.setSelectedItem(visão);
       ultimas_consultasTextArea.append(herbicida.getVisão().toString()+"\n"); 
      
    }//GEN-LAST:event_herbicidas_cadastradosTableMouseClicked

    private void removerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonActionPerformed
         removerHerbicida(evt);
         precisa_ieCheckBox.setSelected(false);
         formulacaoComboBox.setSelectedIndex(-1);
         listar();  
    }//GEN-LAST:event_removerButtonActionPerformed

    private void alterarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButtonActionPerformed
        alterarHerbicida(evt);
        listar();
        
    }//GEN-LAST:event_alterarButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        new Utilitarios().LimpaTela(dados_produtosPanel);
        formulacaoComboBox.setSelectedIndex(-1);
        precisa_ieCheckBox.setSelected(false);
        idTextField.enable(true);
    }//GEN-LAST:event_limparButtonActionPerformed

    private void nome_consultadoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_consultadoTextFieldActionPerformed

    private void nome_consultadoTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldKeyPressed
      
    }//GEN-LAST:event_nome_consultadoTextFieldKeyPressed

    private void nome_consultadoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldKeyReleased
        String nome = "%" + nome_consultadoTextField.getText() + "%";
        
        List<Herbicida> lista = Herbicida.BuscarHerbicidaPorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) herbicidas_cadastradosTable.getModel();
        dados.setNumRows(0);
        for(Herbicida c: lista){
        dados.addRow(new Object[]{
            c.getId(),
            c.getNome(),  c.getCodig_barras(), c.getEmpresa(),
            c.getValor_unitario(), c.getQuantidade_estoque(), c.getFormulacao(),
            c.getPrecisa_registro()
        });  
            
      }
    }//GEN-LAST:event_nome_consultadoTextFieldKeyReleased

    private void precisa_ieCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precisa_ieCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precisa_ieCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Inserscoes_realizadasScrollPane;
    private javax.swing.JButton alterarButton;
    private javax.swing.JLabel cargolabel;
    private javax.swing.JTextField codigo_barrasTextField;
    private javax.swing.JPanel consulta_produtoPanel;
    private javax.swing.JButton consultarButton;
    private javax.swing.JScrollPane consultar_realizadasScrollPane;
    private javax.swing.JPanel dados_produtosPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField empresaTextField;
    private javax.swing.JComboBox formulacaoComboBox;
    private javax.swing.JComboBox herbicidas_cadastradosComboBox;
    private javax.swing.JTable herbicidas_cadastradosTable;
    private javax.swing.JTextField idTextField;
    private javax.swing.JButton inserirButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel nivelLabel;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nome_consultadoTextField;
    private javax.swing.JTextField nome_produtoTextField;
    private javax.swing.JCheckBox precisa_ieCheckBox;
    private javax.swing.JTextField quantidade_estoqueTextField;
    private javax.swing.JButton removerButton;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JLabel senhaLabel1;
    private javax.swing.JTextArea ultimas_consultasTextArea;
    private javax.swing.JTextArea ultimas_insercoesTextArea;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JTextField valor_unitarioTextField;
    // End of variables declaration//GEN-END:variables


}
