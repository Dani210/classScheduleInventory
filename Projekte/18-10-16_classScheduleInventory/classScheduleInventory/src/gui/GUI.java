package gui;

import bl.Appointment;
import bl.AppointmentInventory;
import bl.Schedule;
import bl.ScheduleInventory;
import cryptStation.Cryptor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    //Verwaltung der HÜs und Termine
    private ScheduleInventory schedInv;
    private AppointmentInventory appInv;

    //zum Auswählen der lokalen Dateien
    private final JFileChooser chooser;

    //Dialog zum Hinzufügen von Einträgen (Sched & App)
    private AddEntryDlg addingDialog;
    //Dialog zur 'sicheren' Eingabe von Username und Password
    private LoginDlg loginDialog;

    //Beinhaltet den Username in plaintext
    private String savedUsername;
    //Inhalt des Passwords verschlüsselt
    private char[] savedPassword;
    

    //lokale gespeicherte Dateien, um dem User Arbeit zu ersparen
    //wie savedUsername und Password
    private File savedLocalSchedulesFile;
    private File savedLocalAppointmentsFile;

    public GUI() {
        initComponents();

        schedInv = new ScheduleInventory();
        appInv = new AppointmentInventory();

        listSchedules.setModel(schedInv);
        listAppointments.setModel(appInv);

        chooser = new JFileChooser();
        //im Ordner DataFiles befinden sich alle Daten die zur Laufzeit gesammelt wurden
        //local Files
        chooser.setCurrentDirectory(new File("src/DataFiles"));
        chooser.setMultiSelectionEnabled(false); //nur zur Sicherheit

        savedUsername = null;
        savedPassword = null; //always encrypted

        savedLocalSchedulesFile = null;
        savedLocalAppointmentsFile = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSwitcher = new javax.swing.JTabbedPane();
        plSchedules = new javax.swing.JPanel();
        scrollSchedules = new javax.swing.JScrollPane();
        listSchedules = new javax.swing.JList();
        plAppointments = new javax.swing.JPanel();
        scrollAppointments = new javax.swing.JScrollPane();
        listAppointments = new javax.swing.JList();
        mainMenuBar = new javax.swing.JMenuBar();
        menuData = new javax.swing.JMenu();
        onAdd = new javax.swing.JMenuItem();
        onRemove = new javax.swing.JMenuItem();
        onChange = new javax.swing.JMenuItem();
        menuFile = new javax.swing.JMenu();
        onLoadFromRemote = new javax.swing.JMenuItem();
        onLoadFromLocal = new javax.swing.JMenuItem();
        onSaveToLocal = new javax.swing.JMenuItem();
        onSaveToRemote = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Terminverwaltung");
        setMinimumSize(new java.awt.Dimension(300, 300));

        plSchedules.setLayout(new java.awt.BorderLayout());

        scrollSchedules.setViewportView(listSchedules);

        plSchedules.add(scrollSchedules, java.awt.BorderLayout.CENTER);

        panelSwitcher.addTab("Schedules", plSchedules);

        plAppointments.setLayout(new java.awt.BorderLayout());

        scrollAppointments.setViewportView(listAppointments);

        plAppointments.add(scrollAppointments, java.awt.BorderLayout.CENTER);

        panelSwitcher.addTab("Appointments", plAppointments);

        getContentPane().add(panelSwitcher, java.awt.BorderLayout.CENTER);

        menuData.setText("Data");

        onAdd.setText("Add");
        onAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddActionPerformed(evt);
            }
        });
        menuData.add(onAdd);

        onRemove.setText("Remove");
        menuData.add(onRemove);

        onChange.setText("Change");
        menuData.add(onChange);

        mainMenuBar.add(menuData);

        menuFile.setText("File");

        onLoadFromRemote.setText("load from remote");
        onLoadFromRemote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadFromRemoteActionPerformed(evt);
            }
        });
        menuFile.add(onLoadFromRemote);

        onLoadFromLocal.setText("load from local");
        onLoadFromLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadFromLocalActionPerformed(evt);
            }
        });
        menuFile.add(onLoadFromLocal);

        onSaveToLocal.setText("save to local");
        onSaveToLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSaveToLocalActionPerformed(evt);
            }
        });
        menuFile.add(onSaveToLocal);

        onSaveToRemote.setText("save to remote");
        menuFile.add(onSaveToRemote);

        mainMenuBar.add(menuFile);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getCurrentTab(){
        return panelSwitcher.getSelectedComponent().equals(plSchedules) ? SCHED_TAB : APP_TAB;
    }
    
    private void onAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddActionPerformed
        addingDialog = new AddEntryDlg(this, true);
        
        //gibt das gewählte Fenster an
        int currentTab = getCurrentTab();
        //true, wenn das momentane Fenster schedules anzeigt
        if (currentTab == SCHED_TAB) {
            //Default-Werte
            addingDialog.setTitle("Add schedule");
            addingDialog.setLabels("Subject:", "Until:");
            addingDialog.setTextFields("POS", "Serealisierung", LocalDate.now());
        } else if (currentTab == APP_TAB) {
            addingDialog.setTitle("Add appointment");
            addingDialog.setLabels("Title:", "Date:");
            addingDialog.setTextFields("NVS", "Test", LocalDate.now());
        }
        addingDialog.setVisible(true);

        
        //wird nur ausgeführt, wenn etwas hinzugefügt werden soll
        if (addingDialog.wasOk()) {
            if (currentTab == SCHED_TAB) {
                try {
                    schedInv.add(new Schedule(addingDialog.getTitle(), addingDialog.getDescription(), addingDialog.getDate()));
                } catch (DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(this, dtpe.getMessage(),
                            "Fehler: Datum", JOptionPane.ERROR_MESSAGE);
                }
            } else if (currentTab == APP_TAB) {
                try {
                    appInv.add(new Appointment(addingDialog.getTitle(), addingDialog.getDescription(), addingDialog.getDate()));
                } catch (DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(this, dtpe.getMessage(),
                            "Fehler: Datum", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_onAddActionPerformed

    //hauptsächlich fürs Ver- und Entschlüsseln gedacht
    private String charArrToString(char[] pass) {
        StringBuilder ret = new StringBuilder("");
        for (int i = 0; i < pass.length; i++) {
            ret.append(pass[i]);
        }
        return ret.toString();
    }

    private String[] getUsernameAndPasswordForRemoteAccess() {
        //für die FTP-Verbindung
        String username = null, plainPassword = null;

        //gibt an, ob die Logindaten gespeichert werden sollen
        int yesOrNo;

        if (savedUsername != null && savedPassword != null) {
            //wird nur ausgeführt, sollten schon Daten gespeichert worden sein
            if ((yesOrNo = JOptionPane.showConfirmDialog(this, "Use saved login?", "Login",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE))
                    == JOptionPane.YES_OPTION) 
            {
                username = savedUsername;
                plainPassword = Cryptor.decrypt(savedPassword);

            } else if (yesOrNo == JOptionPane.NO_OPTION) {
                loginDialog = new LoginDlg(this, true);
                loginDialog.setDefaults(savedUsername, Cryptor.decrypt(savedPassword));
                loginDialog.setVisible(true);

                username = loginDialog.getUsername();
                plainPassword = charArrToString(loginDialog.getPassword());
                loginDialog.clearFields(); //zur Sicherheit

                if (loginDialog.getSaveLogin()) {
                    this.savedUsername = username;
                    this.savedPassword = Cryptor.enrypt(plainPassword.toCharArray());
                }
            }
        } else if (savedUsername == null || savedPassword == null) {
            loginDialog = new LoginDlg(this, true);
            loginDialog.setDefaults(DEFAULTUSERNAME, DEFAULTPASSWORD);
            loginDialog.setVisible(true);

            username = loginDialog.getUsername();
            plainPassword = charArrToString(loginDialog.getPassword());
            loginDialog.clearFields();

            if (loginDialog.getSaveLogin()) {
                this.savedUsername = username;
                this.savedPassword = Cryptor.enrypt(plainPassword.toCharArray());
            }
        }

        return new String[]{username, plainPassword};
    }

    private File[] getLocalSchedulesAndAppointmentsFilesForSaving() {
        File localSchedulesFile = null, localAppointmentsFile = null;
        
        int yesOrNo = -1;
        if (savedLocalSchedulesFile != null && savedLocalAppointmentsFile != null) {
            if ((yesOrNo = JOptionPane.showConfirmDialog(this, "Use saved files?", "Files",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE))
                    == JOptionPane.YES_OPTION) {

                localSchedulesFile = savedLocalSchedulesFile;
                localAppointmentsFile = savedLocalAppointmentsFile;

            } else if (yesOrNo == JOptionPane.NO_OPTION) {
                chooser.setDialogTitle("Local Schedules-File");
                localSchedulesFile = chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;
                chooser.setDialogTitle("Local Appointments-File");
                localAppointmentsFile = chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;

                if (JOptionPane.showConfirmDialog(this,
                        "Set files as default-files to save to?",
                        "Default Files", JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {

                    savedLocalSchedulesFile = localSchedulesFile;
                    savedLocalAppointmentsFile = localAppointmentsFile;

                }
            }
        } else if (savedLocalSchedulesFile == null || savedLocalAppointmentsFile == null) {
            chooser.setDialogTitle("Local Schedules-File");
            localSchedulesFile = chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;
            chooser.setDialogTitle("Local Appointments-File");
            localAppointmentsFile = chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;

            if (JOptionPane.showConfirmDialog(this,
                    "Set files as default-files to save to?",
                    "Default Files", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {

                savedLocalSchedulesFile = localSchedulesFile;
                savedLocalAppointmentsFile = localAppointmentsFile;

            }
        }
        
        return new File[]{localSchedulesFile, localAppointmentsFile};
    }

    private void onLoadFromRemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadFromRemoteActionPerformed
        String username = null;
        String password = null;

        File localSchedulesFile = null;
        File localAppointmentsFile = null;

        String[] userAndPass = getUsernameAndPasswordForRemoteAccess();
        username = userAndPass[0];
        password = userAndPass[1];

        if (username != null && password != null) {
            //DefaultFiles ? defFiles : chooser;
            File [] schedAndApp = getLocalSchedulesAndAppointmentsFilesForSaving();
            localSchedulesFile = schedAndApp[0];
            localAppointmentsFile = schedAndApp[1];
        }

        if (localSchedulesFile != null && localAppointmentsFile != null) {
            try {
                schedInv.loadFileFromRemote(
                        "ahif16.bplaced.net/www/classSchedules/Data/dataFile.html",
                        username, password,
                        localSchedulesFile);

                appInv.loadFileFromRemote(
                        "ahif16.bplaced.net/www/classSchedules/Data/terminFile.html",
                        username, password,
                        localAppointmentsFile);

            } catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(this,
                        "Host konnte nicht gefunden werden\n" + e.getMessage(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            } catch (MalformedURLException e) {
                JOptionPane.showMessageDialog(this,
                        "URL falsch\n" + e.getMessage(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Datei konnte nicht geöffnet werden.\n" + e.getMessage(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Datumsformat falsch\n" + e.getMessage(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_onLoadFromRemoteActionPerformed

    private void onLoadFromLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadFromLocalActionPerformed

    }//GEN-LAST:event_onLoadFromLocalActionPerformed

    private void onSaveToLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSaveToLocalActionPerformed
        
    }//GEN-LAST:event_onSaveToLocalActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList listAppointments;
    private javax.swing.JList listSchedules;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu menuData;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem onAdd;
    private javax.swing.JMenuItem onChange;
    private javax.swing.JMenuItem onLoadFromLocal;
    private javax.swing.JMenuItem onLoadFromRemote;
    private javax.swing.JMenuItem onRemove;
    private javax.swing.JMenuItem onSaveToLocal;
    private javax.swing.JMenuItem onSaveToRemote;
    private javax.swing.JTabbedPane panelSwitcher;
    private javax.swing.JPanel plAppointments;
    private javax.swing.JPanel plSchedules;
    private javax.swing.JScrollPane scrollAppointments;
    private javax.swing.JScrollPane scrollSchedules;
    // End of variables declaration//GEN-END:variables


    
    //Konstanten
    public static final String DEFAULTUSERNAME = "anonymous";
    public static final String DEFAULTPASSWORD = "";
    
    private static final int SCHED_TAB = 0;
    private static final int APP_TAB = 1;

}
