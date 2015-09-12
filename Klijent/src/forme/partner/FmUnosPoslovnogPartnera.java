/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.partner;

import domen.Mesto;
import domen.PoslovniPartner;
import forme.mesto.KontrolorKIMesta;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author student1
 */
public class FmUnosPoslovnogPartnera extends javax.swing.JDialog {

    private PoslovniPartner partner;

    /**
     * Creates new form FmUnosPoslovnogPartnera
     */
    public FmUnosPoslovnogPartnera(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnIzmeni.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbgPartner = new javax.swing.ButtonGroup();
        btnSnimi = new javax.swing.JButton();
        cbMesto = new javax.swing.JComboBox();
        txtBroj = new javax.swing.JTextField();
        txtUlica = new javax.swing.JTextField();
        txtKontakt = new javax.swing.JTextField();
        txtPib = new javax.swing.JTextField();
        txtNaziv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnIzmeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unos poslovnog partnera");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnSnimi.setText("Sačuvaj poslovnog partnera");
        btnSnimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnimiActionPerformed(evt);
            }
        });

        cbMesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Naziv:");

        jLabel3.setText("PIB:");

        jLabel7.setText("Kontakt:");

        jLabel4.setText("Ulica:");

        jLabel5.setText("Broj:");

        jLabel6.setText("Mesto:");

        btnIzmeni.setText("Izmeni poslovnog partnera");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNaziv)
                            .addComponent(txtPib)
                            .addComponent(txtUlica)
                            .addComponent(txtBroj)
                            .addComponent(cbMesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKontakt)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSnimi)
                        .addGap(27, 27, 27)
                        .addComponent(btnIzmeni)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUlica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSnimi)
                    .addComponent(btnIzmeni))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSnimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnimiActionPerformed
        KontorlerKIUnosPoslovnogPartnera.zapamtiPoslovnogPartnera(txtNaziv,
                txtPib, txtKontakt, txtUlica, txtBroj, cbMesto, this);

    }//GEN-LAST:event_btnSnimiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        popuniCbMesta();
        if (partner != null) {
            cbMesto.setSelectedItem(partner.getAdresa().getMesto());
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        int o = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da izmenite poslovnog partnera?", 
                "Potvrda", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (o) {
            case JOptionPane.YES_OPTION:
                KontrolerKIIzmenaPoslovnogPartnera.izmeniPoslovnogPartnera(partner, txtNaziv,
                        txtPib, txtKontakt, txtUlica, txtBroj, cbMesto, this);
                break;
            case JOptionPane.NO_OPTION:
                dispose();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

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
            java.util.logging.Logger.getLogger(FmUnosPoslovnogPartnera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FmUnosPoslovnogPartnera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FmUnosPoslovnogPartnera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FmUnosPoslovnogPartnera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FmUnosPoslovnogPartnera dialog = new FmUnosPoslovnogPartnera(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnSnimi;
    private javax.swing.JComboBox cbMesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.ButtonGroup jbgPartner;
    private javax.swing.JTextField txtBroj;
    private javax.swing.JTextField txtKontakt;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtPib;
    private javax.swing.JTextField txtUlica;
    // End of variables declaration//GEN-END:variables

    private void popuniCbMesta() {
        try {
            List<Mesto> lm = KontrolorKIMesta.vratiMesta();
            cbMesto.removeAllItems();
            cbMesto.setModel(new DefaultComboBoxModel(lm.toArray()));
            cbMesto.setSelectedIndex(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    void popuniPodatke(PoslovniPartner p) {
        partner = p;
        txtNaziv.setText(p.getNaziv());
        txtPib.setText(p.getPib());
        txtKontakt.setText(p.getKontakt());
        txtUlica.setText(p.getAdresa().getUlica());
        txtBroj.setText(p.getAdresa().getBroj());

        btnSnimi.setEnabled(false);
        btnIzmeni.setEnabled(true);
        setVisible(true);
    }
}