package cs.ia;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class ManageFundsRequest extends javax.swing.JFrame {
    public static final Funds [] funds = new Funds[1000];
    public static int maxIndex = -1;
    public static int count = 0;
    public static int diviCode;
    public static String dateR;
    public static String dateA;
    public static int bnkCode;
    public static int bnkNum;
    public static String iBAN;
    public static int cod;
    public static String cur;
    public static double amunt;
    public static String purp;
    public static boolean complete;
    public static String url = "jdbc:mysql://localhost/funds?useSSL=false";
    public static String user = "root";
    public static String password = "";
    
    public ManageFundsRequest() throws ClassNotFoundException  {
        initComponents();
        sortTable();
        readFunds();
    }
    
    private void sortTable()
    {
        DefaultTableModel dA = (DefaultTableModel) jTableAdd.getModel();
        TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel> (dA);
        jTableAdd.setRowSorter(sort);
        
        DefaultTableModel dD = (DefaultTableModel) jTableDel.getModel();
        TableRowSorter<DefaultTableModel> so = new TableRowSorter<DefaultTableModel> (dD);
        jTableDel.setRowSorter(so);
        
        DefaultTableModel dS = (DefaultTableModel) jTableSearch.getModel();
        TableRowSorter<DefaultTableModel> s = new TableRowSorter<DefaultTableModel> (dS);
        jTableSearch.setRowSorter(s);
    }
        
    //method used in order to read the funds stored in the funds details table in the MySQL database
    private void readFunds() throws ClassNotFoundException
    {
        //connecting to the database
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int temp = -1;
        try
        {
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection(url, user, password);
            //trying to access all the data from the database using a query
            String query1 = "SELECT * FROM fundsDetails"; 
            st = con.createStatement(); 
            rs = st.executeQuery(query1); 
            //loop in order to store all the results from the query into an
            //object array created earlier in the class
            while(rs.next())
            {
                maxIndex++;
                cod = rs.getInt(1);
                diviCode = rs.getInt(2);
                dateR = rs.getString(3);
                dateA = rs.getString(4);
                bnkCode = rs.getInt(5);
                bnkNum = rs.getInt(6);
                iBAN = rs.getString(7);
                if(rs.getDouble(8) != 0)
                {
                    cur = "USD";
                    temp = 8;
                }
                else if(rs.getDouble(10) != 0)
                {
                    cur = "EUR";
                    temp = 10;
                }
                else
                {
                    cur = "SAR";
                    temp = 12;
                }
                amunt = rs.getDouble(temp);
                purp = rs.getString(14);
                complete = rs.getBoolean(15);
                funds[maxIndex] = new Funds(cod, diviCode, dateR, dateA, bnkCode, bnkNum, iBAN, cur, amunt, purp, complete);
            }
        }
        catch(SQLException e)
        {
             Logger lgr = Logger.getLogger(ManageFundsRequest.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
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

            } catch (SQLException ex) 
            {    
                Logger lgr = Logger.getLogger(ManageFundsRequest.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLArrangementTitle = new javax.swing.JLabel();
        jTFundsArr = new javax.swing.JTabbedPane();
        jPAddA = new javax.swing.JPanel();
        jLAddDiv = new javax.swing.JLabel();
        jCBAddDivisionA = new javax.swing.JComboBox<>();
        jLAddDateA = new javax.swing.JLabel();
        jLAddAmountA = new javax.swing.JLabel();
        jTAddAmountA = new javax.swing.JTextField();
        jBAddArrangement = new javax.swing.JButton();
        jBAddDisplay = new javax.swing.JButton();
        jLErrorMessage = new javax.swing.JLabel();
        jDAddDateA = new com.toedter.calendar.JDateChooser();
        jLAddANote = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableAdd = new javax.swing.JTable();
        jLAddHead = new javax.swing.JLabel();
        jPDelete = new javax.swing.JPanel();
        jLDelDiv = new javax.swing.JLabel();
        jBDelete = new javax.swing.JButton();
        jCBDeleteDiv = new javax.swing.JComboBox<>();
        jBDelDisplay = new javax.swing.JButton();
        jLDeleteNote = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableDel = new javax.swing.JTable();
        jLDelHead = new javax.swing.JLabel();
        jPSearch = new javax.swing.JPanel();
        jTSearchNonDate = new javax.swing.JTextField();
        jLSearchDiv = new javax.swing.JLabel();
        jCBSearchDiv = new javax.swing.JComboBox<>();
        jBSearchDisplay = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableSearch = new javax.swing.JTable();
        jLSearchHead = new javax.swing.JLabel();
        jSeparatorArrangement = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLogoArrangement = new javax.swing.JLabel();

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLArrangementTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLArrangementTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLArrangementTitle.setText("MANAGE FUNDS REQUESTS");
        getContentPane().add(jLArrangementTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 978, 103));

        jPAddA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPAddAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPAddAFocusLost(evt);
            }
        });
        jPAddA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAddDiv.setText("Division");
        jPAddA.add(jLAddDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        jCBAddDivisionA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "BMW", "Hyundai", "JLR" }));
        jCBAddDivisionA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAddDivisionAActionPerformed(evt);
            }
        });
        jPAddA.add(jCBAddDivisionA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 20, 227, -1));

        jLAddDateA.setVisible(false);
        jLAddDateA.setText("Date Funds Arranged");
        jPAddA.add(jLAddDateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 200, -1, -1));

        jLAddAmountA.setVisible(false);
        jLAddAmountA.setText("Amount");
        jPAddA.add(jLAddAmountA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 310, -1, -1));

        jTAddAmountA.setVisible(false);
        jTAddAmountA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTAddAmountAKeyTyped(evt);
            }
        });
        jPAddA.add(jTAddAmountA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 300, 302, -1));

        jBAddArrangement.setVisible(false);
        jBAddArrangement.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBAddArrangement.setText("Enter");
        jBAddArrangement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddArrangementActionPerformed(evt);
            }
        });
        jPAddA.add(jBAddArrangement, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 420, -1, -1));

        jBAddDisplay.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBAddDisplay.setText("Next");
        jBAddDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddDisplayActionPerformed(evt);
            }
        });
        jPAddA.add(jBAddDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 110, -1, -1));
        jPAddA.add(jLErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(1307, 388, 422, 44));

        jDAddDateA.setVisible(false);
        jPAddA.add(jDAddDateA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 190, 302, -1));

        jLAddANote.setVisible(false);
        jLAddANote.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLAddANote.setText("Please select the fund request from the table you wish to add an arrangement for");
        jPAddA.add(jLAddANote, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 160, 510, -1));

        jTableAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Requested", "Arranged", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTableAdd);
        if (jTableAdd.getColumnModel().getColumnCount() > 0) {
            jTableAdd.getColumnModel().getColumn(0).setResizable(false);
            jTableAdd.getColumnModel().getColumn(1).setResizable(false);
            jTableAdd.getColumnModel().getColumn(2).setResizable(false);
            jTableAdd.getColumnModel().getColumn(3).setResizable(false);
            jTableAdd.getColumnModel().getColumn(4).setResizable(false);
            jTableAdd.getColumnModel().getColumn(5).setResizable(false);
            jTableAdd.getColumnModel().getColumn(6).setResizable(false);
            jTableAdd.getColumnModel().getColumn(7).setResizable(false);
            jTableAdd.getColumnModel().getColumn(8).setResizable(false);
        }

        jPAddA.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 107, 970, 350));

        jLAddHead.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLAddHead.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPAddA.add(jLAddHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 60));

        jTFundsArr.addTab("Add Fund Arrangement", jPAddA);

        jPDelete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPDeleteFocusGained(evt);
            }
        });
        jPDelete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDelDiv.setText("Division");
        jPDelete.add(jLDelDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, -1, -1));

        jBDelete.setVisible(false);
        jBDelete.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBDelete.setText("Delete");
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });
        jPDelete.add(jBDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 280, -1, -1));

        jCBDeleteDiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "BMW", "Hyundai", "JLR" }));
        jPDelete.add(jCBDeleteDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 90, 348, -1));

        jBDelDisplay.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBDelDisplay.setText("Display");
        jBDelDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDelDisplayActionPerformed(evt);
            }
        });
        jPDelete.add(jBDelDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 180, -1, -1));

        jLDeleteNote.setVisible(false);
        jLDeleteNote.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLDeleteNote.setText("Please select the fund request you wish to be deleted from the records");
        jPDelete.add(jLDeleteNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 250, -1, -1));

        jTableDel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Requested", "Arranged", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableDel);
        if (jTableDel.getColumnModel().getColumnCount() > 0) {
            jTableDel.getColumnModel().getColumn(0).setResizable(false);
            jTableDel.getColumnModel().getColumn(1).setResizable(false);
            jTableDel.getColumnModel().getColumn(2).setResizable(false);
            jTableDel.getColumnModel().getColumn(4).setResizable(false);
            jTableDel.getColumnModel().getColumn(6).setResizable(false);
            jTableDel.getColumnModel().getColumn(7).setResizable(false);
            jTableDel.getColumnModel().getColumn(8).setResizable(false);
        }

        jPDelete.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 107, 970, 350));

        jLDelHead.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLDelHead.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPDelete.add(jLDelHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 60));

        jTFundsArr.addTab("Delete", jPDelete);

        jPSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPSearchFocusGained(evt);
            }
        });
        jPSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTSearchNonDate.setVisible(false);
        jTSearchNonDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTSearchNonDateActionPerformed(evt);
            }
        });
        jTSearchNonDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTSearchNonDateKeyReleased(evt);
            }
        });
        jPSearch.add(jTSearchNonDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 350, 522, -1));

        jLSearchDiv.setText("Division");
        jPSearch.add(jLSearchDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, -1, -1));

        jCBSearchDiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "BMW", "Hyundai", "JLR" }));
        jPSearch.add(jCBSearchDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 40, 329, -1));

        jBSearchDisplay.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBSearchDisplay.setText("Next");
        jBSearchDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSearchDisplayActionPerformed(evt);
            }
        });
        jPSearch.add(jBSearchDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 230, -1, -1));

        jTableSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Requested", "Arranged", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTableSearch);
        if (jTableSearch.getColumnModel().getColumnCount() > 0) {
            jTableSearch.getColumnModel().getColumn(0).setResizable(false);
            jTableSearch.getColumnModel().getColumn(1).setResizable(false);
            jTableSearch.getColumnModel().getColumn(2).setResizable(false);
            jTableSearch.getColumnModel().getColumn(3).setResizable(false);
            jTableSearch.getColumnModel().getColumn(4).setResizable(false);
            jTableSearch.getColumnModel().getColumn(5).setResizable(false);
            jTableSearch.getColumnModel().getColumn(6).setResizable(false);
            jTableSearch.getColumnModel().getColumn(7).setResizable(false);
            jTableSearch.getColumnModel().getColumn(8).setResizable(false);
        }

        jPSearch.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 107, 970, 350));

        jLSearchHead.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLSearchHead.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPSearch.add(jLSearchHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 60));

        jTFundsArr.addTab("Search", jPSearch);

        getContentPane().add(jTFundsArr, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 148, 1570, 515));
        getContentPane().add(jSeparatorArrangement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1600, 10));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1337, 24, -1, -1));

        jLogoArrangement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLogoArrangement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs/ia/download.png"))); // NOI18N
        jLogoArrangement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoArrangementMouseClicked(evt);
            }
        });
        getContentPane().add(jLogoArrangement, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 271, 103));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBAddDivisionAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAddDivisionAActionPerformed
        
    }//GEN-LAST:event_jCBAddDivisionAActionPerformed

    private void jBAddDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddDisplayActionPerformed
        DefaultTableModel dA = (DefaultTableModel) jTableAdd.getModel();
        dA.setRowCount(0);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Statement state = null;
        ResultSet res = null;
        
        while((jCBAddDivisionA.getSelectedItem().toString()).equalsIgnoreCase("choose"))
        {
            jLAddHead.setText(null);
            JOptionPane.showMessageDialog(null, ("You must choose a division from the options given in the drop-down"));
            break;
        }
        int temp = 0;
        while(!(jCBAddDivisionA.getSelectedItem().toString()).equalsIgnoreCase("choose"))
        {
            jLAddHead.setText(jCBAddDivisionA.getSelectedItem().toString());
            try
            {
                con = DriverManager.getConnection(url, user, password); 
                st = con.createStatement();
                String query = "SELECT divisionCode FROM divisions WHERE divisionName=" + "'" + jCBAddDivisionA.getSelectedItem().toString() + "'";
                rs = st.executeQuery(query); 
                if(rs.next())
                {
                    temp = temp + rs.getInt(1);
                    try
                    {
                        conn = DriverManager.getConnection(url, user, password); 
                        state = conn.createStatement();
                        String query2 = "SELECT fundsdetails.fundCode, fundsdetails.dateRequested, fundsdetails.dateArranged, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, "
                                + "fundsdetails.USDchange, fundsdetails.EURchange, fundsdetails.SARchange FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON "
                                + "fundsdetails.bankCode=t1.bankCode WHERE complete=0 AND divcode=" + temp;
                        res = state.executeQuery(query2);
                        while(res.next())
                        {
                            dA.addRow(new Object[]{res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getDouble(8), 
                            res.getDouble(9)}); 
                        }
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    finally 
                    {
                        try
                        {
                            if (res != null) 
                            {
                                res.close();
                            }

                            if (state != null) 
                            {
                                state.close();
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
                }
                catch(Exception exc)
                {
                    JOptionPane.showMessageDialog(null, exc); 
                }
            }
            jLAddDateA.setVisible(true);
            jDAddDateA.setVisible(true);
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            Date date = new Date();
            String now = formatter.format(date);
            try {
                Date nowD = formatter.parse(now);
                jDAddDateA.setDate(nowD);
            } catch (ParseException ex) {
                Logger.getLogger(ManageFundsRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLAddAmountA.setVisible(true);
            jTAddAmountA.setVisible(true);
            jTAddAmountA.setText(null);
            jBAddArrangement.setVisible(true);
            jLAddANote.setVisible(true);
            break;
        }
    }//GEN-LAST:event_jBAddDisplayActionPerformed

    private void jBAddArrangementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddArrangementActionPerformed
        DefaultTableModel dA = (DefaultTableModel) jTableAdd.getModel();
        String temp = "";
        double newAmount = -1;
        double validAmount = -1;
        int amountIndex = -1;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Connection conne = null;
        Connection connecticut = null;
        Connection conet = null;
        Statement stat = null;
        ResultSet resuts = null;
        double insertAmount = 0;
        double addNewAmount = Double.parseDouble(jTAddAmountA.getText()); 
        Date inputDate = jDAddDateA.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        int codeRowIndex = jTableAdd.getSelectedRow();
        int fundCode = (int) dA.getValueAt(codeRowIndex, 0);
        
        try {            
            String actualDate = formatter.format(inputDate);
            con = DriverManager.getConnection(url, user, password); 
            st = con.createStatement();
            String query2 = "SELECT USDchange, EURchange, SARchange FROM fundsDetails WHERE fundcode=" + fundCode;
            rs = st.executeQuery(query2); 
            if(rs.next()) {
                if(rs.getDouble(1) != 0) {
                    newAmount = rs.getDouble(1) - addNewAmount; //amount pending after the valid payment would be made
                    temp = temp + "USD";
                    amountIndex = 6; //this represents the index on the 
                }
                else if(rs.getDouble(2) != 0) {
                    newAmount = rs.getDouble(2) - addNewAmount;
                    temp = temp + "EUR";
                    amountIndex = 7;
                }
                else {
                    newAmount = rs.getDouble(3) - addNewAmount;
                    temp = temp + "SAR";
                    amountIndex = 8;
                }
            }
            conet = DriverManager.getConnection(url, user, password);
            stat = conet.createStatement();
            String query21 = "SELECT " + temp + "p FROM costsmade WHERE fundcodep=" + fundCode;
            resuts = stat.executeQuery(query21);
            if(resuts.next()) {
                insertAmount = addNewAmount + resuts.getDouble(1); //The total payments made for this particular fund request
            }
            while(newAmount >= 0) {
                validAmount = newAmount; //making sure the amound entered is valid
                break;
            }
            if(validAmount == 0 && !actualDate.equals(null)) {
                try {
                    Class.forName("java.sql.Driver");
                    String query6 = "UPDATE fundsdetails SET dateArranged=?, " + temp + "change=?, complete=? WHERE fundCode=" + fundCode;
                    conne = DriverManager.getConnection(url, user, password);
                    PreparedStatement prst = conne.prepareStatement(query6);
                    prst.setString(1, ((JTextField)jDAddDateA.getDateEditor().getUiComponent()).getText());
                    prst.setDouble(2, 0);
                    prst.setBoolean(3, true);
                    prst.execute();
                    String query9 = "UPDATE costsMade SET dateArrangedP=?, " + temp + "P=?, completeP=? WHERE fundCodeP=" + fundCode;
                    Connection connec = DriverManager.getConnection(url, user, password);
                    PreparedStatement prste = connec.prepareStatement(query9);
                    prste.setString(1, ((JTextField)jDAddDateA.getDateEditor().getUiComponent()).getText());
                    prste.setDouble(2, insertAmount);
                    prste.setBoolean(3, true);
                    prste.execute();
                    JOptionPane.showMessageDialog(null, "Successfully saved");
                    dA.removeRow(codeRowIndex);
                    jLAddDateA.setVisible(false);
                    jDAddDateA.setVisible(false);
                    jDAddDateA.setDate(null);
                    jLAddAmountA.setVisible(false);
                    jTAddAmountA.setVisible(false);
                    jTAddAmountA.setText(null);
                    jBAddArrangement.setVisible(false);
                    jLAddANote.setVisible(false);
                    jCBAddDivisionA.setSelectedItem("Choose"); }
                catch(Exception e) {
                    JOptionPane.showMessageDialog(null, e); }}
            else if(validAmount > 0 && !actualDate.equals(null)) {
                try {
                    Class.forName("java.sql.Driver");
                    String query3 = "UPDATE fundsdetails SET dateArranged=?, " + temp + "change=? " + " WHERE fundCode=" + fundCode;
                    conn = DriverManager.getConnection(url, user, password);
                    PreparedStatement pst = conn.prepareStatement(query3);
                    pst.setString(1, ((JTextField)jDAddDateA.getDateEditor().getUiComponent()).getText());
                    pst.setDouble(2, validAmount);
                    pst.execute();
                    String query10 = "UPDATE costsMade SET dateArrangedP=?, " + temp + "P=? " + "WHERE fundCodeP=" + fundCode;
                    connecticut = DriverManager.getConnection(url, user, password);
                    PreparedStatement prst = connecticut.prepareStatement(query10);
                    prst.setString(1, ((JTextField)jDAddDateA.getDateEditor().getUiComponent()).getText());
                    prst.setDouble(2, insertAmount);
                    prst.execute();
                    JOptionPane.showMessageDialog(null, "Successfully saved");
                    dA.setValueAt(validAmount, codeRowIndex, amountIndex);
                    dA.setValueAt(((JTextField)jDAddDateA.getDateEditor().getUiComponent()).getText(), codeRowIndex, 2);
                    jLAddDateA.setVisible(false);
                    jDAddDateA.setVisible(false);
                    jDAddDateA.setDate(null);
                    jLAddAmountA.setVisible(false);
                    jTAddAmountA.setVisible(false);
                    jTAddAmountA.setText(null);
                    jBAddArrangement.setVisible(false);
                    jLAddANote.setVisible(false);
                    jCBAddDivisionA.setSelectedItem("Choose"); }
                catch(Exception e) {
                    JOptionPane.showMessageDialog(null, e); }}
            else if(validAmount == -1) {
                JOptionPane.showMessageDialog(null, "Please insert a valid amount");
                jTAddAmountA.setText(null);}
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
            }
            catch(Exception exc)
            {
                JOptionPane.showMessageDialog(null, exc); 
            }
        }
    }//GEN-LAST:event_jBAddArrangementActionPerformed

    private void jTAddAmountAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAddAmountAKeyTyped
        char vChar = evt.getKeyChar();
        if(!(Character.isDigit(vChar))
            || (vChar == KeyEvent.VK_BACK_SPACE)
            || (vChar == KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTAddAmountAKeyTyped

    private void jBDelDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDelDisplayActionPerformed
        DefaultTableModel dD = (DefaultTableModel) jTableDel.getModel();
        dD.setRowCount(0);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Statement state = null;
        ResultSet res = null;
        while((jCBDeleteDiv.getSelectedItem().toString()).equalsIgnoreCase("choose"))
        {
            jLDelHead.setText(null);
            JOptionPane.showMessageDialog(null, ("You must choose a division from the options given in the drop-down"));
            break;
        }
        int temp = 0;
        while(!(jCBDeleteDiv.getSelectedItem().toString()).equalsIgnoreCase("choose"))
        {
            jLDelHead.setText(jCBDeleteDiv.getSelectedItem().toString());
            try 
            {
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                String query = "SELECT divisionCode FROM divisions WHERE divisionName=" + "'" + jCBDeleteDiv.getSelectedItem().toString() + "'";
                rs = st.executeQuery(query);
                if (rs.next()) 
                {
                    temp = temp + rs.getInt(1);
                    try 
                    {
                        conn = DriverManager.getConnection(url, user, password);
                        state = conn.createStatement();
                        String query2 = "SELECT fundsdetails.fundCode, fundsdetails.dateRequested, fundsdetails.dateArranged, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, "
                                + "fundsdetails.USDchange, fundsdetails.EURchange, fundsdetails.SARchange FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON "
                                + "fundsdetails.bankCode=t1.bankCode WHERE complete=0 AND divcode=" + temp;
                        res = state.executeQuery(query2);
                        while(res.next())
                        {
                            dD.addRow(new Object[]{res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getDouble(8), 
                            res.getDouble(9)}); 
                        }
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    } 
                    finally 
                    {
                        try 
                        {
                            if (res != null) 
                            {
                                res.close();
                            }

                            if (state != null) 
                            {
                                state.close();
                            }

                            if (conn != null) 
                            {
                                conn.close();
                            }
                        } 
                        catch (Exception exc) 
                        {
                            JOptionPane.showMessageDialog(null, exc);
                        }
                    }
                }
            } 
            catch (Exception e) 
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
                } 
                catch (Exception exc) 
                {
                    JOptionPane.showMessageDialog(null, exc);
                }
            }
            jLDeleteNote.setVisible(true);
            jBDelete.setVisible(true);
            break;
        }
    }//GEN-LAST:event_jBDelDisplayActionPerformed

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed
        DefaultTableModel dD = (DefaultTableModel) jTableDel.getModel();
        int codeRowIndex = jTableDel.getSelectedRow();
        int fundCode = (int) dD.getValueAt(codeRowIndex, 0);
        int delOp = JOptionPane.showConfirmDialog(null, "Do you really want to delete this?", "Delete", JOptionPane.YES_NO_OPTION);
        if(delOp == 0)
        {
            dD.removeRow(codeRowIndex); 
            try
            {
                Class.forName("java.sql.Driver");
                String query3 = "DELETE FROM fundsdetails WHERE fundCode=" + fundCode;
                Connection conne = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = conne.prepareStatement(query3);
                pst.execute();
                
                String query4 = "DELETE FROM costsMade WHERE fundCodeP=" + fundCode;
                Connection connec = DriverManager.getConnection(url, user, password);
                PreparedStatement prst = connec.prepareStatement(query4);
                prst.execute();
                
                JOptionPane.showMessageDialog(null, "Successfully deleted");
                jLDeleteNote.setVisible(false);
                jBDelete.setVisible(false);
                jCBDeleteDiv.setSelectedItem("Choose");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jBDeleteActionPerformed

    private void jTSearchNonDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTSearchNonDateActionPerformed

    }//GEN-LAST:event_jTSearchNonDateActionPerformed
    //search method using a key release event
    private void jTSearchNonDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTSearchNonDateKeyReleased
        DefaultTableModel dS = (DefaultTableModel) jTableSearch.getModel(); //create the default table model
        String search = jTSearchNonDate.getText().toUpperCase();
        TableRowSorter<DefaultTableModel> tS = new TableRowSorter<DefaultTableModel> (dS);
        jTableSearch.setRowSorter(tS);
        tS.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTSearchNonDateKeyReleased

    private void jBSearchDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSearchDisplayActionPerformed
        DefaultTableModel dS = (DefaultTableModel) jTableSearch.getModel();
        dS.setRowCount(0);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Statement state = null;
        ResultSet res = null;
        int temp = 0;
        while((jCBSearchDiv.getSelectedItem().toString().toString()).equalsIgnoreCase("choose"))
        {
            JOptionPane.showMessageDialog(null, ("Incomplete form, please complete the empty boxes"));
            jLSearchHead.setText(null);
            break;
        }
        while(!(jCBSearchDiv.getSelectedItem().toString().toString()).equalsIgnoreCase("choose"))
        {
            jLSearchHead.setText(jCBSearchDiv.getSelectedItem().toString());
            jTSearchNonDate.setVisible(true);
            try 
            {
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                String query = "SELECT divisionCode FROM divisions WHERE divisionName=" + "'" + jCBSearchDiv.getSelectedItem().toString() + "'";
                rs = st.executeQuery(query);
                if (rs.next()) 
                {
                    temp = temp + rs.getInt(1);
                    try 
                    {
                        conn = DriverManager.getConnection(url, user, password);
                        state = conn.createStatement();
                        String query2 = "SELECT fundsdetails.fundCode, fundsdetails.dateRequested, fundsdetails.dateArranged, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, "
                                + "fundsdetails.USDchange, fundsdetails.EURchange, fundsdetails.SARchange FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON "
                                + "fundsdetails.bankCode=t1.bankCode WHERE complete=0 AND divcode=" + temp;
                        res = state.executeQuery(query2);
                        while(res.next())
                        {
                            dS.addRow(new Object[]{res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6), res.getDouble(7), res.getDouble(8), 
                            res.getDouble(9)}); 
                        }
                    } 
                    catch (Exception ex) 
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    } 
                    finally 
                    {
                        try 
                        {
                            if (res != null) 
                            {
                                res.close();
                            }

                            if (state != null) 
                            {
                                state.close();
                            }

                            if (conn != null) 
                            {
                                conn.close();
                            }
                        } 
                        catch (Exception exc) 
                        {
                            JOptionPane.showMessageDialog(null, exc);
                        }
                    }
                }
            } 
            catch (Exception e) 
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
                } 
                catch (Exception exc) 
                {
                    JOptionPane.showMessageDialog(null, exc);
                }
            }
            break;
        }
    }//GEN-LAST:event_jBSearchDisplayActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jPAddAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPAddAFocusLost
        // TODO add your handling code
    }//GEN-LAST:event_jPAddAFocusLost

    private void jPAddAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPAddAFocusGained

    }//GEN-LAST:event_jPAddAFocusGained

    private void jPDeleteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPDeleteFocusGained

    }//GEN-LAST:event_jPDeleteFocusGained

    private void jPSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPSearchFocusGained

    }//GEN-LAST:event_jPSearchFocusGained

    private void jLogoArrangementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoArrangementMouseClicked

    }//GEN-LAST:event_jLogoArrangementMouseClicked

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
            java.util.logging.Logger.getLogger(ManageFundsRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageFundsRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageFundsRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageFundsRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageFundsRequest().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ManageFundsRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jBAddArrangement;
    private javax.swing.JButton jBAddDisplay;
    private javax.swing.JButton jBDelDisplay;
    public static javax.swing.JButton jBDelete;
    private javax.swing.JButton jBSearchDisplay;
    public static javax.swing.JComboBox<String> jCBAddDivisionA;
    private javax.swing.JComboBox<String> jCBDeleteDiv;
    private javax.swing.JComboBox<String> jCBSearchDiv;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDAddDateA;
    private javax.swing.JLabel jLAddANote;
    public static javax.swing.JLabel jLAddAmountA;
    private javax.swing.JLabel jLAddDateA;
    private javax.swing.JLabel jLAddDiv;
    private javax.swing.JLabel jLAddHead;
    private javax.swing.JLabel jLArrangementTitle;
    private javax.swing.JLabel jLDelDiv;
    private javax.swing.JLabel jLDelHead;
    private javax.swing.JLabel jLDeleteNote;
    private javax.swing.JLabel jLErrorMessage;
    private javax.swing.JLabel jLSearchDiv;
    private javax.swing.JLabel jLSearchHead;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLogoArrangement;
    private javax.swing.JPanel jPAddA;
    private javax.swing.JPanel jPDelete;
    private javax.swing.JPanel jPSearch;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparatorArrangement;
    public static javax.swing.JTextField jTAddAmountA;
    private javax.swing.JTabbedPane jTFundsArr;
    private javax.swing.JTextField jTSearchNonDate;
    private javax.swing.JTable jTableAdd;
    private javax.swing.JTable jTableDel;
    private javax.swing.JTable jTableSearch;
    // End of variables declaration//GEN-END:variables
}