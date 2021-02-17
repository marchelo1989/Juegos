/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Juegos.GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author march
 */
public class FrHome extends javax.swing.JFrame {

    /**
     * Creates new form FrHome
     */
    public FrHome() {
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false); 
        setTitle("Inicio de Juegos");
        String url="/Cl/Burgos/Juegos/IMG/";
        setIconImage(new ImageIcon(getClass().getResource(url+"Icono.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon MyImgCustom =new ImageIcon(this.getClass().getResource(url+"fondo1.jpg"));
        JLabel fondo= new JLabel();
        
        fondo.setIcon(MyImgCustom);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,MyImgCustom.getIconWidth(),MyImgCustom.getIconHeight());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPsx = new javax.swing.JButton();
        btnPs2 = new javax.swing.JButton();
        btnPsp = new javax.swing.JButton();
        btnPs3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPsx.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPsx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PSX.png"))); // NOI18N
        btnPsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPsxActionPerformed(evt);
            }
        });

        btnPs2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PS2.png"))); // NOI18N
        btnPs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPs2ActionPerformed(evt);
            }
        });

        btnPsp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PSP.png"))); // NOI18N
        btnPsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPspActionPerformed(evt);
            }
        });

        btnPs3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PS3.png"))); // NOI18N
        btnPs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPs3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PC.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico Archivos.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cl/Burgos/Juegos/ICO/ico PS4.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPsx)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPs2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPsp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPs3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPsx)
                    .addComponent(btnPs2)
                    .addComponent(btnPsp)
                    .addComponent(btnPs3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPsxActionPerformed
        // TODO add your handling code here:
        FrPsx frPsx = new FrPsx();
        frPsx.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPsxActionPerformed

    private void btnPs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPs2ActionPerformed
        // TODO add your handling code here:
        FrPs2 frPs2 = new FrPs2();
        frPs2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPs2ActionPerformed

    private void btnPspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPspActionPerformed
        // TODO add your handling code here:
        FrPsp frPsp = new FrPsp();
        frPsp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPspActionPerformed

    private void btnPs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPs3ActionPerformed
        // TODO add your handling code here:
        FrPs3 frPs3 = new FrPs3();
        frPs3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPs3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FrPc frPc = new FrPc();
        frPc.setVisible(true);
        this.dispose();
//        JOptionPane.showMessageDialog(null, "Sin Terminar");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FrDescargarImg frDescargarImg = new FrDescargarImg();
        frDescargarImg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        FrPs4 frPs4 = new FrPs4();
        frPs4.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPs2;
    private javax.swing.JButton btnPs3;
    private javax.swing.JButton btnPsp;
    private javax.swing.JButton btnPsx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}
