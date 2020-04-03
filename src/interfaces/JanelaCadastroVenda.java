package interfaces;

import java.awt.Color;
import javax.swing.UIManager;
import java.util.Vector;
import controle.ControladorCadastroHerbicida;
import controle.ControladorCadastroVenda;
import entidade.Cliente;
import entidade.Herbicida;
import entidade.Herbicida.Formulacao;
import entidade.Visão;
import interfaces.dialgo.DialogPagamentoVenda;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import util.Data;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Utilitarios;

public class JanelaCadastroVenda extends javax.swing.JFrame {

    ControladorCadastroVenda controlador;
    String data_venda; //Converter Sql

    DefaultTableModel carrinho;

    private static DefaultListModel estoques_cadastrados;
    private static Vector<Visão<Integer>> herbicidas_cadastrados = new Vector<Visão<Integer>>();
    private static Vector<Visão<String>> clientes_cadastrados = new Vector<Visão<String>>();

    public JanelaCadastroVenda(ControladorCadastroVenda controlador) {
        this.controlador = controlador; 
        herbicidas_cadastrados = Herbicida.getVisões();
        clientes_cadastrados = Cliente.getVisões();
        initComponents();

    }
  
    @SuppressWarnings("unchecked")

    //---------------------Inserção-----------
    //---------------------Vião dos Dados-----------
    private Visão<Integer> getVisãoHerbicidasCadastrados(int chave) {
        for (Visão<Integer> visão : herbicidas_cadastrados) {
            if (visão.getChave().equals(chave)) {
                return visão;
            }
        }
        return null;
    }

    public void InserirItemVenda() {

        boolean consulta = ConsultarItemVenda();
        if (consulta == false) {
            JOptionPane.showMessageDialog(this, "Erro: Quantidade de Produto nao inserida");
        }

    }

    public String ConsultarIdCliente() {

        Visão<String> visão = (Visão<String>) clientes_cadastradosComboBox.getSelectedItem();
        Cliente cliente = null;
        String cpf = null;
        String mensagem_erro = null;
        if (visão != null) {
            cliente = Cliente.buscarCliente(visão.getChave());
            cpf = cliente.getCpf();
            if (cliente == null) {
                mensagem_erro = "Herbicida não cadastrado";
                return null;
            }
        }
        return cpf;
    }

    //---------------------Consultar----------------
    public boolean ConsultarItemVenda() {
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

        int quantidade_solicitada = Integer.parseInt(quantidadeTextField.getText());

        if (quantidade_solicitada > herbicida.getQuantidade_estoque()) {
            JOptionPane.showMessageDialog(this, "A quantidade informada encontra-se indisponivel no momento, total atual: " + herbicida.getQuantidade_estoque() + " Produtos");
        } else {

            if (mensagem_erro == null) {
                float total = Float.parseFloat(valor_totalTextField.getText().trim());

                if (quantidadeTextField.getText().equals("")) {
                    return false;
                }
                int quantidade = Integer.parseInt(quantidadeTextField.getText().trim());
                float subtotal = (quantidade * herbicida.getValor_unitario());

                carrinho = (DefaultTableModel) carrinho_comprasTable.getModel();
                carrinho.addRow(new Object[]{herbicida.getId(),
                    herbicida.getNome(),
                    herbicida.getValor_unitario(),
                    quantidade,
                    subtotal
                });
                total = total + subtotal;
                valor_totalTextField.setText(String.valueOf(total));

            } else {
                JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        return true;
    }
    
  private void LimparTelarVenda(){
      int vazia = carrinho_comprasTable.getRowCount();

      if(vazia != 0){
         carrinho.setRowCount(0);
      }
      valor_totalTextField.setText("0");
      clientes_cadastradosComboBox.setSelectedIndex(-1);
      herbicidas_cadastradosComboBox.setSelectedIndex(-1);
  } 
  
    
    //Remoção da JTable e Atualizaçao do valor
    public void RemoverHerbicidaCarrinho() {

        if (carrinho_comprasTable.getSelectedRow() >= 0) {
            carrinho.removeRow(carrinho_comprasTable.getSelectedRow());
            carrinho_comprasTable.setModel(carrinho);
            int linha = carrinho_comprasTable.getSelectedRow() - 1;

            float soma = 0;
            for (int i = 0; i < carrinho_comprasTable.getRowCount(); i++) {
                soma += Float.parseFloat(carrinho_comprasTable.getValueAt(i, 4).toString());
            }
            valor_totalTextField.setText(String.valueOf(soma));
        } else {
            JOptionPane.showMessageDialog(this, "Erro: Escolha uma produto na tabela para remover");
        }
    }
    //---------------------Listar para J Table -- JList----------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dados_clientePanel = new javax.swing.JPanel();
        clientes_cadastradosComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        data_vendaFormattedTextField = new javax.swing.JFormattedTextField();
        dataLabel = new javax.swing.JLabel();
        dados_clientePanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        carrinho_comprasTable = new javax.swing.JTable();
        dados_clientePanel2 = new javax.swing.JPanel();
        herbicidas_cadastradosComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        quantidadeTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        btIncluirProduto2 = new javax.swing.JButton();
        btIncluirProduto1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        consultarButton = new javax.swing.JButton();
        alterarButton1 = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        valor_totalTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        salvarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        usuario_logadoLabel = new javax.swing.JLabel();
        cargo_logadoLabel = new javax.swing.JLabel();
        nivel_logadoLabel = new javax.swing.JLabel();
        dados_vendasPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        estoques_cadastradosList = new javax.swing.JList();
        nova_vendaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Usuários - AgroHerbicides");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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
        jLabel1.setText("Painel de Vendas");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dados_clientePanel.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        clientes_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        clientes_cadastradosComboBox.setModel((new DefaultComboBoxModel (clientes_cadastrados)));
        clientes_cadastradosComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                clientes_cadastradosComboBoxFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cliente:");

        data_vendaFormattedTextField.setText("  /  /");
        data_vendaFormattedTextField.setEnabled(false);

        dataLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataLabel.setText("Data:");

        javax.swing.GroupLayout dados_clientePanelLayout = new javax.swing.GroupLayout(dados_clientePanel);
        dados_clientePanel.setLayout(dados_clientePanelLayout);
        dados_clientePanelLayout.setHorizontalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(dataLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(data_vendaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dados_clientePanelLayout.setVerticalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataLabel)
                    .addComponent(data_vendaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        dados_clientePanel3.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrinho de Compras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        carrinho_comprasTable.setAutoCreateRowSorter(true);
        carrinho_comprasTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        carrinho_comprasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Produto", "Valor unit.", "Quantidade", "Valor total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        carrinho_comprasTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        carrinho_comprasTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(carrinho_comprasTable);
        if (carrinho_comprasTable.getColumnModel().getColumnCount() > 0) {
            carrinho_comprasTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout dados_clientePanel3Layout = new javax.swing.GroupLayout(dados_clientePanel3);
        dados_clientePanel3.setLayout(dados_clientePanel3Layout);
        dados_clientePanel3Layout.setHorizontalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );
        dados_clientePanel3Layout.setVerticalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );

        dados_clientePanel2.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        herbicidas_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        herbicidas_cadastradosComboBox.setModel(new DefaultComboBoxModel (herbicidas_cadastrados));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Produto:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Quant.:");

        quantidadeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        btIncluirProduto2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btIncluirProduto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3 - New Venda.png"))); // NOI18N
        btIncluirProduto2.setText("Incluir no Carrinho");
        btIncluirProduto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dados_clientePanel2Layout = new javax.swing.GroupLayout(dados_clientePanel2);
        dados_clientePanel2.setLayout(dados_clientePanel2Layout);
        dados_clientePanel2Layout.setHorizontalGroup(
            dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_clientePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(herbicidas_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dados_clientePanel2Layout.createSequentialGroup()
                        .addComponent(quantidadeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btIncluirProduto2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dados_clientePanel2Layout.setVerticalGroup(
            dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(herbicidas_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantidadeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btIncluirProduto2))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btIncluirProduto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btIncluirProduto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3_remove Cart 32x32.png"))); // NOI18N
        btIncluirProduto1.setText("Remover do Carrinho");
        btIncluirProduto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto1ActionPerformed(evt);
            }
        });

        consultarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/magnifier.png"))); // NOI18N
        consultarButton.setText("Consultar");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarButtonconsultarClienteButtonActionPerformed(evt);
            }
        });

        alterarButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/package_go.png"))); // NOI18N
        alterarButton1.setText("Editar (Admin)");
        alterarButton1.setToolTipText("");
        alterarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarButton1alterarClienteButtonActionPerformed(evt);
            }
        });

        removerButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/package_delete.png"))); // NOI18N
        removerButton.setText("Remover (Admin)");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonremoverClienteButtonActionPerformed(evt);
            }
        });

        limparButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        limparButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pencil_delete.png"))); // NOI18N
        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonlimpar_dados_interfacesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(consultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(alterarButton1)
                .addGap(26, 26, 26)
                .addComponent(removerButton)
                .addGap(27, 27, 27)
                .addComponent(limparButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {alterarButton1, consultarButton, limparButton, removerButton});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alterarButton1)
                    .addComponent(removerButton)
                    .addComponent(limparButton))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {alterarButton1, consultarButton, limparButton, removerButton});

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Total R$:");

        valor_totalTextField.setEditable(false);
        valor_totalTextField.setBackground(new java.awt.Color(255, 153, 153));
        valor_totalTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        valor_totalTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        valor_totalTextField.setText("0.0");
        valor_totalTextField.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        salvarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salvarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tick.png"))); // NOI18N
        salvarButton.setText("EfetuarPagamento");
        salvarButton.setToolTipText("");
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3excluir.png"))); // NOI18N
        cancelarButton.setText("Cancelar Venda");
        cancelarButton.setToolTipText("");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        usuario_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        usuario_logadoLabel.setText("Usuário Logado: Paulo Souza");

        cargo_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cargo_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        cargo_logadoLabel.setText("Usuário Logado: Paulo Souza");

        nivel_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nivel_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        nivel_logadoLabel.setText("Usuário Logado: Paulo Souza");

        dados_vendasPanel.setBackground(new java.awt.Color(255, 255, 255));
        dados_vendasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas Realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        dados_vendasPanel.setToolTipText("");

        estoques_cadastradosList.setModel(new DefaultListModel());
        estoques_cadastradosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(estoques_cadastradosList);

        javax.swing.GroupLayout dados_vendasPanelLayout = new javax.swing.GroupLayout(dados_vendasPanel);
        dados_vendasPanel.setLayout(dados_vendasPanelLayout);
        dados_vendasPanelLayout.setHorizontalGroup(
            dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        dados_vendasPanelLayout.setVerticalGroup(
            dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        nova_vendaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nova_vendaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3novo.png"))); // NOI18N
        nova_vendaButton.setText("Nova Venda");
        nova_vendaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nova_vendaButtonconsultarClienteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dados_clientePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dados_clientePanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dados_vendasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nova_vendaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dados_clientePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(valor_totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btIncluirProduto1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(salvarButton)
                                .addGap(37, 37, 37)
                                .addComponent(cancelarButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(usuario_logadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargo_logadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nivel_logadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluirProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nova_vendaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dados_clientePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valor_totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salvarButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargo_logadoLabel)
                            .addComponent(usuario_logadoLabel)
                            .addComponent(nivel_logadoLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dados_clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dados_clientePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dados_vendasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelarButton, salvarButton, valor_totalTextField});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date agora = new Date();
        SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
        String data_atual = dataBr.format(agora);
        data_vendaFormattedTextField.setText(data_atual);

        UIManager.put("herbicidas_cadastradosComboBox.disabledForeground", Color.BLACK);
        UIManager.put("herbicidas_cadastradosComboBox.disabledBackground", Color.WHITE);
    }//GEN-LAST:event_formWindowActivated

    private void btIncluirProduto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto1ActionPerformed
        RemoverHerbicidaCarrinho();
    }//GEN-LAST:event_btIncluirProduto1ActionPerformed

    private void btIncluirProduto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto2ActionPerformed
        InserirItemVenda();
    }//GEN-LAST:event_btIncluirProduto2ActionPerformed

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed
        // TODO add your handling code here:

        String cliente_id = ConsultarIdCliente();

        float valor_daVenda;

        valor_daVenda = Float.parseFloat(valor_totalTextField.getText());
        DialogPagamentoVenda pagamento = new DialogPagamentoVenda(controlador, cliente_id, valor_daVenda, carrinho);
        pagamento.show(true);


    }//GEN-LAST:event_salvarButtonActionPerformed

    private void consultarButtonconsultarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarButtonconsultarClienteButtonActionPerformed
        // consultarVenda(evt);
    }//GEN-LAST:event_consultarButtonconsultarClienteButtonActionPerformed

    private void alterarButton1alterarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButton1alterarClienteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alterarButton1alterarClienteButtonActionPerformed

    private void removerButtonremoverClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonremoverClienteButtonActionPerformed
        // removerVenda(evt);
    }//GEN-LAST:event_removerButtonremoverClienteButtonActionPerformed

    private void limparButtonlimpar_dados_interfacesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonlimpar_dados_interfacesButtonActionPerformed
        LimparTelarVenda();
    }//GEN-LAST:event_limparButtonlimpar_dados_interfacesButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
         LimparTelarVenda();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void clientes_cadastradosComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_clientes_cadastradosComboBoxFocusLost


    }//GEN-LAST:event_clientes_cadastradosComboBoxFocusLost

    private void nova_vendaButtonconsultarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nova_vendaButtonconsultarClienteButtonActionPerformed
          LimparTelarVenda();
    }//GEN-LAST:event_nova_vendaButtonconsultarClienteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton1;
    private javax.swing.JButton btIncluirProduto1;
    private javax.swing.JButton btIncluirProduto2;
    private javax.swing.JButton cancelarButton;
    public javax.swing.JLabel cargo_logadoLabel;
    private javax.swing.JTable carrinho_comprasTable;
    private javax.swing.JComboBox clientes_cadastradosComboBox;
    private javax.swing.JButton consultarButton;
    private javax.swing.JPanel dados_clientePanel;
    private javax.swing.JPanel dados_clientePanel2;
    private javax.swing.JPanel dados_clientePanel3;
    private javax.swing.JPanel dados_vendasPanel;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JFormattedTextField data_vendaFormattedTextField;
    private javax.swing.JList estoques_cadastradosList;
    private javax.swing.JComboBox herbicidas_cadastradosComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limparButton;
    public javax.swing.JLabel nivel_logadoLabel;
    private javax.swing.JButton nova_vendaButton;
    private javax.swing.JFormattedTextField quantidadeTextField;
    private javax.swing.JButton removerButton;
    private javax.swing.JButton salvarButton;
    public javax.swing.JLabel usuario_logadoLabel;
    private javax.swing.JFormattedTextField valor_totalTextField;
    // End of variables declaration//GEN-END:variables

}
