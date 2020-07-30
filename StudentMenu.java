/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ashutosh
 */
public class StudentMenu extends javax.swing.JFrame {

        /**
         * Creates new form StudentMenu
         */
        public StudentMenu() {
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

                search = new javax.swing.JButton();
                mybooks = new javax.swing.JButton();

                FormListener formListener = new FormListener();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                search.setText("Search All Books");
                search.addActionListener(formListener);

                mybooks.setText("View My Books");
                mybooks.addActionListener(formListener);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(mybooks, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(89, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mybooks, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(118, Short.MAX_VALUE))
                );

                pack();
        }

        // Code for dispatching events from components to event handlers.

        private class FormListener implements java.awt.event.ActionListener {
                FormListener() {}
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource() == mybooks) {
                                StudentMenu.this.mybooksActionPerformed(evt);
                        }
                        else if (evt.getSource() == search) {
                                StudentMenu.this.searchActionPerformed(evt);
                        }
                }
        }// </editor-fold>//GEN-END:initComponents

        private void mybooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybooksActionPerformed
                // TODO add your handling code here:
                JFrame f = new JFrame("Book List"); 
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection con = Library.connect();
                String sql= "SELECT BOOK.BOOK_NAME, BOOK.BOOK_ID, BOOK.BOOK_AUTHOR, BOOK.BOOK_PUBLISHER, BOOK.BOOK_SHELF FROM BOOK INNER JOIN " +
                        "ISSUEDBOOKS ON BOOK.BOOK_ID = ISSUEDBOOKS.BOOK_ID " +
                        "WHERE ISSUEDBOOKS.STUDENT_ID = '" + Library.user + "'" ;
                try {
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("USE LIBRARYMANAGEMENTSYSTEM");
                        ResultSet rs = stmt.executeQuery(sql);
                        JTable book_list= new JTable(Library.buildTableModel(rs)); 
                        JScrollPane scrollPane = new JScrollPane(book_list); 
                        f.add(scrollPane);
                        f.setSize(800, 400); 
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                } catch (Exception e) {
                     e.printStackTrace();
                }  
        }//GEN-LAST:event_mybooksActionPerformed

        private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
                // TODO add your handling code here:
                JFrame f = new JFrame("Book List"); 
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection con = Library.connect();
                String sql="SELECT * FROM BOOK WHERE BOOK_ID NOT IN (SELECT BOOK_ID FROM ISSUEDBOOKS) ORDER BY BOOK_NAME ASC";
                try {
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("USE LIBRARYMANAGEMENTSYSTEM");
                        ResultSet rs = stmt.executeQuery(sql);
                        JTable book_list= new JTable(Library.buildTableModel(rs)); 
                        JScrollPane scrollPane = new JScrollPane(book_list); 
                        f.add(scrollPane);
                        f.setSize(800, 400); 
                        f.setVisible(true);
                        f.setLocationRelativeTo(null);
                } catch (Exception e) {
                     e.printStackTrace();
                } 
        }//GEN-LAST:event_searchActionPerformed

        /**
         * @param args the command line arguments
         */
//        public static void main(String args[]) {
//                //</editor-fold>
//
//                /* Create and display the form */
//                java.awt.EventQueue.invokeLater(new Runnable() {
//                        public void run() {
//                                new StudentMenu().setVisible(true);
//                        }
//                });
//        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        public javax.swing.JButton mybooks;
        public javax.swing.JButton search;
        // End of variables declaration//GEN-END:variables
}
