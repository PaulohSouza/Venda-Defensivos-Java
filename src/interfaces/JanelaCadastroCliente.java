package interfaces;

import java.util.Vector;
import controle.ControladorCadastroCliente;
import entidade.Cliente;
import entidade.Endereço;

import entidade.Visão;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import util.Data;
import javax.swing.DefaultComboBoxModel;

public class JanelaCadastroCliente extends javax.swing.JFrame {

    ControladorCadastroCliente controlador;
    Vector<Visão<String>> clientes_cadastrados;

    public JanelaCadastroCliente(ControladorCadastroCliente controlador) {
        this.controlador = controlador;
        clientes_cadastrados = Cliente.getVisões();

        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void inserirCliente(java.awt.event.ActionEvent evt) {
        Cliente cliente = obterClienteInformado();
        String mensagem_erro = null;
        if (cliente != null) {
            mensagem_erro = controlador.inserirCliente(cliente);
        } else {
            mensagem_erro = "Algum atributo do cliente não foi informado";
        }
        if (mensagem_erro == null) {
            Visão<String> visão = cliente.getVisão();
            clientes_cadastradosComboBox.addItem(visão);
            clientes_cadastradosComboBox.setSelectedItem(visão);
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarCliente(java.awt.event.ActionEvent evt) {
        Visão<String> visão = (Visão<String>) clientes_cadastradosComboBox.getSelectedItem();
        Cliente cliente = null;
        String mensagem_erro = null;
        if (visão != null) {
            cliente = Cliente.buscarCliente(visão.getChave());
            if (cliente == null) {
                mensagem_erro = "Cliente não cadastrado";
            }
        } else {
            mensagem_erro = "Nenhum cliente selecionado";
        }
        if (mensagem_erro == null) {
            cpf_FormattedTextField.setText(cliente.getCpf());
            nomeTextField.setText(cliente.getNome());
            rgTextField.setText(cliente.getRg());
            emailTextField.setText(cliente.getEmail());
            telefone_clienteFormattedTextField.setText(cliente.getTelefone());
            celularFormattedTextFiel.setText(cliente.getCelular());
            data_nascimento_FormattedTextField.setText((cliente.getNascimentoData() + ""));
            if (cliente.getSexo().equals("m")) {
                masculinoRadioButton.setSelected(true);
            } else {
                femininoRadioButton.setSelected(true);
            }
            ruaTextField.setText(cliente.getEndereço().getRua());
            bairroTextField.setText(cliente.getEndereço().getBairro());
            numeroTextField.setText(cliente.getEndereço().getNúmero());
            complementoTextField.setText(cliente.getEndereço().getComplemento());
            cepFormattedTextField.setText(cliente.getEndereço().getCep());
            cidadeTextField.setText(cliente.getEndereço().getCidade());
            estadoselecionarComboBox.setSelectedItem(cliente.getEndereço().getEstado());

        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarCliente(java.awt.event.ActionEvent evt) {
        Cliente cliente = obterClienteInformado();
        String mensagem_erro = null;
        if (cliente != null) {
            mensagem_erro = controlador.alterarCliente(cliente);
        } else {
            mensagem_erro = "Algum atributo do cliente não foi informado";
        }
        if (mensagem_erro == null) {
            Visão<String> visão = getVisãoClientesCadastrados(cliente.getCpf());
            if (visão != null) {

                visão.setInfo(cliente.getVisão().getInfo());
                clientes_cadastradosComboBox.updateUI();
                clientes_cadastradosComboBox.setSelectedItem(visão);

            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCliente(java.awt.event.ActionEvent evt) {
        Visão<String> visão
                = (Visão<String>) clientes_cadastradosComboBox.getSelectedItem();
        String mensagem_erro = null;
        if (visão != null) {
            mensagem_erro = controlador.removerCliente(visão.getChave());
        } else {
            mensagem_erro = "Nenhum cliente selecionado";
        }
        if (mensagem_erro == null) {
            clientes_cadastrados.remove(visão);
            if (clientes_cadastrados.size() >= 1) {
                clientes_cadastradosComboBox.setSelectedIndex(0);
            } else {
                clientes_cadastradosComboBox.setSelectedIndex(-1);
            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Cliente obterClienteInformado() {
        String cpf = cpf_FormattedTextField.getText();
        String auxiliar = "   .   .   -  ";
        String aux_celular = "(  )       -    ";
        if (auxiliar.equals(cpf)) {
            return null;
        }
        if (cpf.isEmpty()) {
            return null; 
        }
        String nome = nomeTextField.getText();
        if (nome.isEmpty()) {
            return null;
        }
        String rg = rgTextField.getText();
        if (rg.isEmpty()) {
            return null;
        }
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            return null;
        }

        String telefone = telefone_clienteFormattedTextField.getText();
        if (telefone.equals(aux_celular)) {
            if (telefone.isEmpty()) {
                return null;
            }
        }
        String celular = celularFormattedTextFiel.getText();
        if (celular.equals(aux_celular)) {
            return null;
        }
        if (celular.isEmpty()) {
            return null;
        }
        String data_nascimento = data_nascimento_FormattedTextField.getText();
        if (data_nascimento.isEmpty()) {
            return null;
        }
        Data data_nova = Data.toData(data_nascimento);
        if (data_nova == null) {
            return null;
        }

         String sexo = null;
        if (masculinoRadioButton.isSelected()) {
            sexo = "m";
        } else if (femininoRadioButton.isSelected()) {
            sexo = "f";
        } else {
            return null;
        }
        
        if (sexo.isEmpty()) {
            return null;
        }

        String rua = ruaTextField.getText();
        if (rua.isEmpty()) {
            return null;
        }

        String numero = numeroTextField.getText();
        if (numero.isEmpty()) {
            return null;
        }

        String complemento = complementoTextField.getText();
        if (complemento.isEmpty()) {
            return null;
        }

        String bairro = bairroTextField.getText();
        if (bairro.isEmpty()) {
            return null;
        }

        String cidade = cidadeTextField.getText();
        if (cidade.isEmpty()) {
            return null;
        }

        String cep = cepFormattedTextField.getText();
        if (cep.isEmpty()) {
            return null;
        }

        String estado = estadoselecionarComboBox.getSelectedItem().toString();
        if (sexo.contains("Selecione")) {
            return null;
        }

        Endereço endereço = new Endereço(rua, numero, complemento, bairro, cidade, cep, estado);
        return new Cliente(cpf, nome, rg, data_nascimento, sexo, email, telefone, celular, endereço);
    }

    private void limparCamposTexto(java.awt.event.ActionEvent evt) {
        cpf_FormattedTextField.setText("");
        nomeTextField.setText("");
        estadoselecionarComboBox.setSelectedIndex(0);
        rgTextField.setText("");
        telefone_clienteFormattedTextField.setText("");
        celularFormattedTextFiel.setText("");

        data_nascimento_FormattedTextField.setText("");
        
        emailTextField.setText("");
        estadoselecionarComboBox.setActionCommand("");
        cepFormattedTextField.setText("");
        bairroTextField.setText("");
        numeroTextField.setText("");
        cidadeTextField.setText("");
        ruaTextField.setText("");
        complementoTextField.setText("");
    }

    private Visão<String> getVisãoClientesCadastrados(String chave) {
        for (Visão<String> visão : clientes_cadastrados) {
            if (visão.getChave().equals(chave)) {
                return visão;
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        sexoGroup = new javax.swing.ButtonGroup();
        endereçoPanel = new javax.swing.JPanel();
        ruaLabel = new javax.swing.JLabel();
        numero_casaLabel = new javax.swing.JLabel();
        complementoLabel = new javax.swing.JLabel();
        barrioLabel = new javax.swing.JLabel();
        cepLabel = new javax.swing.JLabel();
        cidadeLabel = new javax.swing.JLabel();
        estadoselecionarComboBox = new javax.swing.JComboBox<>();
        estadoLabel = new javax.swing.JLabel();
        ruaTextField = new javax.swing.JTextField();
        bairroTextField = new javax.swing.JTextField();
        cidadeTextField = new javax.swing.JTextField();
        numeroTextField = new javax.swing.JTextField();
        complementoTextField = new javax.swing.JTextField();
        funçoesPanel = new javax.swing.JPanel();
        inserirButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();
        alterarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        cepFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        ContatoPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        celularLabel = new javax.swing.JLabel();
        celularFormattedTextFiel = new javax.swing.JFormattedTextField();
        telefoneLabel = new javax.swing.JLabel();
        telefone_clienteFormattedTextField = new javax.swing.JFormattedTextField();
        emailTextField = new javax.swing.JTextField();
        dadosPessoaisPanel = new javax.swing.JPanel();
        Nome_Label = new javax.swing.JLabel();
        cpf_Label = new javax.swing.JLabel();
        cpf_FormattedTextField = new javax.swing.JFormattedTextField();
        rg_Label = new javax.swing.JLabel();
        sexoLabel = new javax.swing.JLabel();
        data_nascimento_Label = new javax.swing.JLabel();
        data_nascimento_FormattedTextField = new javax.swing.JFormattedTextField();
        rgTextField = new javax.swing.JTextField();
        nomeTextField = new javax.swing.JTextField();
        masculinoRadioButton = new javax.swing.JRadioButton();
        femininoRadioButton = new javax.swing.JRadioButton();
        clientes_cadastradosComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Cliente");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setResizable(false);

        endereçoPanel.setBackground(new java.awt.Color(255, 255, 255));
        endereçoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        ruaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ruaLabel.setText("Rua");

        numero_casaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        numero_casaLabel.setText("Nº");

        complementoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        complementoLabel.setText("Compl.");

        barrioLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        barrioLabel.setText("Bairro");

        cepLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cepLabel.setText("Cep");
        cepLabel.setToolTipText("");

        cidadeLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cidadeLabel.setText("Cidade");

        estadoselecionarComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        estadoselecionarComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        estadoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        estadoLabel.setText("Estado");

        ruaTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ruaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruaTextFieldActionPerformed(evt);
            }
        });

        bairroTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cidadeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        numeroTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        complementoTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        complementoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complementoTextFieldActionPerformed(evt);
            }
        });

        inserirButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inserirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_add.png"))); // NOI18N
        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirClienteButtonActionPerformed(evt);
            }
        });

        consultarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/magnifier.png"))); // NOI18N
        consultarButton.setText("Consultar");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarClienteButtonActionPerformed(evt);
            }
        });

        alterarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_edit.png"))); // NOI18N
        alterarButton.setText("Alterar");
        alterarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarClienteButtonActionPerformed(evt);
            }
        });

        removerButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_delete.png"))); // NOI18N
        removerButton.setText("Remover");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerClienteButtonActionPerformed(evt);
            }
        });

        limparButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        limparButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil_delete.png"))); // NOI18N
        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpar_dados_interfacesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout funçoesPanelLayout = new javax.swing.GroupLayout(funçoesPanel);
        funçoesPanel.setLayout(funçoesPanelLayout);
        funçoesPanelLayout.setHorizontalGroup(
            funçoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funçoesPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(inserirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consultarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(limparButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        funçoesPanelLayout.setVerticalGroup(
            funçoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(funçoesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(funçoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultarButton)
                    .addComponent(inserirButton)
                    .addComponent(alterarButton)
                    .addComponent(removerButton)
                    .addComponent(limparButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        try {
            cepFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cepFormattedTextField.setToolTipText("");
        cepFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cepFormattedTextField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cepFormattedTextFieldInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        cepFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cepFormattedTextFieldKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Digite o CEP e Pressione Enter para busca automática");

        javax.swing.GroupLayout endereçoPanelLayout = new javax.swing.GroupLayout(endereçoPanel);
        endereçoPanel.setLayout(endereçoPanelLayout);
        endereçoPanelLayout.setHorizontalGroup(
            endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endereçoPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cepLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(barrioLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(estadoLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21)
                .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cepFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(endereçoPanelLayout.createSequentialGroup()
                        .addComponent(estadoselecionarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numero_casaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(complementoLabel)
                    .addComponent(cidadeLabel)
                    .addComponent(ruaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ruaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(complementoTextField)
                    .addComponent(cidadeTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(endereçoPanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(funçoesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        endereçoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bairroTextField, cepFormattedTextField, cidadeTextField, complementoTextField, ruaTextField});

        endereçoPanelLayout.setVerticalGroup(
            endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endereçoPanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(endereçoPanelLayout.createSequentialGroup()
                        .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ruaLabel)
                            .addComponent(ruaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cepLabel)
                            .addComponent(cepFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cidadeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cidadeLabel)
                            .addComponent(barrioLabel)
                            .addComponent(bairroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(endereçoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(complementoLabel)
                            .addComponent(estadoLabel)
                            .addComponent(estadoselecionarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numero_casaLabel)
                            .addComponent(numeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(complementoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(funçoesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ContatoPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContatoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N
        ContatoPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));

        emailLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        emailLabel.setText("E-mail");

        celularLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        celularLabel.setText("Celular");

        try {
            celularFormattedTextFiel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        celularFormattedTextFiel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        telefoneLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        telefoneLabel.setText("Telefone");

        try {
            telefone_clienteFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)  ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefone_clienteFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        emailTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout ContatoPanelLayout = new javax.swing.GroupLayout(ContatoPanel);
        ContatoPanel.setLayout(ContatoPanelLayout);
        ContatoPanelLayout.setHorizontalGroup(
            ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContatoPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(telefoneLabel)
                    .addComponent(emailLabel)
                    .addComponent(celularLabel))
                .addGap(18, 18, 18)
                .addGroup(ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celularFormattedTextFiel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefone_clienteFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ContatoPanelLayout.setVerticalGroup(
            ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContatoPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContatoPanelLayout.createSequentialGroup()
                        .addGroup(ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celularFormattedTextFiel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(celularLabel))
                .addGap(9, 9, 9)
                .addGroup(ContatoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefoneLabel)
                    .addComponent(telefone_clienteFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ContatoPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {celularFormattedTextFiel, emailTextField, telefone_clienteFormattedTextField});

        dadosPessoaisPanel.setBackground(new java.awt.Color(255, 255, 255));
        dadosPessoaisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 15))); // NOI18N

        Nome_Label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Nome_Label.setText("Nome");

        cpf_Label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cpf_Label.setText("CPF");

        try {
            cpf_FormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpf_FormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        rg_Label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rg_Label.setText("Rg");

        sexoLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        sexoLabel.setText("Sexo");

        data_nascimento_Label.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        data_nascimento_Label.setText("Data De Nacimento");

        try {
            data_nascimento_FormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        data_nascimento_FormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        rgTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nomeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        masculinoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        sexoGroup.add(masculinoRadioButton);
        masculinoRadioButton.setText("Masculino");

        femininoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        sexoGroup.add(femininoRadioButton);
        femininoRadioButton.setText("Feminino");

        javax.swing.GroupLayout dadosPessoaisPanelLayout = new javax.swing.GroupLayout(dadosPessoaisPanel);
        dadosPessoaisPanel.setLayout(dadosPessoaisPanelLayout);
        dadosPessoaisPanelLayout.setHorizontalGroup(
            dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosPessoaisPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Nome_Label)
                    .addComponent(rg_Label))
                .addGap(18, 18, 18)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dadosPessoaisPanelLayout.createSequentialGroup()
                        .addComponent(rgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(data_nascimento_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(data_nascimento_FormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cpf_Label)
                    .addComponent(sexoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dadosPessoaisPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(masculinoRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femininoRadioButton))
                    .addComponent(cpf_FormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        dadosPessoaisPanelLayout.setVerticalGroup(
            dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosPessoaisPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nome_Label)
                    .addComponent(cpf_Label)
                    .addComponent(cpf_FormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dadosPessoaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rg_Label)
                    .addComponent(rgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data_nascimento_Label)
                    .addComponent(data_nascimento_FormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexoLabel)
                    .addComponent(masculinoRadioButton)
                    .addComponent(femininoRadioButton))
                .addGap(23, 23, 23))
        );

        dadosPessoaisPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cpf_FormattedTextField, data_nascimento_FormattedTextField, nomeTextField, rgTextField});

        clientes_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        clientes_cadastradosComboBox.setModel((new DefaultComboBoxModel (clientes_cadastrados)));
        clientes_cadastradosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientes_cadastradosComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Clientes cadastrados ");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CLIENTES");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dadosPessoaisPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ContatoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endereçoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(dadosPessoaisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContatoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endereçoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clientes_cadastradosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientes_cadastradosComboBoxActionPerformed

    }//GEN-LAST:event_clientes_cadastradosComboBoxActionPerformed

    private void inserirClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirClienteButtonActionPerformed
        inserirCliente(evt);
    }//GEN-LAST:event_inserirClienteButtonActionPerformed

    private void consultarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarClienteButtonActionPerformed
        consultarCliente(evt);
    }//GEN-LAST:event_consultarClienteButtonActionPerformed

    private void alterarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarClienteButtonActionPerformed
        alterarCliente(evt);
    }//GEN-LAST:event_alterarClienteButtonActionPerformed

    private void removerClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerClienteButtonActionPerformed
        removerCliente(evt);
        limparCamposTexto(evt);
        
        
    }//GEN-LAST:event_removerClienteButtonActionPerformed

    private void limpar_dados_interfacesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpar_dados_interfacesButtonActionPerformed
        limparCamposTexto(evt);
     
    }//GEN-LAST:event_limpar_dados_interfacesButtonActionPerformed

    private void ruaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ruaTextFieldActionPerformed

    private void complementoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complementoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_complementoTextFieldActionPerformed

    private void cepFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cepFormattedTextFieldKeyPressed
        // TODO add your handling code here:
       if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

            Endereço obj = Endereço.buscaCep(cepFormattedTextField.getText());
            if(obj != null){
            ruaTextField.setText(obj.getRua());
            bairroTextField.setText(obj.getBairro());
            cidadeTextField.setText(obj.getCidade());
            estadoselecionarComboBox.setSelectedItem(obj.getEstado());
            numeroTextField.grabFocus();
            }
            else{
                cepFormattedTextField.setText("");
                cepFormattedTextField.grabFocus();
            }
        }
    }//GEN-LAST:event_cepFormattedTextFieldKeyPressed

    private void cepFormattedTextFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cepFormattedTextFieldInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cepFormattedTextFieldInputMethodTextChanged

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContatoPanel;
    private javax.swing.JLabel Nome_Label;
    private javax.swing.JButton alterarButton;
    private javax.swing.JTextField bairroTextField;
    private javax.swing.JLabel barrioLabel;
    private javax.swing.JFormattedTextField celularFormattedTextFiel;
    private javax.swing.JLabel celularLabel;
    private javax.swing.JFormattedTextField cepFormattedTextField;
    private javax.swing.JLabel cepLabel;
    private javax.swing.JLabel cidadeLabel;
    private javax.swing.JTextField cidadeTextField;
    private javax.swing.JComboBox clientes_cadastradosComboBox;
    private javax.swing.JLabel complementoLabel;
    private javax.swing.JTextField complementoTextField;
    private javax.swing.JButton consultarButton;
    private javax.swing.JFormattedTextField cpf_FormattedTextField;
    private javax.swing.JLabel cpf_Label;
    private javax.swing.JPanel dadosPessoaisPanel;
    private javax.swing.JFormattedTextField data_nascimento_FormattedTextField;
    private javax.swing.JLabel data_nascimento_Label;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel endereçoPanel;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JComboBox<String> estadoselecionarComboBox;
    private javax.swing.JRadioButton femininoRadioButton;
    private javax.swing.JPanel funçoesPanel;
    private javax.swing.JButton inserirButton;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton limparButton;
    private javax.swing.JRadioButton masculinoRadioButton;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JTextField numeroTextField;
    private javax.swing.JLabel numero_casaLabel;
    private javax.swing.JButton removerButton;
    private javax.swing.JTextField rgTextField;
    private javax.swing.JLabel rg_Label;
    private javax.swing.JLabel ruaLabel;
    private javax.swing.JTextField ruaTextField;
    private javax.swing.ButtonGroup sexoGroup;
    private javax.swing.JLabel sexoLabel;
    private javax.swing.JLabel telefoneLabel;
    private javax.swing.JFormattedTextField telefone_clienteFormattedTextField;
    // End of variables declaration//GEN-END:variables
}
