package gui;

import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.UIManager;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import socket.GeneralSocket;

/**
 *
 * @author DiogoCirne
 */
public class campoDeBatalha extends javax.swing.JFrame {

    GeneralSocket generalSocket;

    public campoDeBatalha() {
        this.setPreferredSize(new Dimension(700, 450));
        this.setIconImage(ImageUtilities.loadImage("imgs/anchor.png"));
        initComponents();
        pnl_grid.fillGrid(5);
        pnl_grid.createShips();
        pnl_gridEnemy.fillGrid(5);
        try {
            InetAddress addr = InetAddress.getLocalHost();
            txt_ip.setText(addr.getHostAddress());
        } catch (UnknownHostException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_grid = new gui.gridPanel();
        pnl_gridEnemy = new gui.gridPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_ip = new javax.swing.JTextField();
        txt_port = new javax.swing.JTextField();
        cmb_socketType = new javax.swing.JComboBox();
        btn_startSocket = new javax.swing.JButton();
        txt_write = new javax.swing.JTextField();
        btn_write = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batalha Naval Controlador por Voz - Processamento por Redes Neurais\n");
        setMinimumSize(new java.awt.Dimension(520, 330));

        javax.swing.GroupLayout pnl_gridLayout = new javax.swing.GroupLayout(pnl_grid);
        pnl_grid.setLayout(pnl_gridLayout);
        pnl_gridLayout.setHorizontalGroup(
            pnl_gridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );
        pnl_gridLayout.setVerticalGroup(
            pnl_gridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        pnl_gridEnemy.setPreferredSize(new java.awt.Dimension(331, 241));

        javax.swing.GroupLayout pnl_gridEnemyLayout = new javax.swing.GroupLayout(pnl_gridEnemy);
        pnl_gridEnemy.setLayout(pnl_gridEnemyLayout);
        pnl_gridEnemyLayout.setHorizontalGroup(
            pnl_gridEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        pnl_gridEnemyLayout.setVerticalGroup(
            pnl_gridEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txt_ip.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ip.setToolTipText("IP");
        txt_ip.setEnabled(false);

        txt_port.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_port.setText("7300");
        txt_port.setToolTipText("Port");

        cmb_socketType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Servidor", "Cliente" }));
        cmb_socketType.setToolTipText("");
        cmb_socketType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_socketTypeItemStateChanged(evt);
            }
        });

        btn_startSocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/antenna.png"))); // NOI18N
        btn_startSocket.setToolTipText("Abrir conexão");
        btn_startSocket.setContentAreaFilled(false);
        btn_startSocket.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_startSocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startSocketActionPerformed(evt);
            }
        });

        txt_write.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_write.setToolTipText("Mensagem de comunicação");
        txt_write.setEnabled(false);
        txt_write.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_writePropertyChange(evt);
            }
        });

        btn_write.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/send.png"))); // NOI18N
        btn_write.setToolTipText("Enviar mensagem");
        btn_write.setContentAreaFilled(false);
        btn_write.setEnabled(false);
        btn_write.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_writeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cmb_socketType, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_startSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_write, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_write, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(btn_startSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmb_socketType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_write, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_write))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_gridEnemy, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_gridEnemy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startSocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startSocketActionPerformed
        generalSocket = new GeneralSocket(txt_write);
        cmb_socketType.setEnabled(false);
        txt_port.setEnabled(false);
        txt_ip.setEnabled(false);
        switch (cmb_socketType.getSelectedIndex()) {
            case 0:
                this.setTitle("Servidor: Batalha Naval Controlador por Voz - Processamento por Redes Neurais");
                txt_write.setEnabled(false);
                btn_write.setEnabled(false);
                txt_write.setText("Aguardando...");
                generalSocket.makeServerSocket(Integer.parseInt(txt_port.getText()));
                generalSocket.makeProcessamento(pnl_grid, pnl_gridEnemy);
                generalSocket.start(false);
                break;
            case 1:
                this.setTitle("Cliente: Batalha Naval Controlador por Voz - Processamento por Redes Neurais");
                txt_write.setEnabled(true);
                btn_write.setEnabled(true);
                generalSocket.makeClientSocket(txt_ip.getText(), Integer.parseInt(txt_port.getText()));
                generalSocket.makeProcessamento(pnl_grid, pnl_gridEnemy);
                generalSocket.start(true);
                break;
        }
        btn_startSocket.setEnabled(false);
    }//GEN-LAST:event_btn_startSocketActionPerformed

    private void cmb_socketTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_socketTypeItemStateChanged
        switch (cmb_socketType.getSelectedIndex()) {
            case 0:
                try {
                    InetAddress addr = InetAddress.getLocalHost();
                    txt_ip.setText(addr.getHostAddress());
                    txt_ip.setEnabled(false);
                    btn_startSocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/antenna.png")));
                    btn_startSocket.setToolTipText("Abrir Conexão");
                } catch (UnknownHostException ex) {
                    Exceptions.printStackTrace(ex);
                }
                break;
            case 1:
                txt_ip.setEnabled(true);
                btn_startSocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/connect.png")));
                btn_startSocket.setToolTipText("Conectar");
                txt_ip.setText("localhost");
                break;
        }
    }//GEN-LAST:event_cmb_socketTypeItemStateChanged

    private void btn_writeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_writeActionPerformed
        generalSocket.resumeThread();
//        btn_write.setEnabled(false);
//        txt_write.setEnabled(false);
//        txt_write.setText("Aguardando...");
    }//GEN-LAST:event_btn_writeActionPerformed

    private void txt_writePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_writePropertyChange
        btn_write.setEnabled(txt_write.isEnabled());
    }//GEN-LAST:event_txt_writePropertyChange

    public void atacar(String celula) {
        txt_write.setText(celula);
        generalSocket.resumeThread();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(campoDeBatalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(campoDeBatalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(campoDeBatalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(campoDeBatalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new campoDeBatalha().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_startSocket;
    private javax.swing.JButton btn_write;
    private javax.swing.JComboBox cmb_socketType;
    private javax.swing.JPanel jPanel1;
    private gui.gridPanel pnl_grid;
    private gui.gridPanel pnl_gridEnemy;
    private javax.swing.JTextField txt_ip;
    private javax.swing.JTextField txt_port;
    private javax.swing.JTextField txt_write;
    // End of variables declaration//GEN-END:variables
}
