package interfaces;

import java.awt.Color;
import javax.swing.UIManager;
import java.util.Vector;
import controle.ControladorCadastroHerbicida;
import controle.ControladorCadastroVenda;
import controle.ControladorGerenciamentoVenda;
import entidade.Cliente;
import entidade.Herbicida;
import entidade.Herbicida.Formulacao;
import entidade.Venda;
import entidade.Visão;
import interfaces.dialgo.DialogPagamentoVenda;
import java.awt.Font;
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
  
    DefaultTableModel carrinho;

    private static DefaultListModel vendas_cadastradas;
    private static Vector<Visão<Integer>> herbicidas_cadastrados = new Vector<Visão<Integer>>();
    private static Vector<Visão<String>> clientes_cadastrados = new Vector<Visão<String>>();

    public JanelaCadastroVenda(ControladorCadastroVenda controlador) {
        this.controlador = controlador; 
        herbicidas_cadastrados = Herbicida.getVisões();
        clientes_cadastrados = Cliente.getVisões();
//        inicializarEstoquesCadastrados();
        initComponents();
        carrinho_comprasTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
        carrinho_comprasTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
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
/*
    private void inicializarEstoquesCadastrados() {
        vendas_cadastradas = (DefaultListModel) vendas_cadastradasList.getModel();
        Vector<Visão<Integer>> visões = Venda.getVisões();
        for (Visão<Integer> visão : visões) {
           vendas_cadastradas.addElement(visão);
        }
    }
    */

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

        if (quantidadeTextField.getText().isEmpty()){
            return false;
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
      quantidadeTextField.setText("");
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
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel8 = new javax.swing.JLabel();
        valor_totalTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
        cancelarButton = new javax.swing.JButton();
        salvarButton = new javax.swing.JButton();
        btIncluirProduto3 = new javax.swing.JButton();
        btIncluirProduto4 = new javax.swing.JButton();
        btIncluirProduto6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Vendas- AgroHerbicides");
        setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Painel de Vendas");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dados_clientePanel.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        clientes_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clientes_cadastradosComboBox.setModel((new DefaultComboBoxModel (clientes_cadastrados)));
        clientes_cadastradosComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                clientes_cadastradosComboBoxFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Cliente:");

        data_vendaFormattedTextField.setText("  /  /");
        data_vendaFormattedTextField.setEnabled(false);
        data_vendaFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dataLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dataLabel.setText("Data:");

        javax.swing.GroupLayout dados_clientePanelLayout = new javax.swing.GroupLayout(dados_clientePanel);
        dados_clientePanel.setLayout(dados_clientePanelLayout);
        dados_clientePanelLayout.setHorizontalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dataLabel)
                .addGap(18, 18, 18)
                .addComponent(data_vendaFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        dados_clientePanelLayout.setVerticalGroup(
            dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(data_vendaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dados_clientePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clientes_cadastradosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(dataLabel)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        dados_clientePanel3.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrinho de Compras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        carrinho_comprasTable.setAutoCreateRowSorter(true);
        carrinho_comprasTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        carrinho_comprasTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        carrinho_comprasTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(carrinho_comprasTable);
        if (carrinho_comprasTable.getColumnModel().getColumnCount() > 0) {
            carrinho_comprasTable.getColumnModel().getColumn(0).setResizable(false);
            carrinho_comprasTable.getColumnModel().getColumn(1).setMinWidth(250);
            carrinho_comprasTable.getColumnModel().getColumn(2).setMinWidth(150);
            carrinho_comprasTable.getColumnModel().getColumn(3).setMinWidth(100);
            carrinho_comprasTable.getColumnModel().getColumn(4).setMinWidth(250);
        }

        javax.swing.GroupLayout dados_clientePanel3Layout = new javax.swing.GroupLayout(dados_clientePanel3);
        dados_clientePanel3.setLayout(dados_clientePanel3Layout);
        dados_clientePanel3Layout.setHorizontalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
        );
        dados_clientePanel3Layout.setVerticalGroup(
            dados_clientePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dados_clientePanel2.setBackground(new java.awt.Color(255, 255, 255));
        dados_clientePanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        herbicidas_cadastradosComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        herbicidas_cadastradosComboBox.setModel(new DefaultComboBoxModel (herbicidas_cadastrados));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Produto:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Quant.:");

        quantidadeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        quantidadeTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btIncluirProduto2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btIncluirProduto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3 - New Venda.png"))); // NOI18N
        btIncluirProduto2.setText("Incluir no carrinho");
        btIncluirProduto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dados_clientePanel2Layout = new javax.swing.GroupLayout(dados_clientePanel2);
        dados_clientePanel2.setLayout(dados_clientePanel2Layout);
        dados_clientePanel2Layout.setHorizontalGroup(
            dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dados_clientePanel2Layout.createSequentialGroup()
                        .addComponent(quantidadeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                        .addComponent(btIncluirProduto2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(herbicidas_cadastradosComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dados_clientePanel2Layout.setVerticalGroup(
            dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dados_clientePanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(herbicidas_cadastradosComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dados_clientePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btIncluirProduto2)
                    .addComponent(jLabel7))
                .addGap(19, 19, 19))
        );

        btIncluirProduto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btIncluirProduto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3_remove Cart 32x32.png"))); // NOI18N
        btIncluirProduto1.setText("Remover do Carrinho");
        btIncluirProduto1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btIncluirProduto1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btIncluirProduto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setText("Total R$:");

        valor_totalTextField.setEditable(false);
        valor_totalTextField.setBackground(new java.awt.Color(255, 153, 153));
        valor_totalTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        valor_totalTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        valor_totalTextField.setText("0.0");
        valor_totalTextField.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        cancelarButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3excluir.png"))); // NOI18N
        cancelarButton.setText("Cancelar Venda");
        cancelarButton.setToolTipText("");
        cancelarButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cancelarButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        salvarButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        salvarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tick.png"))); // NOI18N
        salvarButton.setText("EfetuarPagamento");
        salvarButton.setToolTipText("");
        salvarButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        salvarButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });

        btIncluirProduto3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btIncluirProduto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3iconfinder_system-log-out_118796.png"))); // NOI18N
        btIncluirProduto3.setText("Limpar tela");
        btIncluirProduto3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btIncluirProduto3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btIncluirProduto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto3ActionPerformed(evt);
            }
        });

        btIncluirProduto4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btIncluirProduto4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/4_consultaPNG.png"))); // NOI18N
        btIncluirProduto4.setText("Gerenciar Vendas");
        btIncluirProduto4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btIncluirProduto4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btIncluirProduto4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto4ActionPerformed(evt);
            }
        });

        btIncluirProduto6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btIncluirProduto6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3novo.png"))); // NOI18N
        btIncluirProduto6.setText("Nova Venda");
        btIncluirProduto6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirProduto6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dados_clientePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dados_clientePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dados_clientePanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btIncluirProduto6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valor_totalTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btIncluirProduto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btIncluirProduto3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btIncluirProduto4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salvarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dados_clientePanel, dados_clientePanel2, dados_clientePanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salvarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIncluirProduto4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIncluirProduto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIncluirProduto3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btIncluirProduto6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dados_clientePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dados_clientePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(valor_totalTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dados_clientePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btIncluirProduto1, btIncluirProduto3, btIncluirProduto4, cancelarButton, salvarButton});

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

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed
        // TODO add your handling code here:

        String cliente_id = ConsultarIdCliente();
         
        float valor_daVenda;

        valor_daVenda = Float.parseFloat(valor_totalTextField.getText());
        DialogPagamentoVenda pagamento = new DialogPagamentoVenda(controlador, cliente_id, valor_daVenda, carrinho);
        pagamento.show(true);


    }//GEN-LAST:event_salvarButtonActionPerformed

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

    private void btIncluirProduto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto3ActionPerformed
         LimparTelarVenda();
    }//GEN-LAST:event_btIncluirProduto3ActionPerformed

    private void btIncluirProduto4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto4ActionPerformed
        String senha = "admin";
        senha =  JOptionPane.showInputDialog("Informe a senha de acesso a gerencia: ");
        if(senha.equals("admin")){
            new ControladorGerenciamentoVenda();
        }else{  
            String mensagem_erro = "Senha não informada ou inválida - Você não tem acesso a esta tela";
            JOptionPane.showMessageDialog(null, mensagem_erro, "Login Invalido!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btIncluirProduto4ActionPerformed

    private void btIncluirProduto6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto6ActionPerformed
       LimparTelarVenda();
    }//GEN-LAST:event_btIncluirProduto6ActionPerformed

    private void btIncluirProduto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirProduto2ActionPerformed
        InserirItemVenda();
    }//GEN-LAST:event_btIncluirProduto2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIncluirProduto1;
    private javax.swing.JButton btIncluirProduto2;
    private javax.swing.JButton btIncluirProduto3;
    private javax.swing.JButton btIncluirProduto4;
    private javax.swing.JButton btIncluirProduto6;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTable carrinho_comprasTable;
    private javax.swing.JComboBox clientes_cadastradosComboBox;
    private javax.swing.JPanel dados_clientePanel;
    private javax.swing.JPanel dados_clientePanel2;
    private javax.swing.JPanel dados_clientePanel3;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JFormattedTextField data_vendaFormattedTextField;
    private javax.swing.JComboBox herbicidas_cadastradosComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JFormattedTextField quantidadeTextField;
    private javax.swing.JButton salvarButton;
    private javax.swing.JFormattedTextField valor_totalTextField;
    // End of variables declaration//GEN-END:variables

}
