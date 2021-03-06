package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashutosh
 */
public class Login extends javax.swing.JFrame {

        /**
         * Creates new form Login
         */
        public Login() {
                initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                register = new javax.swing.JButton();
                login = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                usr = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                pass = new javax.swing.JTextField();

                FormListener formListener = new FormListener();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                register.setText("REGISTER");
                register.addActionListener(formListener);

                login.setText("LOGIN");
                login.addActionListener(formListener);

                jLabel1.setText("USERNAME");

                jLabel2.setText("PASSWORD");

                jLabel3.setText("New Student?");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 182, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(register)
                                .addGap(237, 237, 237))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(63, 63, 63)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(usr, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                        .addComponent(pass)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(245, 245, 245)
                                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usr, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(login)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(register)
                                        .addComponent(jLabel3))
                                .addContainerGap(119, Short.MAX_VALUE))
                );

                pack();
        }

        // Code for dispatching events from components to event handlers.

        private class FormListener implements java.awt.event.ActionListener {
                FormListener() {}
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource() == register) {
                                Login.this.registerActionPerformed(evt);
                        }
                        else if (evt.getSource() == login) {
                                Login.this.loginActionPerformed(evt);
                        }
                }
        }// </editor-fold>//GEN-END:initComponents

        private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
                // TODO add your handling code here:
                String u = usr.getText();
                String p = pass.getText();
                
                if(u.equals("admin") && p.equals("admin")) {
                        this.dispose();
                        java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                        new AdminMenu().setVisible(true);
                                }
                        });
                }
                else {
                        
                        try {
                                Connection con = Library.connect();
                                Statement stmt = con.createStatement();
                                stmt.execute("USE LIBRARYMANAGEMENTSYSTEM");
                                String query = "SELECT * FROM USER WHERE USER_ID = " + "'" + u + "' AND USER_PASSWORD =  '" + p +"'";
                                ResultSet rs = stmt.executeQuery(query);
                                if(rs.next()) {
                                        query = "SELECT STUDENT_ID FROM STUDENT WHERE STUDENT_ID = " + "'" + u + "'"; 
                                        rs = stmt.executeQuery(query);
                                        if(rs.next()) {
                                                Library.user = u;
                                                this.setVisible(false);
                                                java.awt.EventQueue.invokeLater(new Runnable() {
                                                        public void run() {
                                                                new StudentMenu().setVisible(true);
                                                        }
                                                });
                                        }
                                        else {
                                                query = "SELECT TEACHER_ID FROM TEACHER WHERE TEACHER_ID = " + "'" + u + "'"; 
                                                rs = stmt.executeQuery(query);
                                                if(rs.next()) {
                                                        Library.user = u;
                                                        this.setVisible(false);
                                                        java.awt.EventQueue.invokeLater(new Runnable() {
                                                                public void run() {
                                                                        new TeacherMenu().setVisible(true);
                                                                }
                                                        });
                                                }
                                                else {
                                                        JOptionPane.showMessageDialog(null, "Invalid Credential!");

                                                }
                                        }
                                }
                        }
                        catch(Exception e) {
                                e.printStackTrace();
                        }
                }
        }//GEN-LAST:event_loginActionPerformed

        private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
                // TODO add your handling code here:
                this.dispose();
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new Register().setVisible(true);
                        }
                });
        }//GEN-LAST:event_registerActionPerformed

        /**
         * @param args the command line arguments
         */
        

        // Variables declaration - do not modify//GEN-BEGIN:variables
        public javax.swing.JLabel jLabel1;
        public javax.swing.JLabel jLabel2;
        public javax.swing.JLabel jLabel3;
        public javax.swing.JButton login;
        public javax.swing.JTextField pass;
        public javax.swing.JButton register;
        public javax.swing.JTextField usr;
        // End of variables declaration//GEN-END:variables
}
