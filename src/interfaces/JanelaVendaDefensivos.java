
package interfaces;

import javax.swing.JOptionPane;
import persistencia.BD;
import controle.*;
import entidade.Funcionario;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import util.Data;

public class JanelaVendaDefensivos extends javax.swing.JFrame {

    public JanelaVendaDefensivos(String nome, String cargo, String nivel_acesso) {
       //To change body of generated methods, choose Tools | Templates.
      // cadastrar_FuncionariosMenuItem.enable(false); 
       initComponents();
        BD.criaConexãoComando();
        data_horaLabel.setVisible(false);
        data_horaTimer.start();
        usuario_logadoLabel.setText(nome);
        cargo_logadoLabel.setText(cargo);
        nivel_logadoLabel.setText(nivel_acesso);
        nivel_logadoLabel.setVisible(false);
        
        //Se não for Administrador não vai aparecer
        if (!nivel_acesso.equals("Administrador")){
            //cadastrar_FuncionariosMenuItem.setEnabled(false);
        }
        try {   
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JanelaVendaDefensivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JanelaVendaDefensivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JanelaVendaDefensivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JanelaVendaDefensivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void dadosLogados(){
        String usuario, cargo, nivel;
   
    }
    //Aqui publico só para poder puxar dados para todas as telas
 
          
    private void informaServiçoIndisponível() {
        JOptionPane.showMessageDialog(this, "Serviço Indisponível",
                "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        data_horaTimer = new org.netbeans.examples.lib.timerbean.Timer();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        clientesButton = new javax.swing.JButton();
        consultaButton = new javax.swing.JButton();
        sairButton = new javax.swing.JButton();
        herbicidabutton = new javax.swing.JButton();
        vendasButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        usuario_logadoLabel2 = new javax.swing.JLabel();
        data_horaLabel = new javax.swing.JLabel();
        cargo_logadoLabel = new javax.swing.JLabel();
        usuario_logadoLabel = new javax.swing.JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/capa.JPG"));
        Image image = icon.getImage();
        capaDesktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }

        };

        ;
        nivel_logadoLabel = new javax.swing.JLabel();
        BarraMenuBar = new javax.swing.JMenuBar();
        cadastrosMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem CadastrarClienteMenuItem = new javax.swing.JMenuItem();
        cadastrar_defensivoMenuItem = new javax.swing.JMenuItem();
        CadastrarEntregaMenuItem = new javax.swing.JMenuItem();
        cadastrar_FuncionariosMenuItem = new javax.swing.JMenuItem();
        consultasMenu = new javax.swing.JMenu();
        filtro_vendasMenuItem = new javax.swing.JMenuItem();
        RelatoriosMenu = new javax.swing.JMenu();
        gerenciamentoMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        designMenu = new javax.swing.JMenu();
        mudar_telaMenu = new javax.swing.JMenu();
        design_metalMenuItem = new javax.swing.JMenuItem();
        design_windowsMenuItem = new javax.swing.JMenuItem();
        design_motifMenuItem = new javax.swing.JMenuItem();
        AjudaMenu = new javax.swing.JMenu();
        suporteonlineMenuItem = new javax.swing.JMenuItem();
        sobreMenuItem = new javax.swing.JMenuItem();

        data_horaTimer.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                data_horaTimerOnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Siistema Venda de Defensivos - AgroHerbicides");
        setName(""); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                terminarSistema(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        clientesButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clientesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3clientes.png"))); // NOI18N
        clientesButton.setText("Clientes");
        clientesButton.setFocusable(false);
        clientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesButtonActionPerformed(evt);
            }
        });

        consultaButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consultaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3_ iconfinder_folder-saved-search_118905.png"))); // NOI18N
        consultaButton.setText("Consultar");
        consultaButton.setFocusable(false);

        sairButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sairButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3sair.png"))); // NOI18N
        sairButton.setText("Sair");
        sairButton.setFocusable(false);
        sairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairButtonActionPerformed(evt);
            }
        });

        herbicidabutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        herbicidabutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3produtos.png"))); // NOI18N
        herbicidabutton.setText("Herbicidas");
        herbicidabutton.setFocusable(false);
        herbicidabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herbicidabuttonActionPerformed(evt);
            }
        });

        vendasButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vendasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3vendas.png"))); // NOI18N
        vendasButton.setText("Vendas");
        vendasButton.setFocusable(false);
        vendasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(clientesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(herbicidabutton)
                .addGap(10, 10, 10)
                .addComponent(vendasButton)
                .addGap(10, 10, 10)
                .addComponent(consultaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sairButton)
                .addGap(0, 106, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clientesButton, consultaButton, herbicidabutton, sairButton, vendasButton});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(herbicidabutton)
                        .addComponent(clientesButton))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(consultaButton)
                        .addComponent(sairButton)
                        .addComponent(vendasButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {clientesButton, consultaButton, herbicidabutton, sairButton, vendasButton});

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        usuario_logadoLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usuario_logadoLabel2.setText("Usuário Logado: ");

        data_horaLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        data_horaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        data_horaLabel.setText("data_horaLabel");

        cargo_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cargo_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        cargo_logadoLabel.setText("Usuário Logado: Paulo Souza");

        usuario_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        usuario_logadoLabel.setText("Usuário Logado: Paulo Souza");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(usuario_logadoLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario_logadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cargo_logadoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(data_horaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuario_logadoLabel2)
                    .addComponent(cargo_logadoLabel)
                    .addComponent(usuario_logadoLabel)
                    .addComponent(data_horaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nivel_logadoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nivel_logadoLabel.setForeground(new java.awt.Color(0, 102, 102));
        nivel_logadoLabel.setText("Usuário Logado: Paulo Souza");
        nivel_logadoLabel.setEnabled(false);

        capaDesktopPane.setLayer(nivel_logadoLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout capaDesktopPaneLayout = new javax.swing.GroupLayout(capaDesktopPane);
        capaDesktopPane.setLayout(capaDesktopPaneLayout);
        capaDesktopPaneLayout.setHorizontalGroup(
            capaDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, capaDesktopPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nivel_logadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        capaDesktopPaneLayout.setVerticalGroup(
            capaDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, capaDesktopPaneLayout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(nivel_logadoLabel)
                .addGap(27, 27, 27))
        );

        cadastrosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/database_edit.png"))); // NOI18N
        cadastrosMenu.setText("Cadastros");
        cadastrosMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        CadastrarClienteMenuItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadastrarClienteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_add.png"))); // NOI18N
        CadastrarClienteMenuItem.setText("Clientes");
        CadastrarClienteMenuItem.setToolTipText("");
        CadastrarClienteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarCliente(evt);
            }
        });
        cadastrosMenu.add(CadastrarClienteMenuItem);

        cadastrar_defensivoMenuItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cadastrar_defensivoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map_add.png"))); // NOI18N
        cadastrar_defensivoMenuItem.setText("Defensivos");
        cadastrar_defensivoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrar_defensivoMenuItemActionPerformed(evt);
            }
        });
        cadastrosMenu.add(cadastrar_defensivoMenuItem);

        CadastrarEntregaMenuItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadastrarEntregaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/package_add.png"))); // NOI18N
        CadastrarEntregaMenuItem.setText("Vendas");
        CadastrarEntregaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarEntregaMenuItemActionPerformed(evt);
            }
        });
        cadastrosMenu.add(CadastrarEntregaMenuItem);

        cadastrar_FuncionariosMenuItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cadastrar_FuncionariosMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/user_red.png"))); // NOI18N
        cadastrar_FuncionariosMenuItem.setText("Funcionarios");
        cadastrar_FuncionariosMenuItem.setContentAreaFilled(false);
        cadastrar_FuncionariosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrar_FuncionariosMenuItemActionPerformed(evt);
            }
        });
        cadastrosMenu.add(cadastrar_FuncionariosMenuItem);

        BarraMenuBar.add(cadastrosMenu);

        consultasMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/magnifier.png"))); // NOI18N
        consultasMenu.setText("Consultas");
        consultasMenu.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N

        filtro_vendasMenuItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        filtro_vendasMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/application.png"))); // NOI18N
        filtro_vendasMenuItem.setText("Filtro de Vendas");
        filtro_vendasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtro_vendasMenuItemActionPerformed(evt);
            }
        });
        consultasMenu.add(filtro_vendasMenuItem);

        BarraMenuBar.add(consultasMenu);

        RelatoriosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map.png"))); // NOI18N
        RelatoriosMenu.setText("Relatórios");
        RelatoriosMenu.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N
        BarraMenuBar.add(RelatoriosMenu);

        gerenciamentoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/3_relatoriosPNG.png"))); // NOI18N
        gerenciamentoMenu.setText("Área Gerencial");
        gerenciamentoMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/application_edit.png"))); // NOI18N
        jMenuItem1.setText("Gerenciar Vendas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        gerenciamentoMenu.add(jMenuItem1);

        BarraMenuBar.add(gerenciamentoMenu);

        designMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cog.png"))); // NOI18N
        designMenu.setText("Utilitários");
        designMenu.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N

        mudar_telaMenu.setText("Alterar Design");

        design_metalMenuItem.setText("Metal");
        design_metalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                design_metalMenuItemActionPerformed(evt);
            }
        });
        mudar_telaMenu.add(design_metalMenuItem);

        design_windowsMenuItem.setText("Windows");
        design_windowsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                design_windowsMenuItemActionPerformed(evt);
            }
        });
        mudar_telaMenu.add(design_windowsMenuItem);

        design_motifMenuItem.setText("Motif");
        design_motifMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                design_motifMenuItemActionPerformed(evt);
            }
        });
        mudar_telaMenu.add(design_motifMenuItem);

        designMenu.add(mudar_telaMenu);

        BarraMenuBar.add(designMenu);

        AjudaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/information.png"))); // NOI18N
        AjudaMenu.setText("Ajuda");
        AjudaMenu.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N

        suporteonlineMenuItem.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        suporteonlineMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/status_online.png"))); // NOI18N
        suporteonlineMenuItem.setText("Suporte Online");
        suporteonlineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suporteonlineMenuItemActionPerformed(evt);
            }
        });
        AjudaMenu.add(suporteonlineMenuItem);

        sobreMenuItem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sobreMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/map.png"))); // NOI18N
        sobreMenuItem.setText("Sobre o Sistema");
        sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItemActionPerformed(evt);
            }
        });
        AjudaMenu.add(sobreMenuItem);

        BarraMenuBar.add(AjudaMenu);

        setJMenuBar(BarraMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(capaDesktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capaDesktopPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("fsfsfsf");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void suporteonlineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suporteonlineMenuItemActionPerformed
        informaServiçoIndisponível();
    }//GEN-LAST:event_suporteonlineMenuItemActionPerformed

    private void CadastrarCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarCliente
        new ControladorCadastroCliente();
    }//GEN-LAST:event_CadastrarCliente

    private void cadastrar_defensivoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrar_defensivoMenuItemActionPerformed
      
        new ControladorCadastroHerbicida();
       
    }//GEN-LAST:event_cadastrar_defensivoMenuItemActionPerformed

    private void terminarSistema(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_terminarSistema
              System.exit(0);
    }//GEN-LAST:event_terminarSistema

    private void CadastrarEntregaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarEntregaMenuItemActionPerformed
       new ControladorCadastroVenda();
    }//GEN-LAST:event_CadastrarEntregaMenuItemActionPerformed

    private void sobreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreMenuItemActionPerformed
        JOptionPane.showMessageDialog(null,"Sistema desenvolvido por Souza Softwares","Sobre o Sistema", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_sobreMenuItemActionPerformed

    private void data_horaTimerOnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_horaTimerOnTime
        String data_atual = Data.DataHora();
        data_horaLabel.setVisible(true);
        data_horaLabel.setText(data_atual);
    }//GEN-LAST:event_data_horaTimerOnTime

    private void clientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesButtonActionPerformed
       new ControladorCadastroCliente();
      
    }//GEN-LAST:event_clientesButtonActionPerformed

    private void herbicidabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_herbicidabuttonActionPerformed
        new ControladorCadastroHerbicida();
    }//GEN-LAST:event_herbicidabuttonActionPerformed

    private void design_metalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_design_metalMenuItemActionPerformed
        try{
             UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
             SwingUtilities.updateComponentTreeUI(this);
            
        }catch(Exception erro){
               JOptionPane.showConfirmDialog(this, erro);
            }
    }//GEN-LAST:event_design_metalMenuItemActionPerformed

    private void design_windowsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_design_windowsMenuItemActionPerformed
        try{
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             SwingUtilities.updateComponentTreeUI(this);
            
        }catch(Exception erro){
               JOptionPane.showConfirmDialog(this, erro);
        }                                                  

    }//GEN-LAST:event_design_windowsMenuItemActionPerformed

    private void design_motifMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_design_motifMenuItemActionPerformed
       try{
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
             SwingUtilities.updateComponentTreeUI(this);
            
        }catch(Exception erro){
               JOptionPane.showConfirmDialog(this, erro);
        }
    }//GEN-LAST:event_design_motifMenuItemActionPerformed

    private void cadastrar_FuncionariosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrar_FuncionariosMenuItemActionPerformed
        new ControladorCadastroFuncionario();
    }//GEN-LAST:event_cadastrar_FuncionariosMenuItemActionPerformed

    private void vendasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendasButtonActionPerformed

        new ControladorCadastroVenda();
      
    }//GEN-LAST:event_vendasButtonActionPerformed

    private void sairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_sairButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new ControladorGerenciamentoVenda(); 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void filtro_vendasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtro_vendasMenuItemActionPerformed
             new JanelaPesquisaEmFiltro().setVisible(true);; 
    }//GEN-LAST:event_filtro_vendasMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjudaMenu;
    private javax.swing.JMenuBar BarraMenuBar;
    javax.swing.JMenuItem CadastrarEntregaMenuItem;
    private javax.swing.JMenu RelatoriosMenu;
    private javax.swing.JMenuItem cadastrar_FuncionariosMenuItem;
    private javax.swing.JMenuItem cadastrar_defensivoMenuItem;
    private javax.swing.JMenu cadastrosMenu;
    private javax.swing.JDesktopPane capaDesktopPane;
    public javax.swing.JLabel cargo_logadoLabel;
    private javax.swing.JButton clientesButton;
    private javax.swing.JButton consultaButton;
    private javax.swing.JMenu consultasMenu;
    private javax.swing.JLabel data_horaLabel;
    private org.netbeans.examples.lib.timerbean.Timer data_horaTimer;
    private javax.swing.JMenu designMenu;
    private javax.swing.JMenuItem design_metalMenuItem;
    private javax.swing.JMenuItem design_motifMenuItem;
    private javax.swing.JMenuItem design_windowsMenuItem;
    private javax.swing.JMenuItem filtro_vendasMenuItem;
    private javax.swing.JMenu gerenciamentoMenu;
    private javax.swing.JButton herbicidabutton;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mudar_telaMenu;
    public javax.swing.JLabel nivel_logadoLabel;
    private javax.swing.JButton sairButton;
    private javax.swing.JMenuItem sobreMenuItem;
    private javax.swing.JMenuItem suporteonlineMenuItem;
    public javax.swing.JLabel usuario_logadoLabel;
    private javax.swing.JLabel usuario_logadoLabel2;
    private javax.swing.JButton vendasButton;
    // End of variables declaration//GEN-END:variables
}
