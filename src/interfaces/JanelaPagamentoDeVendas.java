
package interfaces;

import controle.ControladorCadastroVenda;
import entidade.Cliente;
import entidade.Venda;
import entidade.Venda.PagamentoTipo;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Data;


public class JanelaPagamentoDeVendas extends javax.swing.JDialog {

    ControladorCadastroVenda controlador;
    String cliente_id;
    String data_venda;
    float valor_daVenda;
    
    public JanelaPagamentoDeVendas() {
        initComponents();
        valor_brutoTextField.setText("0");
        valor_finalTextField.setText("0");
        valor_trocoTextField.setText("0");
        valor_pagoTextField.setText("0");
        valor_descontoTextField.setText("0");
    }
    
     private String inserirVenda(java.awt.event.ActionEvent evt) {
        Venda venda = obterDadosVenda();
        String mensagem_erro = null;
        if (venda != null) {
            venda.inserirVenda(venda);
            
            mensagem_erro = "ok";
             
        } else {
            mensagem_erro = "Algum atributo não foi informado";
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return mensagem_erro;
    }
     private Venda obterDadosVenda() {
       
        String cliente = cliente_id;
        if (cliente_id == null) {
            return null; 
        }

       String aux_produtos = valor_brutoTextField.getText();
        if (aux_produtos.isEmpty()) {
              return null;
        }
       
        float valor_produtos = Float.parseFloat(aux_produtos);
        
        String aux_desconto = valor_descontoTextField.getText();
        if (aux_desconto.isEmpty()) {
              return null;
        }
       
        float valor_desconto = Float.parseFloat(aux_desconto);
        
        String aux_total = valor_finalTextField.getText();
        if (aux_total.isEmpty()) {
              return null;
        }
       
        float valor_total = Float.parseFloat(aux_total);
       
        
        PagamentoTipo pagamentotipo = null;
           
        if (pagamento_tipoCombobox.getSelectedIndex()>=0) 
             pagamentotipo = PagamentoTipo.values()[pagamento_tipoCombobox.getSelectedIndex()];
        else
            return null;
        
        Boolean precisa_nfe= precisa_nfeCheckBox.isSelected(); 
    
        Date agora = new Date();
        SimpleDateFormat dataBr = new SimpleDateFormat("yyyy-MM-dd");
        String data_atual = dataBr.format(agora);
  
       return new Venda(0, cliente, data_atual, pagamentotipo,  valor_produtos, valor_desconto, valor_total, precisa_nfe);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        valor_pagoTextField = new javax.swing.JTextField();
        valor_descontoTextField = new javax.swing.JTextField();
        senhaLabel1 = new javax.swing.JLabel();
        cargolabel = new javax.swing.JLabel();
        valor_produtosLabel = new javax.swing.JLabel();
        valor_brutoTextField = new javax.swing.JTextField();
        pagamento_tipoCombobox = new javax.swing.JComboBox<>();
        forma_pagamentoLabel = new javax.swing.JLabel();
        precisa_nfeCheckBox = new javax.swing.JCheckBox();
        senhaLabel2 = new javax.swing.JLabel();
        valor_trocoTextField = new javax.swing.JTextField();
        cargolabel1 = new javax.swing.JLabel();
        valor_finalTextField = new javax.swing.JTextField();
        salvarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        salvarButton1 = new javax.swing.JButton();
        nfeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        usuarioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        valor_pagoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_pagoTextField.setToolTipText("");
        valor_pagoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                valor_pagoTextFieldFocusLost(evt);
            }
        });

        valor_descontoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_descontoTextField.setToolTipText("");
        valor_descontoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                valor_descontoTextFieldFocusLost(evt);
            }
        });
        valor_descontoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valor_descontoTextFieldKeyReleased(evt);
            }
        });

        senhaLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        senhaLabel1.setText("Valor Pago:");

        cargolabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cargolabel.setText("Desconto:");

        valor_produtosLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valor_produtosLabel.setText("Valor dos Produtos: ");

        valor_brutoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_brutoTextField.setToolTipText("");
        valor_brutoTextField.setEnabled(false);

        pagamento_tipoCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pagamento_tipoCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cartao_Credito", "Cartao_Debito" }));

        forma_pagamentoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        forma_pagamentoLabel.setText("Pagamento:");
        forma_pagamentoLabel.setToolTipText("");

        precisa_nfeCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        precisa_nfeCheckBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precisa_nfeCheckBox.setText("Precisa Nota Fiscal");
        precisa_nfeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precisa_nfeCheckBoxActionPerformed(evt);
            }
        });

        senhaLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        senhaLabel2.setText("Troco:");

        valor_trocoTextField.setBackground(new java.awt.Color(255, 204, 102));
        valor_trocoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_trocoTextField.setToolTipText("");
        valor_trocoTextField.setEnabled(false);
        valor_trocoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valor_trocoTextFieldActionPerformed(evt);
            }
        });

        cargolabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cargolabel1.setText("Valor Final:");

        valor_finalTextField.setBackground(new java.awt.Color(204, 204, 255));
        valor_finalTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_finalTextField.setToolTipText("");
        valor_finalTextField.setEnabled(false);

        salvarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salvarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tick.png"))); // NOI18N
        salvarButton.setText("EfetuarPagamento");
        salvarButton.setToolTipText("");
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pagamentos");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        salvarButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salvarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3iconfinder_system-log-out_118796.png"))); // NOI18N
        salvarButton1.setText("Sair");
        salvarButton1.setToolTipText("");
        salvarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButton1ActionPerformed(evt);
            }
        });

        nfeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nfeLabel.setText("NFE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cargolabel1)
                                    .addComponent(senhaLabel2))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(senhaLabel1)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valor_finalTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(valor_trocoTextField)
                            .addComponent(valor_pagoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(forma_pagamentoLabel)
                                    .addComponent(cargolabel))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nfeLabel)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valor_descontoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precisa_nfeCheckBox))))
                .addGap(14, 42, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(salvarButton)
                        .addGap(28, 28, 28)
                        .addComponent(salvarButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(valor_produtosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valor_brutoTextField))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {valor_descontoTextField, valor_finalTextField, valor_pagoTextField, valor_trocoTextField});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_brutoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valor_produtosLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nfeLabel)
                    .addComponent(precisa_nfeCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forma_pagamentoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargolabel)
                    .addComponent(valor_descontoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargolabel1)
                    .addComponent(valor_finalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_pagoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senhaLabel1))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel2)
                    .addComponent(valor_trocoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salvarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {valor_brutoTextField, valor_descontoTextField, valor_finalTextField, valor_pagoTextField, valor_trocoTextField});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(usuarioLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void precisa_nfeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precisa_nfeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precisa_nfeCheckBoxActionPerformed

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed
     inserirVenda(evt);    


    }//GEN-LAST:event_salvarButtonActionPerformed

    private void valor_descontoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_descontoTextFieldKeyReleased
   
    }//GEN-LAST:event_valor_descontoTextFieldKeyReleased

    private void valor_descontoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valor_descontoTextFieldFocusLost
        float valor_bruto, valor_final, valor_desconto;
 
        
        valor_bruto = valor_daVenda;
        valor_brutoTextField.setText(String.valueOf(valor_daVenda));
        
        if(valor_descontoTextField.getText().equals("")){
            valor_desconto = 0;
            valor_descontoTextField.setText("0");
        }else{
          valor_desconto = Float.parseFloat(valor_descontoTextField.getText());
       }
        valor_final = valor_bruto - valor_desconto;

        valor_finalTextField.setText(String.valueOf(valor_final));
      
    
    }//GEN-LAST:event_valor_descontoTextFieldFocusLost

    private void valor_pagoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valor_pagoTextFieldFocusLost
  float valor_pago, valor_final, valor_troco;
       
       
        valor_final = Float.parseFloat(valor_finalTextField.getText());
        if(valor_pagoTextField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Valor Pago não pode ser Zero");
        }else{
            valor_pago = Float.parseFloat(valor_pagoTextField.getText());
            if(valor_pago < valor_final){     
                JOptionPane.showMessageDialog(this, "Erro - Valor Pago não pode ser menor que o valor Final");
                
            }else{
                 valor_troco = valor_final - valor_pago;
                 valor_trocoTextField.setText(String.valueOf(valor_troco));     
            }
        }
        
          
    }//GEN-LAST:event_valor_pagoTextFieldFocusLost

    private void valor_trocoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valor_trocoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_trocoTextFieldActionPerformed

    private void salvarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButton1ActionPerformed
        
    }//GEN-LAST:event_salvarButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
     
      
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaPagamentoDeVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPagamentoDeVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPagamentoDeVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPagamentoDeVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPagamentoDeVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cargolabel;
    private javax.swing.JLabel cargolabel1;
    private javax.swing.JLabel forma_pagamentoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JLabel nfeLabel;
    private javax.swing.JComboBox<String> pagamento_tipoCombobox;
    private javax.swing.JCheckBox precisa_nfeCheckBox;
    private javax.swing.JButton salvarButton;
    private javax.swing.JButton salvarButton1;
    private javax.swing.JLabel senhaLabel1;
    private javax.swing.JLabel senhaLabel2;
    private javax.swing.JLabel usuarioLabel;
    public javax.swing.JTextField valor_brutoTextField;
    private javax.swing.JTextField valor_descontoTextField;
    private javax.swing.JTextField valor_finalTextField;
    private javax.swing.JTextField valor_pagoTextField;
    private javax.swing.JLabel valor_produtosLabel;
    private javax.swing.JTextField valor_trocoTextField;
    // End of variables declaration//GEN-END:variables
}
