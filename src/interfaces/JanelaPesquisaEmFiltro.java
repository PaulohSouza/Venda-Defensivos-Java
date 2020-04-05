/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidade.ItemVenda;
import entidade.Visão;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import util.Data;
import static util.Data.toData;


public class JanelaPesquisaEmFiltro extends javax.swing.JFrame {

    private DefaultListModel filtro;
    private DefaultListModel geral;
   
   
    public JanelaPesquisaEmFiltro() {
        initComponents();
        inicializarFiltro();
        formulacaoComboBox.setSelectedIndex(-1);
        pagamento_tipoCombobox.setSelectedIndex(-1);
        this.setLocationRelativeTo(null);//<--comando para que a Janela apareça Centralizado na Tela  
    }

     
    private void inicializarFiltro() {
     //   geral = (DefaultListModel) geralList.getModel();
        filtro = (DefaultListModel) filtroList.getModel();
  
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexo_clienteButtonGroup = new javax.swing.ButtonGroup();
        masculinoRadioButton = new javax.swing.JRadioButton();
        sexoLabel1 = new javax.swing.JLabel();
        femininoRadioButton = new javax.swing.JRadioButton();
        nivelLabel = new javax.swing.JLabel();
        formulacaoComboBox = new javax.swing.JComboBox<>();
        limparButton = new javax.swing.JButton();
        pesquisarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filtroScrollPane = new javax.swing.JScrollPane();
        filtroList = new javax.swing.JList<>();
        pagamento_tipoCombobox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        sexoLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        masculinoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        sexo_clienteButtonGroup.add(masculinoRadioButton);
        masculinoRadioButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        masculinoRadioButton.setText("Masculino");

        sexoLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sexoLabel1.setText("Gênero Cliente:");

        femininoRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        sexo_clienteButtonGroup.add(femininoRadioButton);
        femininoRadioButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        femininoRadioButton.setText("Feminino");

        nivelLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nivelLabel.setText("Formulação Herbicida:");

        formulacaoComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formulacaoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Po", "Liquido", "Granulado" }));

        limparButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonlimparAdoçãoButtonActionPerformed(evt);
            }
        });

        pesquisarButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pesquisarButton.setText("Pesquisar");
        pesquisarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarButtonpesquisarAdoçãoButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Filtro de Vendas");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        filtroList.setModel(new DefaultListModel());
        filtroList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtroListMouseClicked(evt);
            }
        });
        filtroScrollPane.setViewportView(filtroList);

        pagamento_tipoCombobox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pagamento_tipoCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cartao_Credito", "Cartao_Debito" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Vendas Realizadas");

        sexoLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sexoLabel2.setText("Forma Pagamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sexoLabel2)
                    .addComponent(sexoLabel1)
                    .addComponent(nivelLabel))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(limparButton)
                            .addGap(18, 18, 18)
                            .addComponent(pesquisarButton))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(masculinoRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(femininoRadioButton))
                            .addComponent(formulacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(486, 486, 486))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(filtroScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagamento_tipoCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexoLabel2))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(masculinoRadioButton)
                        .addComponent(femininoRadioButton))
                    .addComponent(sexoLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nivelLabel)
                    .addComponent(formulacaoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limparButton)
                    .addComponent(pesquisarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(filtroScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limparButtonlimparAdoçãoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonlimparAdoçãoButtonActionPerformed
        
 
  
    }//GEN-LAST:event_limparButtonlimparAdoçãoButtonActionPerformed

    private void pesquisarButtonpesquisarAdoçãoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarButtonpesquisarAdoçãoButtonActionPerformed
        Vector<Visão<Integer>> visões = new Vector<Visão<Integer>>();

        int formulacao1 = -1, pagamento1 = -1;
        String mensagem_erro = null;
        if (formulacaoComboBox.getSelectedIndex()>=0){
             formulacao1 = formulacaoComboBox.getSelectedIndex();
        }
        
        if (pagamento_tipoCombobox.getSelectedIndex()>=0){
             pagamento1 = pagamento_tipoCombobox.getSelectedIndex();
        }
        String sexo_cliente = null;
        if (masculinoRadioButton.isSelected()) {
            sexo_cliente = "m";
        } else if (femininoRadioButton.isSelected()) {
            sexo_cliente = "f";
        }else{
            sexo_cliente = "x";
       }
     
     visões = ItemVenda.getVisõesFiltradas(pagamento1, sexo_cliente, formulacao1);
   
       if (visões == null) {
           filtro.removeAllElements();
           JOptionPane.showMessageDialog(null, "Nenhum dado encontrado no filtro selecionado");

       } else {
              filtro.removeAllElements();
            for (Visão<Integer> visão : visões) {
                filtro.addElement(visão);
              //  filtro.addElement("\n");
            }  

         inicializarFiltro();
        }  
      if(visões.isEmpty()){
          filtro.removeAllElements();
          filtro.addElement("NENHUMA INFORMACAO DE VENDA ENCONTRADA COM O FILTRO INDICADO!");
        //  JOptionPane.showMessageDialog(null, "Nenhum dado encontrado no filtro selecionado");
      }
    }//GEN-LAST:event_pesquisarButtonpesquisarAdoçãoButtonActionPerformed

    private void filtroListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtroListMouseClicked
        int selectedIndex = filtroList.getSelectedIndex();
              if (selectedIndex != -1) {
              filtro.remove(selectedIndex);
        }
    }//GEN-LAST:event_filtroListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton femininoRadioButton;
    private javax.swing.JList<String> filtroList;
    private javax.swing.JScrollPane filtroScrollPane;
    private javax.swing.JComboBox<String> formulacaoComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton limparButton;
    private javax.swing.JRadioButton masculinoRadioButton;
    private javax.swing.JLabel nivelLabel;
    private javax.swing.JComboBox<String> pagamento_tipoCombobox;
    private javax.swing.JButton pesquisarButton;
    private javax.swing.JLabel sexoLabel1;
    private javax.swing.JLabel sexoLabel2;
    private javax.swing.ButtonGroup sexo_clienteButtonGroup;
    // End of variables declaration//GEN-END:variables
}
