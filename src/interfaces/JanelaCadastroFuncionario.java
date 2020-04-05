
package interfaces;

import java.awt.Color;
import javax.swing.UIManager;
import java.util.Vector;
import controle.ControladorCadastroFuncionario;
import entidade.Funcionario;
import entidade.Visão;
import java.util.List;
import util.Data;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Utilitarios;


public class JanelaCadastroFuncionario extends javax.swing.JFrame {

    ControladorCadastroFuncionario controlador;
    Vector<Visão<String>> funcionarios_cadastrados;

    public JanelaCadastroFuncionario(ControladorCadastroFuncionario controlador) {
       this.controlador = controlador;
        funcionarios_cadastrados = Funcionario.getVisões();
       initComponents();
    }
    @SuppressWarnings("unchecked")

   //---------------------Inserção-----------
   private void inserirFuncionario(java.awt.event.ActionEvent evt) {
        Funcionario funcionario = obterFuncionarioInformado();
        String mensagem_erro = null;
        if (funcionario != null) {
            mensagem_erro = controlador.inserirFuncionario(funcionario);
        } else {
            mensagem_erro = "Algum atributo do funcionario não foi informado";
        }
        if (mensagem_erro == null) {
            Visão<String> visão = funcionario.getVisão();
            funcionarios_cadastradosComboBox.addItem(visão);
            funcionarios_cadastradosComboBox.setSelectedItem(visão);
            ultimas_insercoesTextArea.append(funcionario.getVisão().toString()+"\n");
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
   
    //---------------------Vião dos Dados-----------
    private Visão<String> getVisãoFuncionariosCadastrados(String chave) {
        for (Visão<String> visão : funcionarios_cadastrados) {
            if (visão.getChave().equals(chave)) {
                return visão;
            }
        }
        return null;
    }

   //---------------------Obter Dados---------
   private Funcionario obterFuncionarioInformado() {
        String login = loginTextField.getText();
        if (login.isEmpty()) {
            return null; 
        }
        
        String nome = nomeTextField.getText();
        if (nome.isEmpty()) {
            return null;
        }
        
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            return null;
        }
        String cargo = cargoTextField.getText();
        if (cargo.isEmpty()) {
            return null;
        }

         String senha = senhaTextField.getText();
         if (senha.isEmpty()) {
            return null;
        }
         
          String nivel_acesso = nivelComboBox.getSelectedItem().toString();
        if (nivel_acesso.contains("Selecione")) {
            return null;
        }

        return new Funcionario(login, nome, senha, email, cargo, nivel_acesso);
    }

    //---------------------Remoção-----------------
    private void removerFuncionario(java.awt.event.ActionEvent evt) {
        Visão<String> visão
                = (Visão<String>) funcionarios_cadastradosComboBox.getSelectedItem();
        String mensagem_erro = null;
        if (visão != null) {
            mensagem_erro = controlador.removerFuncionario(visão.getChave());
        } else {
            mensagem_erro = "Nenhum cliente selecionado";
        }
        if (mensagem_erro == null) {
            funcionarios_cadastrados.remove(visão);
            new Utilitarios().LimpaTela(dados_funcionariosPanel);
            loginTextField.enable(true);
            if (funcionarios_cadastrados.size() >= 1) {
                funcionarios_cadastradosComboBox.setSelectedIndex(0);
            } else {
                funcionarios_cadastradosComboBox.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
       //---------------------Alteração-----------------
    private void alterarFuncionario(java.awt.event.ActionEvent evt) {
        Funcionario funcionario = obterFuncionarioInformado();
        String mensagem_erro = null;
        if (funcionario != null) {
            mensagem_erro = controlador.alterarFuncionario(funcionario);
        } else {
            mensagem_erro = "Algum atributo do funcionário não foi informado";
        }
        if (mensagem_erro == null) {
            Visão<String> visão = getVisãoFuncionariosCadastrados(funcionario.getLogin());
            if (visão != null) {

                visão.setInfo(funcionario.getVisão().getInfo());
                funcionarios_cadastradosComboBox.updateUI();
                funcionarios_cadastradosComboBox.setSelectedItem(visão);  
            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //---------------------Consultar----------------
    private void consultarFuncionario(java.awt.event.ActionEvent evt) {
        Visão<String> visão = (Visão<String>) funcionarios_cadastradosComboBox.getSelectedItem();
        Funcionario funcionario = null;
        String mensagem_erro = null;
        if (visão != null) {
            funcionario = Funcionario.buscarFuncionario(visão.getChave());
            if (funcionario == null) {
                mensagem_erro = "Funcionario não cadastrado";
            }
        } else {
            mensagem_erro = "Nenhum funcionario selecionado";
        }
        if (mensagem_erro == null) {
            loginTextField.setText(funcionario.getLogin());
            nomeTextField.setText(funcionario.getNome());
            senhaTextField.setText(funcionario.getSenha());
            emailTextField.setText(funcionario.getEmail()); 
            cargoTextField.setText(funcionario.getCargo());
            nivelComboBox.setSelectedItem(funcionario.getNivel_acesso());
            ultimas_consultasTextArea.append(funcionario.getVisão().toString()+"\n");
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //---------------------Listar para J Table -- JList----------------
    public void listar(){
        
        List<Funcionario> lista = Funcionario.listarFuncionario();
        DefaultTableModel dados = (DefaultTableModel) funcionarios_cadastradosTable.getModel();
        dados.setNumRows(0);
        for(Funcionario c: lista){
        
        dados.addRow(new Object[]{
            c.getLogin(),
            c.getNome(),  c.getSenha(), c.getEmail(),
            c.getCargo(), c.getNivel_acesso(),
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
        dados_funcionariosPanel = new javax.swing.JPanel();
        nomeLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        usuarioLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        cargolabel = new javax.swing.JLabel();
        cargoTextField = new javax.swing.JTextField();
        senhaLabel = new javax.swing.JLabel();
        nivelLabel = new javax.swing.JLabel();
        nivelComboBox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        funcionarios_cadastradosComboBox = new javax.swing.JComboBox();
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
        senhaTextField = new javax.swing.JTextField();
        consulta_funcionarioPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        funcionarios_cadastradosTable = new javax.swing.JTable();
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
        jLabel1.setText("Usuários do Sistema");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        dados_funcionariosPanel.setBackground(new java.awt.Color(255, 255, 255));

        nomeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeLabel.setText("Nome:");

        nomeTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeTextField.setToolTipText("");

        usuarioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioLabel.setText("Usuário");

        loginTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginTextField.setToolTipText("");

        emailTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailTextField.setToolTipText("");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailLabel.setText("E-mail:");

        cargolabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargolabel.setText("Cargo:");

        cargoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargoTextField.setToolTipText("");

        senhaLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaLabel.setText("Senha:");

        nivelLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivelLabel.setText("Nivel:");

        nivelComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nivelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Administrador", "Caixa", "Balconista", "Financeiro", " " }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Funcionários Cadastrados:");

        funcionarios_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        funcionarios_cadastradosComboBox.setModel((new DefaultComboBoxModel (funcionarios_cadastrados)));
        funcionarios_cadastradosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarios_cadastradosComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(funcionarios_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(funcionarios_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        senhaTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaTextField.setToolTipText("");

        javax.swing.GroupLayout dados_funcionariosPanelLayout = new javax.swing.GroupLayout(dados_funcionariosPanel);
        dados_funcionariosPanel.setLayout(dados_funcionariosPanelLayout);
        dados_funcionariosPanelLayout.setHorizontalGroup(
            dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                        .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel)
                                    .addComponent(nomeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                    .addComponent(nomeTextField))
                                .addGap(66, 66, 66)
                                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cargolabel)
                                    .addComponent(usuarioLabel)))
                            .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                                .addComponent(senhaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(senhaTextField)
                                .addGap(78, 78, 78)
                                .addComponent(nivelLabel)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cargoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(loginTextField)
                            .addComponent(nivelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                        .addComponent(Inserscoes_realizadasScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(consultar_realizadasScrollPane)))
                .addContainerGap())
        );
        dados_funcionariosPanelLayout.setVerticalGroup(
            dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(cargolabel)
                    .addComponent(cargoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel)
                    .addComponent(nivelLabel)
                    .addComponent(nivelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senhaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(dados_funcionariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(Inserscoes_realizadasScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dados_funcionariosPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(consultar_realizadasScrollPane)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Dados Funcionários", dados_funcionariosPanel);

        consulta_funcionarioPanel.setBackground(new java.awt.Color(255, 255, 255));

        funcionarios_cadastradosTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        funcionarios_cadastradosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Nome", "Senha", "Email", "Cargo", "Nivel_Acesso"
            }
        ));
        funcionarios_cadastradosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                funcionarios_cadastradosTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(funcionarios_cadastradosTable);

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

        javax.swing.GroupLayout consulta_funcionarioPanelLayout = new javax.swing.GroupLayout(consulta_funcionarioPanel);
        consulta_funcionarioPanel.setLayout(consulta_funcionarioPanelLayout);
        consulta_funcionarioPanelLayout.setHorizontalGroup(
            consulta_funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consulta_funcionarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consulta_funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(consulta_funcionarioPanelLayout.createSequentialGroup()
                        .addGroup(consulta_funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(consulta_funcionarioPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nome_consultadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        consulta_funcionarioPanelLayout.setVerticalGroup(
            consulta_funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consulta_funcionarioPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(consulta_funcionarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nome_consultadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta por Nome", consulta_funcionarioPanel);

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
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // ao abrir a Tela
        listar();
        UIManager.put("funcionarios_cadastradosComboBox.disabledForeground", Color.BLACK);
        UIManager.put("funcionarios_cadastradosComboBox.disabledBackground", Color.WHITE);
    }//GEN-LAST:event_formWindowActivated

    private void funcionarios_cadastradosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarios_cadastradosComboBoxActionPerformed

    }//GEN-LAST:event_funcionarios_cadastradosComboBoxActionPerformed

    private void inserirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirButtonActionPerformed
        inserirFuncionario(evt);
        listar(); //Atualizar JTable
    }//GEN-LAST:event_inserirButtonActionPerformed

    private void consultarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarButtonActionPerformed
        consultarFuncionario(evt);
        loginTextField.enable(false); //Impede Edição de Chave
    }//GEN-LAST:event_consultarButtonActionPerformed

    private void funcionarios_cadastradosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_funcionarios_cadastradosTableMouseClicked
         
       jTabbedPane1.setSelectedIndex(0);
       loginTextField.setText(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 0).toString());
       nomeTextField.setText(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 1).toString());
       senhaTextField.setText(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 2).toString());
       emailTextField.setText(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 3).toString());  
       cargoTextField.setText(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 4).toString());
       nivelComboBox.setSelectedItem(funcionarios_cadastradosTable.getValueAt(funcionarios_cadastradosTable.getSelectedRow(), 5).toString());
       
       //Atualizar Vsao e TextArea com Dados
       Funcionario funcionario = obterFuncionarioInformado();
       Visão<String> visão = getVisãoFuncionariosCadastrados(funcionario.getLogin());
       funcionarios_cadastradosComboBox.setSelectedItem(visão);
       ultimas_consultasTextArea.append(funcionario.getVisão().toString()+"\n");  
    }//GEN-LAST:event_funcionarios_cadastradosTableMouseClicked

    private void removerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonActionPerformed
         removerFuncionario(evt);
         listar();  
    }//GEN-LAST:event_removerButtonActionPerformed

    private void alterarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButtonActionPerformed
        alterarFuncionario(evt);
        listar();
    }//GEN-LAST:event_alterarButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        new Utilitarios().LimpaTela(dados_funcionariosPanel);
        loginTextField.enable(true);
    }//GEN-LAST:event_limparButtonActionPerformed

    private void nome_consultadoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_consultadoTextFieldActionPerformed

    private void nome_consultadoTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldKeyPressed
      
    }//GEN-LAST:event_nome_consultadoTextFieldKeyPressed

    private void nome_consultadoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nome_consultadoTextFieldKeyReleased
        String nome = "%" + nome_consultadoTextField.getText() + "%";
        
        List<Funcionario> lista = Funcionario.BuscarFuncionarioPorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) funcionarios_cadastradosTable.getModel();
        dados.setNumRows(0);
        for(Funcionario c: lista){
        dados.addRow(new Object[]{
            c.getLogin(),
            c.getNome(),  c.getSenha(), c.getEmail(),
            c.getCargo(), c.getNivel_acesso(),
        });  
            
      }
    }//GEN-LAST:event_nome_consultadoTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Inserscoes_realizadasScrollPane;
    private javax.swing.JButton alterarButton;
    private javax.swing.JTextField cargoTextField;
    private javax.swing.JLabel cargolabel;
    private javax.swing.JPanel consulta_funcionarioPanel;
    private javax.swing.JButton consultarButton;
    private javax.swing.JScrollPane consultar_realizadasScrollPane;
    private javax.swing.JPanel dados_funcionariosPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JComboBox funcionarios_cadastradosComboBox;
    private javax.swing.JTable funcionarios_cadastradosTable;
    private javax.swing.JButton inserirButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limparButton;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JComboBox<String> nivelComboBox;
    private javax.swing.JLabel nivelLabel;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JTextField nome_consultadoTextField;
    private javax.swing.JButton removerButton;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JTextField senhaTextField;
    private javax.swing.JTextArea ultimas_consultasTextArea;
    private javax.swing.JTextArea ultimas_insercoesTextArea;
    private javax.swing.JLabel usuarioLabel;
    // End of variables declaration//GEN-END:variables


}
