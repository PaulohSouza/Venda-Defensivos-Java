/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.dialgo;

import controle.ControladorCadastroVenda;
import entidade.Herbicida;
import entidade.Herbicida.Formulacao;
import entidade.ItemVenda;
import entidade.Venda;
import entidade.Visão;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class DialogPagamentoVenda extends javax.swing.JDialog {
    
    ControladorCadastroVenda controlador;
    String cliente_id;
    String data_venda;
    float valor_daVenda;
    DefaultTableModel carrinho;
  
    
    public DialogPagamentoVenda(ControladorCadastroVenda controlador, String cliente, float valor_total,  DefaultTableModel carrinho_venda) {
        super(new javax.swing.JFrame(), true);
        initComponents();
        cliente_id = cliente;
        valor_daVenda = valor_total;
        valor_brutoTextField.setText("0");
        valor_finalTextField.setText("0");
        valor_trocoTextField.setText("0");
        valor_pagoTextField.setText("0");
        valor_descontoTextField.setText("0");
        carrinho = carrinho_venda;
        this.controlador = controlador;
        
    }
    
     private void inserirVenda(java.awt.event.ActionEvent evt) {
        Venda venda = obterDadosVenda();
        String mensagem_erro = null;
        if (venda != null) {
            System.out.println("Obteve os dados");
            controlador.inserirVenda(venda);
        } else {
             mensagem_erro = "Algum atributo do cliente não foi informado";
            
        } if(mensagem_erro == null){
           
            }else{
            mensagem_erro = "Algum atributo não foi informado";
            JOptionPane.showMessageDialog(this, mensagem_erro, "ERRO",
           JOptionPane.ERROR_MESSAGE);
     }    
    
  }
  private String VerificarValoresPagamento() {
    float valor_pago = 0F; 
      
     String aux_desconto = valor_descontoTextField.getText();
     String mensagem_erro = null;
    
    if(aux_desconto.isEmpty()) 
        return mensagem_erro = "Valor desconto vazio";
        
    float valor_desconto = 0;    
       
        try {
                valor_desconto = Float.parseFloat(aux_desconto.replace(',', '.'));
      } catch (Exception ex) {  
              return mensagem_erro =  "Desconto informado não é valido! - Favor reinserir valor";
    
      }
        
      String aux_valorPago = valor_pagoTextField.getText();
        if(aux_valorPago.isEmpty()) 
            return  mensagem_erro = "Valor pago vazio";
 
        try {
                valor_pago = Float.parseFloat(aux_valorPago.replace(',', '.'));
      } catch (Exception ex) {
              return mensagem_erro = "O valor pago informado não é monetário";
      }  
    
     Venda.PagamentoTipo pagamentotipo = null;
 
       if (pagamento_tipoCombobox.getSelectedIndex()>=1) {
               pagamentotipo = Venda.PagamentoTipo.values()[pagamento_tipoCombobox.getSelectedIndex()];
            }else{ 
                return mensagem_erro = "Forma de Pagamento não selecionada!";
                
            }
       
        String aux_valorFinal = valor_finalTextField.getText();
        float valor_final = Float.parseFloat(aux_valorFinal);
       if (valor_pago < valor_final){
           valor_pagoTextField.grabFocus();
           return mensagem_erro = "Valor Pago menor que o da Venda - Favor inserir valor pago válido!";
       }
          return null;
  }
  private void EfetuarPagamento(java.awt.event.ActionEvent evt){
      
      Venda.PagamentoTipo pagamentotipo = null;
      Venda venda = new Venda(0, "", data_venda, pagamentotipo, valor_daVenda,valor_daVenda,valor_daVenda,true);
      String mensagem_erro = VerificarValoresPagamento();
      int id;
       
      if(mensagem_erro == null){
             
              inserirVenda(evt);
              
              id = venda.RetornaUltimaVenda();
              venda.setSequencial(id);
              Herbicida herbicida = new Herbicida(0, "", "", "", 0, 0, Formulacao.Granulado, true);
              for(int i =0; i < carrinho.getRowCount(); i++){
                  int qtd_estoque, qtd_comprada, qtd_atualizada,id_herbicida;
                  
                  ItemVenda item = new ItemVenda();
                    
                  herbicida.setId(Integer.parseInt(carrinho.getValueAt(i, 0).toString()));
                  id_herbicida =  Integer.parseInt(carrinho.getValueAt(i, 0).toString());
                  
                  item.setVenda(venda);
                  item.setHerbicida(herbicida);
                  
                  qtd_estoque = herbicida.RetornaEstoqueAtual(id_herbicida);
                  
                  System.out.println("Quantidade em Estoque igual a: " + qtd_estoque);
                  item.setValor_unitario(Float.parseFloat(carrinho.getValueAt(i, 2).toString()));
                  item.setQuantidade((Integer.parseInt(carrinho.getValueAt(i, 3).toString()))); 
                
                  qtd_comprada = Integer.parseInt(carrinho.getValueAt(i, 3).toString());
                  System.out.println("Quantidade comprada: " + qtd_comprada);
                  item.setSubtotal(Float.parseFloat(carrinho.getValueAt(i, 4).toString()));  
                
                  qtd_atualizada = qtd_estoque - qtd_comprada;
                  System.out.println("Quantidade atualziada: " + qtd_atualizada);
                  herbicida.baixarEstoque(id_herbicida, qtd_atualizada);

                  controlador.inserirItemVenda(venda, item);
              }
    } else{
               JOptionPane.showMessageDialog(this,mensagem_erro);
    }        
      
  }
     
   private Venda obterDadosVenda() {
     
        System.out.println(cliente_id);
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
       
        
        Venda.PagamentoTipo pagamentotipo = null;
           
        if (pagamento_tipoCombobox.getSelectedIndex()>=0) 
             pagamentotipo = Venda.PagamentoTipo.values()[pagamento_tipoCombobox.getSelectedIndex()];
        else
            return null;
        
        Boolean precisa_nfe= precisa_nfeCheckBox.isSelected(); 
  
         
        Date agora = new Date();
        SimpleDateFormat dataBr = new SimpleDateFormat("yyyy-MM-dd");
        String data_atual = dataBr.format(agora);
    
       return new Venda(0, cliente, data_atual, pagamentotipo,  valor_produtos, valor_desconto, valor_total, precisa_nfe);
    }
  
private float CalculaValorDesconto(){
   
    String desconto_informado = valor_descontoTextField.getText();
    if(desconto_informado.isEmpty()) return -1;
        float valor_desconto = 0;    
       
        try {
                float aux_desconto = Float.parseFloat(desconto_informado.replace(',', '.'));
                valor_desconto = aux_desconto;
                
      } catch (Exception ex) {  
              return -1;
      }
    return valor_desconto;
}

public float CalculaValorTroco(){
   
    CalculaValorDesconto();
    float valor_pago = 0, valor_troco = 0, valor_final = 0;

    String aux_final = valor_finalTextField.getText();
    String aux_pago = valor_pagoTextField.getText();

    if(aux_pago.isEmpty()){
        return -1;    
       
    }else
    
    {try  
       {
               valor_pago = Float.parseFloat(aux_pago.replace(',', '.'));
               valor_final = Float.parseFloat(aux_final.replace(',', '.'));
               valor_troco = (valor_pago - valor_final);
               valor_pagoTextField.setText(String.valueOf(valor_pago));
      } catch (Exception ex) {  
              return -1;
      }
  
   return valor_troco;
    }
 }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        valor_pagoTextField = new javax.swing.JTextField();
        valor_descontoTextField = new javax.swing.JTextField();
        senhaLabel1 = new javax.swing.JLabel();
        cargolabel = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JLabel();
        valor_brutoTextField = new javax.swing.JTextField();
        pagamento_tipoCombobox = new javax.swing.JComboBox<>();
        forma_pagamentoLabel = new javax.swing.JLabel();
        precisa_nfeCheckBox = new javax.swing.JCheckBox();
        senhaLabel2 = new javax.swing.JLabel();
        valor_trocoTextField = new javax.swing.JTextField();
        cargolabel1 = new javax.swing.JLabel();
        valor_finalTextField = new javax.swing.JTextField();
        salvarButton = new javax.swing.JButton();
        salvarButton1 = new javax.swing.JButton();
        cargolabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Pagamentos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        valor_pagoTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valor_pagoTextField.setToolTipText("");
        valor_pagoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                valor_pagoTextFieldFocusGained(evt);
            }
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

        nomeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nomeLabel.setText("Valor em Produtos:");

        valor_brutoTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valor_brutoTextField.setToolTipText("");
        valor_brutoTextField.setEnabled(false);

        pagamento_tipoCombobox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pagamento_tipoCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cartao_Credito", "Cartao_Debito" }));

        forma_pagamentoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        forma_pagamentoLabel.setText("Forma Pagamento:");

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

        salvarButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salvarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3iconfinder_system-log-out_118796.png"))); // NOI18N
        salvarButton1.setText("Sair");
        salvarButton1.setToolTipText("");
        salvarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButton1ActionPerformed(evt);
            }
        });

        cargolabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cargolabel2.setText("NFE:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(salvarButton)
                        .addGap(32, 32, 32)
                        .addComponent(salvarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(senhaLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(senhaLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(forma_pagamentoLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cargolabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cargolabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cargolabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nomeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(valor_brutoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(238, 238, 238))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(valor_descontoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(valor_finalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(precisa_nfeCheckBox))
                                    .addComponent(valor_trocoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pagamento_tipoCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(valor_pagoTextField)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {valor_brutoTextField, valor_descontoTextField, valor_finalTextField, valor_pagoTextField, valor_trocoTextField});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_brutoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precisa_nfeCheckBox)
                    .addComponent(cargolabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargolabel)
                            .addComponent(valor_descontoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cargolabel1)
                            .addComponent(valor_finalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(forma_pagamentoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(senhaLabel1))
                    .addComponent(valor_pagoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_trocoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senhaLabel2))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salvarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {valor_brutoTextField, valor_descontoTextField, valor_finalTextField, valor_pagoTextField, valor_trocoTextField});

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pagamentos");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void valor_pagoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valor_pagoTextFieldFocusLost
   
      float valor_calculado = 0;
      DecimalFormat df = new DecimalFormat("0.00");
      valor_calculado= CalculaValorTroco();

          if(valor_calculado != -1){
                 String valor = df.format(valor_calculado);
                 valor_trocoTextField.setText(valor);
          } else{
            if(valor_calculado < 0){
                JOptionPane.showMessageDialog(this, "Erro - Valor Pago não pode ser menor que o valor Final");
           }
      }  

    }//GEN-LAST:event_valor_pagoTextFieldFocusLost

    private void valor_descontoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valor_descontoTextFieldFocusLost
        float valor_bruto, valor_final, valor_desconto;

        valor_bruto = valor_daVenda;
        valor_brutoTextField.setText(String.valueOf(valor_bruto));
        
        DecimalFormat df = new DecimalFormat("0.00");
        
         float desconto = CalculaValorDesconto();
         
         if(desconto != -1){
              valor_final = valor_bruto - desconto;
              valor_descontoTextField.setText(String.valueOf(desconto));
              valor_finalTextField.setText(String.valueOf(valor_final));
            //  valor_daVenda = valor_final;
      }else{
             JOptionPane.showMessageDialog(this, "Erro: Este valor não pode ser convertido");
             valor_descontoTextField.setText("0");
         }
 
    }//GEN-LAST:event_valor_descontoTextFieldFocusLost

    private void valor_descontoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_descontoTextFieldKeyReleased

    }//GEN-LAST:event_valor_descontoTextFieldKeyReleased

    private void precisa_nfeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precisa_nfeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precisa_nfeCheckBoxActionPerformed

    private void valor_trocoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valor_trocoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_trocoTextFieldActionPerformed

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed
           EfetuarPagamento(evt);
    }//GEN-LAST:event_salvarButtonActionPerformed

    private void salvarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButton1ActionPerformed

    }//GEN-LAST:event_salvarButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      valor_brutoTextField.setText(String.valueOf(valor_daVenda));
      valor_finalTextField.setText(String.valueOf(valor_daVenda));
    }//GEN-LAST:event_formWindowActivated

    private void valor_pagoTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valor_pagoTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_pagoTextFieldFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cargolabel;
    private javax.swing.JLabel cargolabel1;
    private javax.swing.JLabel cargolabel2;
    private javax.swing.JLabel forma_pagamentoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JComboBox<String> pagamento_tipoCombobox;
    private javax.swing.JCheckBox precisa_nfeCheckBox;
    private javax.swing.JButton salvarButton;
    private javax.swing.JButton salvarButton1;
    private javax.swing.JLabel senhaLabel1;
    private javax.swing.JLabel senhaLabel2;
    public javax.swing.JTextField valor_brutoTextField;
    private javax.swing.JTextField valor_descontoTextField;
    public javax.swing.JTextField valor_finalTextField;
    private javax.swing.JTextField valor_pagoTextField;
    public javax.swing.JTextField valor_trocoTextField;
    // End of variables declaration//GEN-END:variables
}
