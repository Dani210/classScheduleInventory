package gui;

public class LoginDlg extends javax.swing.JDialog {

    private boolean userWantLogin;
    public boolean doesUserWantLogin(){ return userWantLogin; }
    
    //password in plain chars
    public void setDefaults(String username, String password){
        tfUsername.setText(username);
        tfPassword.setText(password);
    }
    
    public void clearFields(){
        tfUsername.setText("");
        tfPassword.setText("");
    }
    
    public String getUsername(){
        return tfUsername.getText();
    }
    
    //can only be called once
    public char [] getPassword(){
        return tfPassword.getPassword();
    }
    
    public boolean getSaveLogin(){
        return checkSaveLogin.isSelected();
    }
    
    
    public LoginDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        userWantLogin = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lbUsername = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lbPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        checkSaveLogin = new javax.swing.JCheckBox();
        onLogin = new javax.swing.JButton();
        onCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FTP Anmeldung");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lbUsername.setText("Username:");
        getContentPane().add(lbUsername, new java.awt.GridBagConstraints());

        tfUsername.setColumns(20);
        tfUsername.setText("anonymous");
        tfUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfUsernameFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfUsername, gridBagConstraints);

        lbPassword.setText("Passwort:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(lbPassword, gridBagConstraints);

        tfPassword.setColumns(20);
        tfPassword.setText("Password");
        tfPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPasswordFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfPassword, gridBagConstraints);

        checkSaveLogin.setText("Angemeldet bleiben");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(checkSaveLogin, gridBagConstraints);

        onLogin.setText("Anmelden");
        onLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(onLogin, gridBagConstraints);

        onCancel.setText("Abbrechen");
        onCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(onCancel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfUsernameFocusGained
        tfUsername.setSelectionStart(0);
        tfUsername.setSelectionEnd(tfUsername.getText().length());
    }//GEN-LAST:event_tfUsernameFocusGained

    private void tfPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPasswordFocusGained
        tfPassword.setSelectionStart(0);
        tfPassword.setSelectionEnd(tfPassword.getPassword().length);
    }//GEN-LAST:event_tfPasswordFocusGained

    private void onLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoginActionPerformed
        userWantLogin = true;
        this.setVisible(false);
    }//GEN-LAST:event_onLoginActionPerformed

    private void onCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCancelActionPerformed
        userWantLogin = false;
        this.setVisible(false);
    }//GEN-LAST:event_onCancelActionPerformed

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
            java.util.logging.Logger.getLogger(LoginDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginDlg dialog = new LoginDlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox checkSaveLogin;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JButton onCancel;
    private javax.swing.JButton onLogin;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
