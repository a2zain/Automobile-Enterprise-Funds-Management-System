package cs.ia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Report1 extends javax.swing.JFrame {
    public static String url = "jdbc:mysql://localhost/funds?useSSL=false";
    public static String user = "root";
    public static String password = "";
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;
    public static Connection conn;
    public static Statement sta;
    public static ResultSet res;
    public static DecimalFormat df = new DecimalFormat(".##");
    
    public Report1() {
        initComponents();
        storeTable();
        sortTable();
    }
    
    public void sortTable()
    {
        DefaultTableModel dG = (DefaultTableModel) jTableReport1.getModel();
        TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel> (dG);
        jTableReport1.setRowSorter(sort);
        
        DefaultTableModel dT = (DefaultTableModel) jTableReport1Total.getModel();
        TableRowSorter<DefaultTableModel> sorts = new TableRowSorter<DefaultTableModel> (dT);
        jTableReport1Total.setRowSorter(sorts);
    }
    
    public void storeTable()
    {
        DefaultTableModel dGen = (DefaultTableModel) jTableReport1.getModel();
        DefaultTableModel dTot = (DefaultTableModel) jTableReport1Total.getModel();
        try
        {
            con = DriverManager.getConnection(url, user, password); 
            st = con.createStatement();
            String query = "SELECT divisions.divisionName, t1.numRequests, t1.tot_us, t1.tot_eu, t1.tot_sar FROM divisions LEFT OUTER JOIN (SELECT divCode, COUNT(*) AS numRequests, "
                    + "SUM(USDchange) AS tot_us, SUM(EURchange) AS tot_eu, SUM(SARchange) AS tot_sar FROM fundsdetails WHERE complete=0 GROUP BY divCode)t1 ON divisions.divisionCode="
                    + "t1.divCode";
            rs = st.executeQuery(query); 
            while(rs.next())
            {
                double total = (rs.getDouble(3)*3.75) + (rs.getDouble(4)*4.35) + rs.getDouble(5); //get the total amount in SAR using the currency conversions researched online
                dGen.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), df.format(total)}); 
            }
            
            conn = DriverManager.getConnection(url, user, password);
            sta = conn.createStatement();
            String query2 = "SELECT t2.divisionName, fundsdetails.dateRequested, fundsdetails.dateArranged, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, fundsdetails.USDchange, "
                    + "fundsdetails.EURchange, fundsdetails.SARchange, fundsdetails.purpose FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON fundsdetails."
                    + "bankCode=t1.bankCode LEFT OUTER JOIN (SELECT divisionCode, divisionName FROM divisions)t2 ON fundsdetails.divCode=t2.divisionCode WHERE complete=0";
            res = sta.executeQuery(query2); 
            while(res.next())
            {
                dTot.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getDouble(8), 
                    res.getDouble(9), res.getString(10)}); 
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e); 
        }
        finally 
        {
            try
            {
                if (rs != null) 
                {
                    rs.close();
                }
                                
                if (st != null) 
                {
                    st.close();
                }
                
                if (con != null) 
                {
                    con.close();
                }
                
                if (res != null) 
                {
                    res.close();
                }
                                
                if (sta != null) 
                {
                    sta.close();
                }
                
                if (conn != null) 
                {
                    conn.close();
                }
            }
            catch(Exception exc)
            {
                JOptionPane.showMessageDialog(null, exc); 
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLogoMain = new javax.swing.JLabel();
        jLMainTitle = new javax.swing.JLabel();
        jLSummaryHeading = new javax.swing.JLabel();
        jSeparatorMain = new javax.swing.JSeparator();
        jTBPending = new javax.swing.JTabbedPane();
        jPGeneral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReport1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableReport1Total = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLogoMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLogoMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs/ia/download.png"))); // NOI18N
        jLogoMain.setMaximumSize(new java.awt.Dimension(300, 150));
        jLogoMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoMainMouseClicked(evt);
            }
        });
        getContentPane().add(jLogoMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(908, 16, 369, 116));

        jLMainTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLMainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLMainTitle.setText("Company Fund Requests");
        getContentPane().add(jLMainTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 16, 884, 116));

        jLSummaryHeading.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLSummaryHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSummaryHeading.setText("Summary of Fund Requests Pending");
        getContentPane().add(jLSummaryHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 601, -1));
        getContentPane().add(jSeparatorMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1292, 1));

        jPGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableReport1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Division", "Number of Requests", "USD", "EUR", "SAR", "Total in SAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableReport1);

        jPGeneral.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1230, 287));

        jTBPending.addTab("Overview", jPGeneral);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableReport1Total.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Division", "Requested", "Arranged", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR", "Purpose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableReport1Total);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1230, 300));

        jTBPending.addTab("Full Details", jPanel1);

        getContentPane().add(jTBPending, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 1260, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        DefaultTableModel dG = (DefaultTableModel) jTableReport1.getModel();
        DefaultTableModel dT = (DefaultTableModel) jTableReport1Total.getModel();
        dG.setRowCount(0);
        dT.setRowCount(0);
        storeTable();
    }//GEN-LAST:event_formWindowActivated

    private void jLogoMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoMainMouseClicked

    }//GEN-LAST:event_jLogoMainMouseClicked

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
            java.util.logging.Logger.getLogger(Report1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLMainTitle;
    private javax.swing.JLabel jLSummaryHeading;
    private javax.swing.JLabel jLogoMain;
    private javax.swing.JPanel jPGeneral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparatorMain;
    private javax.swing.JTabbedPane jTBPending;
    private javax.swing.JTable jTableReport1;
    private javax.swing.JTable jTableReport1Total;
    // End of variables declaration//GEN-END:variables
}