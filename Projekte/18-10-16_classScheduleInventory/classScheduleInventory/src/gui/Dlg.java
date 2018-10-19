package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dlg extends javax.swing.JDialog {

    private boolean wasOk;
    public boolean wasOk(){ return wasOk; }
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public void setLabels(String title, String date){
        lbTitle.setText(title);
        lbDate.setText(date);
    }
    public void setTextFields(String title, String description, LocalDate date){
        tfTitle.setText(title);
        tfDescription.setText(description);
        tfDate.setText(date.format(dtf));
    }
    
    public Dlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        wasOk = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        lbDescription = new javax.swing.JLabel();
        tfDescription = new javax.swing.JTextField();
        lbDate = new javax.swing.JLabel();
        tfDate = new javax.swing.JTextField();
        onOk = new javax.swing.JButton();
        onCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        lbTitle.setText("Title/Subject");
        getContentPane().add(lbTitle);

        tfTitle.setColumns(20);
        getContentPane().add(tfTitle);

        lbDescription.setText("Description");
        getContentPane().add(lbDescription);

        tfDescription.setColumns(20);
        getContentPane().add(tfDescription);

        lbDate.setText("Due/Date");
        getContentPane().add(lbDate);

        tfDate.setColumns(20);
        getContentPane().add(tfDate);

        onOk.setText("OK");
        onOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOkActionPerformed(evt);
            }
        });
        getContentPane().add(onOk);

        onCancel.setText("Cancel");
        onCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCancelActionPerformed(evt);
            }
        });
        getContentPane().add(onCancel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOkActionPerformed
        wasOk = true;
        this.dispose();
    }//GEN-LAST:event_onOkActionPerformed

    private void onCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCancelActionPerformed
        wasOk = false;
        this.dispose();
    }//GEN-LAST:event_onCancelActionPerformed

    public String getTitle(){
        return tfTitle.getText();
    }
    
    public String getDescription(){
        return tfDescription.getText();
    }
    
    public LocalDate getDate()
            throws DateTimeParseException
    {
        return LocalDate.parse(tfDate.getText(), dtf);
    }
    
    
    
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
            java.util.logging.Logger.getLogger(Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dlg dialog = new Dlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JButton onCancel;
    private javax.swing.JButton onOk;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables
}
