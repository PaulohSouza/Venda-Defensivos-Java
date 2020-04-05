package interfaces;

import controle.ControladorCadastroVenda;
import controle.ControladorGerenciamentoVenda;
import entidade.Cliente;
import entidade.Herbicida;
import entidade.Venda;
import entidade.Visão;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static persistencia.BD.comando;
import util.Data;
import util.Utilitarios;

public class JanelaGerenciamentoVenda extends javax.swing.JDialog {

    ControladorGerenciamentoVenda controlador;
    DefaultTableModel carrinho;

    private static DefaultListModel vendas_cadastradas;
    private static Vector<Visão<Integer>> herbicidas_cadastrados = new Vector<Visão<Integer>>();
    private static Vector<Visão<String>> clientes_cadastrados = new Vector<Visão<String>>();

    public JanelaGerenciamentoVenda(ControladorGerenciamentoVenda controlador) {
        this.controlador = controlador;
        herbicidas_cadastrados = Herbicida.getVisões();
        clientes_cadastrados = Cliente.getVisões();
        initComponents();
        inicializarVendasCadastradas();
    }

    private void inicializarVendasCadastradas() {

        vendas_cadastradas = (DefaultListModel) vendas_cadastradasList.getModel();
        Vector<Visão<Integer>> visões = Venda.getVisões();
        vendas_cadastradas.removeAllElements();
        for (Visão<Integer> visão : visões) {
            vendas_cadastradas.addElement(visão);
        }
 }
    @SuppressWarnings("unchecked")

    private Visão<String> getClientesCadastradosVisão(String cpf) {
        for (Visão<String> visão : clientes_cadastrados) {
            if (visão.getChave().equals(cpf)) {
                return visão;
            }
        }
        return null;
    }

    private void preencherCadastro(){
         String selected = vendas_cadastradasList.getSelectedValue().toString();

        String[] partesString = selected.split(":");
       // System.out.println(partesString[0]);

        ResultSet rs = null;
        String sql = "SELECT tab_itemvenda.id, tab_itemvenda.herbicida_id, herbicida.nome, \n"
                + "tab_itemvenda.valor_unitario, tab_itemvenda.quantidade, tab_itemvenda.subtotal FROM tab_vendas, tab_itemvenda, herbicida WHERE "
                + "tab_itemvenda.venda_id = tab_vendas.id_venda and herbicida.id = tab_itemvenda.herbicida_id and"
                + " tab_itemvenda.venda_id = '" + partesString[0] + "';";
        try {
            rs = comando.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JanelaGerenciamentoVenda.class.getName()).log(Level.SEVERE, null, ex);
        }

        carrinho = (DefaultTableModel) carrinho_comprasTable.getModel();
        carrinho.setRowCount(0);

        try {
            while (rs.next()) {
                String codigo = "" + rs.getInt("id");
                String produto = "" + rs.getInt("herbicida_id");
                String valor = "" + rs.getFloat("valor_unitario");
                String quantidade = "" + rs.getInt("quantidade");
                String valorTotal = "" + rs.getFloat("subtotal");

                Object[] row = {codigo, produto, valor, quantidade, valorTotal};
                carrinho.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JanelaGerenciamentoVenda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dados_clientePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        clientes_cadastradosComboBox = new javax.swing.JComboBox();
        data_vendaFormattedTextField = new javax.swing.JFormattedTextField();
        dataLabel = new javax.swing.JLabel();
        valor_produtosTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        valor_produtosLabel = new javax.swing.JLabel();
        descontoLabel = new javax.swing.JLabel();
        descontoTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        precisa_nfeCheckBox = new javax.swing.JCheckBox();
        pagamento_tipoCombobox = new javax.swing.JComboBox<>();
        forma_pagamentoLabel = new javax.swing.JLabel();
        valorfinalLabel = new javax.swing.JLabel();
        valor_finalTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        sequencialTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dados_vendasPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vendas_cadastradasList = new javax.swing.JList();
        consultarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dados_clientePanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        carrinho_comprasTable = new javax.swing.JTable();
        alterarButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        dados_clientePanel.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cliente:");

        clientes_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        clientes_cadastradosComboBox.setModel((new DefaultComboBoxModel (clientes_cadastrados)));
        clientes_cadastradosComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                clientes_cadastradosComboBoxFocusLost(evt);
            }
        });

        data_vendaFormattedTextField.setText("  /  /");
        data_vendaFormattedTextField.setEnabled(false);

        dataLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataLabel.setText("Data:");

        valor_produtosTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        valor_produtosTextField.setEnabled(false);

        valor_produtosLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_produtosLabel.setText("Valor Produtos:");

        descontoLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descontoLabel.setText("Desconto");

        descontoTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        precisa_nfeCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        precisa_nfeCheckBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precisa_nfeCheckBox.setText("Precisa Nota Fiscal Eletrônica");
        precisa_nfeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precisa_nfeCheckBoxActionPerformed(evt);
            }
        });

        pagamento_tipoCombobox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pagamento_tipoCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartao_Credito", "Cartao_Debito" }));

        forma_pagamentoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        forma_pagamentoLabel.setText(" Pagamento:");

        valorfinalLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorfinalLabel.setText("ValorFinal:");

        valor_finalTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        valor_finalTextField.setEnabled(false);

        sequencialTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        sequencialTextField.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Codigo:");

        jLabel4.setText("* Atenção Usuário: Campos de Alteração: Cliente, data, valor desconto, Precisa Nfe..");

        javax.swing.GroupLayout dados_clientePanelLayout = new javax.swing.GroupLayout(dados_clientePanel);
        dados_clientePanel.setLayout(dados_clientePanelLayout);
        dados_clientePanelLayout.setHorizontalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_clientePanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descontoLabel)
                            .addComponent(dataLabel)
                            .addComponent(jLabel2)))
                    .addComponent(forma_pagamentoLabel)
                    .addComponent(valor_produtosLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_clientePanelLayout.createSequentialGroup()
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descontoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(data_vendaFormattedTextField))
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(sequencialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(precisa_nfeCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(dados_clientePanelLayout.createSequentialGroup()
                        .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dados_clientePanelLayout.createSequentialGroup()
                        .addComponent(valor_produtosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(valorfinalLabel)
                        .addGap(18, 18, 18)
                        .addComponent(valor_finalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_clientePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
        dados_clientePanelLayout.setVerticalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_clientePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataLabel)
                    .addComponent(data_vendaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sequencialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descontoLabel)
                    .addComponent(descontoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precisa_nfeCheckBox))
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_clientePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(forma_pagamentoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valor_produtosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valor_produtosLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dados_clientePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valor_finalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorfinalLabel))
                        .addGap(41, 41, 41)))
                .addComponent(jLabel4))
        );

        dados_clientePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {clientes_cadastradosComboBox, data_vendaFormattedTextField, descontoTextField, valor_produtosTextField});

        dados_vendasPanel.setBackground(new java.awt.Color(255, 255, 255));
        dados_vendasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas Realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        dados_vendasPanel.setToolTipText("");

        vendas_cadastradasList.setModel(new DefaultListModel());
        vendas_cadastradasList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vendas_cadastradasList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendas_cadastradasListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vendas_cadastradasList);

        consultarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/magnifier.png"))); // NOI18N
        consultarButton.setText("Consultar Dados");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarButtonconsultarClienteButtonActionPerformed(evt);
            }
        });

        removerButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        removerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/package_delete.png"))); // NOI18N
        removerButton.setText("Remover Venda");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonremoverClienteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dados_vendasPanelLayout = new javax.swing.GroupLayout(dados_vendasPanel);
        dados_vendasPanel.setLayout(dados_vendasPanelLayout);
        dados_vendasPanelLayout.setHorizontalGroup(
            dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dados_vendasPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dados_vendasPanelLayout.createSequentialGroup()
                        .addComponent(consultarButton)
                        .addGap(18, 18, 18)
                        .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );
        dados_vendasPanelLayout.setVerticalGroup(
            dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_vendasPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dados_vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Painel de Gerenciamento de Vendas (Administrador)");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dados_clientePanel3.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mercadoria Adquirida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

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

        javax.swing.GroupLayout dados_clientePanel3Layout = new javax.swing.GroupLayout(dados_clientePanel3);
        dados_clientePanel3.setLayout(dados_clientePanel3Layout);
        dados_clientePanel3Layout.setHorizontalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        dados_clientePanel3Layout.setVerticalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        alterarButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alterarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/package_go.png"))); // NOI18N
        alterarButton1.setText("Salvar Alteração de Venda");
        alterarButton1.setToolTipText("");
        alterarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarButton1alterarClienteButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Dados da Venda");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Produtos Adquiridos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(140, 140, 140))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(alterarButton1))
                    .addComponent(dados_clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(dados_clientePanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dados_vendasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(284, 284, 284))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dados_vendasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dados_clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dados_clientePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientes_cadastradosComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_clientes_cadastradosComboBoxFocusLost

    }//GEN-LAST:event_clientes_cadastradosComboBoxFocusLost

    private void precisa_nfeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precisa_nfeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precisa_nfeCheckBoxActionPerformed

    private void consultarButtonconsultarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarButtonconsultarClienteButtonActionPerformed
        Visão<Integer> visão;
        Venda venda = null;
        String mensagem_erro = null;
        if (vendas_cadastradasList.getSelectedValue() != null) {
            visão = (Visão<Integer>) vendas_cadastradasList.getSelectedValue();
            venda = Venda.buscarVenda(visão.getChave());
         
            if (venda == null) {
                mensagem_erro = "Estoque não encontrado no banco de dados";
            }
        } else {
            mensagem_erro = "Nenhum Estoque selecionado";
        }
        if (mensagem_erro == null) {
            String aux_data = String.valueOf(venda.getData_venda());
            Visão<String> visão1 = getClientesCadastradosVisão(venda.getCpf_cliente());
            sequencialTextField.setText(venda.getSequencial() + "");
            clientes_cadastradosComboBox.setSelectedItem(visão1);
            sequencialTextField.setText(String.valueOf(venda.getSequencial()));
            valor_produtosTextField.setText(String.valueOf(venda.getValor_produtos()));
            valor_finalTextField.setText(String.valueOf(venda.getValor_total()));  
            Data data = Data.toData(aux_data);
            data_vendaFormattedTextField.setText(String.valueOf(data));
            precisa_nfeCheckBox.setSelected(venda.getPrecisa_nfe());
            pagamento_tipoCombobox.setSelectedIndex(venda.getPagamento_tipo().ordinal());
            descontoTextField.setText(String.valueOf(venda.getValor_desconto()));
            preencherCadastro();
        } else {
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_consultarButtonconsultarClienteButtonActionPerformed

    private void alterarButton1alterarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButton1alterarClienteButtonActionPerformed
   
        
    }//GEN-LAST:event_alterarButton1alterarClienteButtonActionPerformed

    private void removerButtonremoverClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonremoverClienteButtonActionPerformed
    String menssagem_erro = null;
        Visão<Integer> visão = null;
        if (vendas_cadastradasList.getSelectedValue() != null) {
            visão = (Visão<Integer>) vendas_cadastradasList.getSelectedValue();
            menssagem_erro = controlador.removerVenda(visão.getChave());
        } else {
            menssagem_erro = "Nenhuma venda foi selecionado";
        }
        if (menssagem_erro == null) {
              vendas_cadastradas.removeElement(visão);
            if (vendas_cadastradas.size() > 0) {
                vendas_cadastradasList.ensureIndexIsVisible(0);
                JOptionPane.showMessageDialog(this, "Venda Excluida com sucesso!");
                new Utilitarios().LimpaTela(dados_clientePanel);
                clientes_cadastradosComboBox.setSelectedIndex(-1);
                pagamento_tipoCombobox.setSelectedIndex(-1);
                inicializarVendasCadastradas();
                carrinho_comprasTable.removeAll();
            }

        } else {
            JOptionPane.showMessageDialog(this, menssagem_erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_removerButtonremoverClienteButtonActionPerformed

    private void vendas_cadastradasListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendas_cadastradasListMouseClicked
        String selected = vendas_cadastradasList.getSelectedValue().toString();
        String[] partesString = selected.split(":");
        System.out.println(partesString[0]);

        ResultSet rs = null;
        String sql = "SELECT tab_itemvenda.id, tab_itemvenda.herbicida_id, herbicida.nome, \n"
                + "tab_itemvenda.valor_unitario, tab_itemvenda.quantidade, tab_itemvenda.subtotal FROM tab_vendas, tab_itemvenda, herbicida WHERE "
                + "tab_itemvenda.venda_id = tab_vendas.id_venda and herbicida.id = tab_itemvenda.herbicida_id and"
                + " tab_itemvenda.venda_id = '" + partesString[0] + "';";
        try {
            rs = comando.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JanelaGerenciamentoVenda.class.getName()).log(Level.SEVERE, null, ex);
        }

        carrinho = (DefaultTableModel) carrinho_comprasTable.getModel();
        carrinho.setRowCount(0);

        try {
            while (rs.next()) {
                String codigo = "" + rs.getInt("id");
                String produto = "" + rs.getInt("herbicida_id");
                String valor = "" + rs.getFloat("valor_unitario");
                String quantidade = "" + rs.getInt("quantidade");
                String valorTotal = "" + rs.getFloat("subtotal");

                Object[] row = {codigo, produto, valor, quantidade, valorTotal};
                carrinho.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JanelaGerenciamentoVenda.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_vendas_cadastradasListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton1;
    private javax.swing.JTable carrinho_comprasTable;
    private javax.swing.JComboBox clientes_cadastradosComboBox;
    private javax.swing.JButton consultarButton;
    private javax.swing.JPanel dados_clientePanel;
    private javax.swing.JPanel dados_clientePanel3;
    private javax.swing.JPanel dados_vendasPanel;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JFormattedTextField data_vendaFormattedTextField;
    private javax.swing.JLabel descontoLabel;
    private javax.swing.JFormattedTextField descontoTextField;
    private javax.swing.JLabel forma_pagamentoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> pagamento_tipoCombobox;
    private javax.swing.JCheckBox precisa_nfeCheckBox;
    private javax.swing.JButton removerButton;
    private javax.swing.JFormattedTextField sequencialTextField;
    private javax.swing.JFormattedTextField valor_finalTextField;
    private javax.swing.JLabel valor_produtosLabel;
    private javax.swing.JFormattedTextField valor_produtosTextField;
    private javax.swing.JLabel valorfinalLabel;
    private javax.swing.JList vendas_cadastradasList;
    // End of variables declaration//GEN-END:variables
}
