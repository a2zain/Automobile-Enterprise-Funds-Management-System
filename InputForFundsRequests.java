package cs.ia;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class InputForFundsRequests extends javax.swing.JFrame {
    public static String url = "jdbc:mysql://localhost/funds?useSSL=false";
    public static String user = "root";
    public static String password = "";
    
    public InputForFundsRequests() {
        initComponents();
        sortTable();
    }
    
    public void sortTable()
    {
        //using the DefaultTableModel methods to sort
        DefaultTableModel dE = (DefaultTableModel) jTableEdit.getModel();
        TableRowSorter<DefaultTableModel> so = new TableRowSorter<DefaultTableModel> (dE);
        jTableEdit.setRowSorter(so);
        
        DefaultTableModel dA = (DefaultTableModel) jTableAll.getModel();
        TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel> (dA);
        jTableAll.setRowSorter(sort);
    }
    
    //this method fills up the table with data (if present)
    public void storeTable() {
        DefaultTableModel dA = (DefaultTableModel) jTableAll.getModel();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        //collecting the necessary data from the database
        try {
            con = DriverManager.getConnection(url, user, password); 
            st = con.createStatement();
            String query = "SELECT t2.divisionName, fundsdetails.dateRequested, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, fundsdetails.USDchange, fundsdetails.EURchange, fundsdetails.SARchange, fundsdetails."
                    + "purpose FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON fundsdetails.bankCode=t1.bankCode LEFT OUTER JOIN (SELECT divisionCode, divisionName FROM divisions)t2 ON "
                    + "fundsdetails.divCode=t2.divisionCode WHERE complete=0";
            rs = st.executeQuery(query); 
            while(rs.next()) {
                dA.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9)}); 
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e); 
        }
        finally {
            try {
                if (rs != null) 
                    rs.close();
                                
                if (st != null) 
                    st.close();
                
                if (con != null) 
                    con.close();
            }
            catch(Exception exc) {
                JOptionPane.showMessageDialog(null, exc); 
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLRequestsTitle = new javax.swing.JLabel();
        jSeparatorRequests = new javax.swing.JSeparator();
        jLogoRequests = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTPInputForFundsRequests = new javax.swing.JTabbedPane();
        jPAddRequest = new javax.swing.JPanel();
        jLAddDiv = new javax.swing.JLabel();
        jLAddDate = new javax.swing.JLabel();
        jLAddCurr = new javax.swing.JLabel();
        jLAddBank = new javax.swing.JLabel();
        jLAddBankNum = new javax.swing.JLabel();
        jLAddIBAN = new javax.swing.JLabel();
        jLAddAmount = new javax.swing.JLabel();
        jLAddPurpose = new javax.swing.JLabel();
        jCBAddDiv = new javax.swing.JComboBox<>();
        jCBAddCurr = new javax.swing.JComboBox<>();
        jCBAddBank = new javax.swing.JComboBox<>();
        jLBankNum6 = new javax.swing.JLabel();
        jTAddBankNum = new javax.swing.JTextField();
        jLIBANStart = new javax.swing.JLabel();
        jTAddIBAN = new javax.swing.JTextField();
        jTAddAmount = new javax.swing.JTextField();
        jTAddPurpose = new javax.swing.JTextField();
        jBAddFund = new javax.swing.JButton();
        jDAddDate = new com.toedter.calendar.JDateChooser();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableAll = new javax.swing.JTable();
        jPEdit = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableEdit = new javax.swing.JTable();
        jLEditDivision = new javax.swing.JLabel();
        jCBEditDiv = new javax.swing.JComboBox<>();
        jLEditt = new javax.swing.JLabel();
        jCBEditChoice = new javax.swing.JComboBox<>();
        jLEditNote = new javax.swing.JLabel();
        jBEditDisplay = new javax.swing.JButton();
        jDEditDate = new com.toedter.calendar.JDateChooser();
        jLEditDate = new javax.swing.JLabel();
        jLEditBank = new javax.swing.JLabel();
        jTEditIBAN = new javax.swing.JTextField();
        jBEdit = new javax.swing.JButton();
        jLEditBankNum = new javax.swing.JLabel();
        jTEditBankNum = new javax.swing.JTextField();
        jLEditIBAN = new javax.swing.JLabel();
        jCBEditBank = new javax.swing.JComboBox<>();
        jLEditHead = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLRequestsTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLRequestsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLRequestsTitle.setText("Input For Funds Requests");
        getContentPane().add(jLRequestsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 472, 117));
        getContentPane().add(jSeparatorRequests, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 134, 1600, 10));

        jLogoRequests.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLogoRequests.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs/ia/download.png"))); // NOI18N
        jLogoRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoRequestsMouseClicked(evt);
            }
        });
        getContentPane().add(jLogoRequests, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 280, 110));
        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jTPInputForFundsRequests.setBackground(new java.awt.Color(204, 204, 255));

        jPAddRequest.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPAddRequestFocusGained(evt);
            }
        });
        jPAddRequest.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLAddDiv.setText("Division*");
        jPAddRequest.add(jLAddDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, -1, -1));

        jLAddDate.setText("Date Funds Requested*");
        jPAddRequest.add(jLAddDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, -1, -1));

        jLAddCurr.setText("Currency*");
        jPAddRequest.add(jLAddCurr, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 150, -1, -1));

        jLAddBank.setText("Bank*");
        jPAddRequest.add(jLAddBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, -1, -1));

        jLAddBankNum.setText("Bank A/C. Number*");
        jPAddRequest.add(jLAddBankNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, -1, -1));

        jLAddIBAN.setText("IBAN*");
        jPAddRequest.add(jLAddIBAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, -1, -1));

        jLAddAmount.setText("Amount*");
        jPAddRequest.add(jLAddAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, -1, -1));

        jLAddPurpose.setText("Purpose");
        jPAddRequest.add(jLAddPurpose, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 450, -1, -1));

        jCBAddDiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "BMW", "JLR", "Hyundai" }));
        jPAddRequest.add(jCBAddDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, 324, -1));

        jCBAddCurr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "USD", "EUR", "SAR" }));
        jPAddRequest.add(jCBAddCurr, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 140, 324, -1));

        jCBAddBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "ALAWWAL", "ANB", "BSF", "GIB", "NCB", "RAJHI", "SABB", "SABBUT", "SAIB", "SAMBA", "SCB" }));
        jPAddRequest.add(jCBAddBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 200, 324, -1));

        jLBankNum6.setText("Last 6 digits");
        jPAddRequest.add(jLBankNum6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 270, -1, -1));

        jTAddBankNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTAddBankNumKeyTyped(evt);
            }
        });
        jPAddRequest.add(jTAddBankNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 270, 224, -1));

        jLIBANStart.setText("SA");
        jPAddRequest.add(jLIBANStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 330, -1, -1));
        jPAddRequest.add(jTAddIBAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 330, 289, -1));

        jTAddAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTAddAmountKeyTyped(evt);
            }
        });
        jPAddRequest.add(jTAddAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 390, 324, -1));
        jPAddRequest.add(jTAddPurpose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 450, 324, -1));

        jBAddFund.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBAddFund.setText("SAVE");
        jBAddFund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddFundActionPerformed(evt);
            }
        });
        jPAddRequest.add(jBAddFund, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 230, -1, -1));
        jPAddRequest.add(jDAddDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 80, 324, -1));

        jTableAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Division", "Requested", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR", "Purpose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTableAll);

        jPAddRequest.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 17, 890, 490));

        jTPInputForFundsRequests.addTab("Add", jPAddRequest);

        jPEdit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPEditFocusGained(evt);
            }
        });
        jPEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Requested", "Bank", "Bank #", "IBAN", "USD", "EUR", "SAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTableEdit);
        if (jTableEdit.getColumnModel().getColumnCount() > 0) {
            jTableEdit.getColumnModel().getColumn(0).setHeaderValue("Code");
        }

        jPEdit.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 107, 880, 400));

        jLEditDivision.setText("Division");
        jPEdit.add(jLEditDivision, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, -1, -1));

        jCBEditDiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "BMW", "Hyundai", "JLR" }));
        jPEdit.add(jCBEditDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 30, 227, -1));

        jLEditt.setText("Edit the");
        jPEdit.add(jLEditt, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, -1, -1));

        jCBEditChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Date Requested", "Bank", "Bank Number", "IBAN" }));
        jPEdit.add(jCBEditChoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 120, 227, -1));

        jLEditNote.setVisible(false);
        jLEditNote.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLEditNote.setText("Please select the fund request you wish to edit");
        jPEdit.add(jLEditNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, -1, -1));

        jBEditDisplay.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBEditDisplay.setText("Next");
        jBEditDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditDisplayActionPerformed(evt);
            }
        });
        jPEdit.add(jBEditDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 180, -1, -1));

        jDEditDate.setVisible(false);
        jPEdit.add(jDEditDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 280, 227, -1));

        jLEditDate.setVisible(false);
        jLEditDate.setText("Date Funds Arranged");
        jPEdit.add(jLEditDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 280, -1, -1));

        jLEditBank.setVisible(false);
        jLEditBank.setText("Bank");
        jPEdit.add(jLEditBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 420, -1, -1));

        jTEditIBAN.setVisible(false);
        jPEdit.add(jTEditIBAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 380, 227, -1));

        jBEdit.setVisible(false);
        jBEdit.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBEdit.setText("Edit");
        jBEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditActionPerformed(evt);
            }
        });
        jPEdit.add(jBEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 470, -1, -1));

        jLEditBankNum.setVisible(false);
        jLEditBankNum.setText("Bank Number");
        jPEdit.add(jLEditBankNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 330, -1, -1));

        jTEditBankNum.setVisible(false);
        jPEdit.add(jTEditBankNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 330, 225, -1));

        jLEditIBAN.setVisible(false);
        jLEditIBAN.setText("IBAN");
        jPEdit.add(jLEditIBAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 380, -1, -1));

        jCBEditBank.setVisible(false);
        jCBEditBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "ALAWWAL", "ANB", "BSF", "GIB", "NCB", "RAJHI", "SABB", "SABBUT", "SAIB", "SAMBA", "SCB" }));
        jPEdit.add(jCBEditBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 420, 230, -1));

        jLEditHead.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jPEdit.add(jLEditHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 110, 50));

        jTPInputForFundsRequests.addTab("Edit", jPEdit);

        getContentPane().add(jTPInputForFundsRequests, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 1580, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAddFundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddFundActionPerformed
        DefaultTableModel dA = (DefaultTableModel) jTableAll.getModel();
        int addDiv = 0;
        double addUSD = 0;
        String addBank = null;
        int addBankCode = -1;
        int addBankNum = -1;
        String addIBAN = null;
        double addEUR = 0;
        double addSAR = 0;
        String addPurpose = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Statement sta = null;
        ResultSet res = null;
        try
        {
            try
            {
                con = DriverManager.getConnection(url, user, password); 
                st = con.createStatement();
                String query = "SELECT divisionCode FROM divisions WHERE divisionName=" + "'" + jCBAddDiv.getSelectedItem().toString() + "'";
                rs = st.executeQuery(query); 
                if(rs.next())
                {
                    addDiv = rs.getInt(1);
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
                catch (Exception exc) 
                {
                    JOptionPane.showMessageDialog(null, exc);
                }
            }

            if(jCBAddCurr.getSelectedItem().toString().equalsIgnoreCase("usd"))
            {
                addSAR = 0;
                addEUR= 0; 
                addUSD = addUSD + Double.parseDouble(jTAddAmount.getText());
            }
            else if(jCBAddCurr.getSelectedItem().toString().equalsIgnoreCase("eur"))
            {
                addSAR = 0;
                addEUR= addEUR + Double.parseDouble(jTAddAmount.getText()); 
                addUSD = 0;
            }
            else 
            {
                addSAR = addSAR + Double.parseDouble(jTAddAmount.getText());
                addEUR= 0; 
                addUSD = 0;
            }

            addBank = jCBAddBank.getSelectedItem().toString();
            try
            {
                conn = DriverManager.getConnection(url, user, password); 
                sta = conn.createStatement();
                String query2 = "SELECT bankCode FROM banks WHERE bankName=" + "'" + addBank + "'";
                res = sta.executeQuery(query2); 
                if(res.next())
                {
                    addBankCode = res.getInt(1);
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
                catch (Exception exc) 
                {
                    JOptionPane.showMessageDialog(null, exc);
                }
            }

            while(((jTAddBankNum.getText()).trim()).length() == 6)
            {
                addBankNum = Integer.parseInt((jTAddBankNum.getText()).trim());
                break;
            }
            
            while(((jTAddIBAN.getText()).trim()).length() == 22)
            {
                addIBAN = jTAddIBAN.getText().toUpperCase().trim();
                break;
            }
            addPurpose = jTAddPurpose.getText();
            
            //check whether there is any missed field
            if(((jCBAddDiv.getSelectedItem().toString())).equalsIgnoreCase("choose") || (jCBAddCurr.getSelectedItem().toString()).equalsIgnoreCase("choose") || 
                    ((jCBAddBank.getSelectedItem().toString())).equalsIgnoreCase("choose") || (jTAddBankNum.getText()).equalsIgnoreCase(null) || (jTAddIBAN.getText()).equalsIgnoreCase(null) 
                    || (jTAddAmount.getText()).equalsIgnoreCase(null))
            {
                JOptionPane.showMessageDialog(null, ("The form is incomplete\nPlease complete the form"));
            }
            //check whether the IBAN meets the 22 character requirement
            else if(addIBAN == null)
            {
                JOptionPane.showMessageDialog(null, "Please re-enter the IBAN");
                jTAddIBAN.setText(null);
            }
            //check whether the bank number reaches the 6 digit requirement
            else if(addBankNum == -1)
            {
                JOptionPane.showMessageDialog(null, "Please re-enter the Bank Number");
                jTAddBankNum.setText(null);
            }
            //otherwise insert the data entered into the database
            else
            {
                Class.forName("java.sql.Driver");
                String query3 = "INSERT INTO fundsDetails(divCode, dateRequested, dateArranged, bankCode, bankNum, iban, USD, USDChange, EUR, EURChange, SAR, SARChange, purpose, complete) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                Connection conne = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = conne.prepareStatement(query3);
                pst.setInt(1, addDiv);
                pst.setString(2, ((JTextField)jDAddDate.getDateEditor().getUiComponent()).getText());
                pst.setString(3, null);
                pst.setInt(4, addBankCode);
                pst.setInt(5, addBankNum);
                pst.setString(6, addIBAN);
                pst.setDouble(7, addUSD);
                pst.setDouble(8, addUSD);
                pst.setDouble(9, addEUR);
                pst.setDouble(10, addEUR);
                pst.setDouble(11, addSAR);
                pst.setDouble(12, addSAR);
                pst.setString(13, addPurpose);
                pst.setBoolean(14, false);
                pst.execute();

                String query4 = "INSERT INTO costsMade(divCodeP, dateRequestedP, dateArrangedP, USDP, EURP, SARP, completeP) VALUES(?,?,?,?,?,?,?)";
                Connection connect = DriverManager.getConnection(url, user, password);
                PreparedStatement prst = connect.prepareStatement(query4);
                prst.setInt(1, addDiv);
                prst.setString(2, ((JTextField)jDAddDate.getDateEditor().getUiComponent()).getText());
                prst.setString(3, null);
                prst.setDouble(4, 0);
                prst.setDouble(5, 0);
                prst.setDouble(6, 0);
                prst.setBoolean(7, false);
                prst.execute();
                
                jCBAddDiv.setSelectedItem("Choose"); 
                jDAddDate.setDate(null); 
                jCBAddCurr.setSelectedItem("Choose");
                jCBAddBank.setSelectedItem("Choose");
                jTAddBankNum.setText(null);
                jTAddIBAN.setText(null);
                jTAddPurpose.setText(null);
                jTAddAmount.setText(null);
                
                dA.addRow(new Object[]{jCBAddDiv.getSelectedItem().toString(), ((JTextField)jDAddDate.getDateEditor().getUiComponent()).getText(), addBank, addBankNum, addIBAN, addUSD, 
                    addEUR, addSAR}); 
                JOptionPane.showMessageDialog(null, "Successfully saved");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jBAddFundActionPerformed

    private void jTAddBankNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAddBankNumKeyTyped
        char vChar = evt.getKeyChar();
        if(!(Character.isDigit(vChar))
            || (vChar == KeyEvent.VK_BACK_SPACE)
            || (vChar == KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTAddBankNumKeyTyped

    private void jTAddAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAddAmountKeyTyped
        char vChar = evt.getKeyChar();
        if(!(Character.isDigit(vChar))
            || (vChar == KeyEvent.VK_BACK_SPACE)
            || (vChar == KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTAddAmountKeyTyped

    private void jBEditDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditDisplayActionPerformed
        DefaultTableModel dE = (DefaultTableModel) jTableEdit.getModel();
        dE.setRowCount(0);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Statement state = null;
        ResultSet res = null;
        int temp = 0;
        while((jCBEditDiv.getSelectedItem().toString().toString()).equalsIgnoreCase("choose") || (jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("choose"))
        {
            JOptionPane.showMessageDialog(null, ("Incomplete form, please complete the empty boxes"));
            break;
        }
        while(!(jCBEditDiv.getSelectedItem().toString().toString()).equalsIgnoreCase("choose") && !(jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("choose"))
        {
            jLEditHead.setText(jCBEditDiv.getSelectedItem().toString());
            if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Date Requested"))
            {
                jLEditDate.setVisible(true);
                jDEditDate.setVisible(true);
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
                Date date = new Date();
                String now = formatter.format(date);
                try {
                    Date nowD = formatter.parse(now);
                    jDAddDate.setDate(nowD);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageFundsRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
                jLEditNote.setVisible(true);
            }
            else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Bank"))
            {
                jLEditBank.setVisible(true);
                jCBEditBank.setVisible(true);
                jLEditNote.setVisible(true);
            }
            else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("IBAN"))
            {
                jLEditIBAN.setVisible(true);
                jTEditIBAN.setVisible(true);
                jLEditNote.setVisible(true);
            }
            else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Bank Number"))
            {
                jLEditBankNum.setVisible(true);
                jTEditBankNum.setVisible(true);
                jLEditNote.setVisible(true);
            }
            try
            {
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                //search for the division code
                String query = "SELECT divisionCode FROM divisions WHERE divisionName=" + "'" + jCBEditDiv.getSelectedItem().toString() + "'";
                rs = st.executeQuery(query);
                if(rs.next())
                {
                    temp = temp + rs.getInt(1);
                    try
                    {
                        conn = DriverManager.getConnection(url, user, password);
                        state = conn.createStatement();
                        //using the division code to then search its relevant fund requests for which payments are still pending
                        String query2 = "SELECT fundsdetails.fundCode, fundsdetails.dateRequested, t1.bankName, fundsdetails.bankNum, fundsdetails.iban, fundsdetails.USDchange, "
                                + "fundsdetails.EURchange, fundsdetails.SARchange FROM fundsdetails LEFT OUTER JOIN (SELECT bankCode, bankName FROM banks)t1 ON fundsdetails.bankCode="
                                + "t1.bankCode WHERE complete=0 AND divcode=" + temp;
                        res = state.executeQuery(query2);
                        while(res.next())
                        {
                            dE.addRow(new Object[]{res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getDouble(6), res.getDouble(7), res.getDouble(8)});
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
            jBEdit.setVisible(true);
            break;
        }
    }//GEN-LAST:event_jBEditDisplayActionPerformed

    private void jBEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditActionPerformed
        DefaultTableModel dE = (DefaultTableModel) jTableEdit.getModel();
        String editDate = null;
        int editBankCode = -1;
        String editIBAN = null;
        int editBankNum = -1;
        Connection con = null;
        Statement st = null;
        Statement sta = null;
        ResultSet res = null;
        ResultSet rs = null;
        Connection conn = null;
        Connection conect = null;
        Connection connect = null;
        int codeRowIndex = jTableEdit.getSelectedRow();
        int fundCode = (int) dE.getValueAt(codeRowIndex, 0);

        if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Bank"))
        {
            if(jCBEditBank.getSelectedItem().toString().equalsIgnoreCase("Choose"))
            {
                JOptionPane.showMessageDialog(null, "Please choose a bank from the drop-down menu");
            }
            else
            {
                try
                {
                    conect = DriverManager.getConnection(url, user, password); 
                    sta = conect.createStatement();
                    String query2 = "SELECT bankCode FROM banks WHERE bankName=" + "'" + jCBEditBank.getSelectedItem().toString() + "'";
                    rs = sta.executeQuery(query2); 
                    if(rs.next())
                    {
                        editBankCode = rs.getInt(1);
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

                        if (sta != null) 
                        {
                            sta.close();
                        }

                        if (conect != null) 
                        {
                            conect.close();
                        }
                    } 
                    catch (Exception exc) 
                    {
                        JOptionPane.showMessageDialog(null, exc);
                    }
                }
                try
                {
                    conn = DriverManager.getConnection(url, user, password);
                    String query2 = "UPDATE fundsdetails SET bankCode=? WHERE fundcode=" + fundCode;
                    PreparedStatement pst = conn.prepareStatement(query2);
                    pst.setInt(1, editBankCode);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Successfully edited");
                    dE.setValueAt(jCBEditBank.getSelectedItem().toString(), codeRowIndex, 2);

                    jCBEditDiv.setSelectedItem("Choose"); 
                    jCBEditChoice.setSelectedItem("Choose");
                    jLEditDate.setVisible(false);
                    jDEditDate.setVisible(false);
                    jDEditDate.setDate(null); 
                    jLEditNote.setVisible(false);
                    jLEditBank.setVisible(false);
                    jCBEditBank.setVisible(false);
                    jCBEditBank.setSelectedItem("Choose");
                    jLEditIBAN.setVisible(false);
                    jTEditIBAN.setVisible(false);
                    jTEditIBAN.setText(null);
                    jLEditBankNum.setVisible(false);
                    jTEditBankNum.setVisible(false);
                    jTEditBankNum.setText(null);
                    jBEdit.setVisible(false);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Date Requested"))
        {
            editDate = ((JTextField)jDEditDate.getDateEditor().getUiComponent()).getText();
            try
            {
                Connection conection = DriverManager.getConnection(url, user, password);
                String query4 = "UPDATE fundsdetails SET dateRequested=? WHERE fundcode=" + fundCode;
                PreparedStatement prep = conection.prepareStatement(query4);
                prep.setString(1, editDate);
                prep.execute();
                
                Connection connectionn = DriverManager.getConnection(url, user, password);
                String query11 = "UPDATE costsMade SET dateRequestedP=? WHERE fundcodeP=" + fundCode;
                PreparedStatement preparation = connectionn.prepareStatement(query11);
                preparation.setString(1, editDate);
                preparation.execute();
                
                dE.setValueAt(editDate, codeRowIndex, 1);
                JOptionPane.showMessageDialog(null, "Successfully edited");
                
                jCBEditDiv.setSelectedItem("Choose"); 
                jCBEditChoice.setSelectedItem("Choose");
                jLEditDate.setVisible(false);
                jDEditDate.setVisible(false);
                jDEditDate.setDate(null); 
                jLEditNote.setVisible(false);
                jLEditBank.setVisible(false);
                jCBEditBank.setVisible(false);
                jCBEditBank.setSelectedItem("Choose");
                jLEditIBAN.setVisible(false);
                jTEditIBAN.setVisible(false);
                jTEditIBAN.setText(null);
                jLEditBankNum.setVisible(false);
                jTEditBankNum.setVisible(false);
                jTEditBankNum.setText(null);
                jBEdit.setVisible(false);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("IBAN"))
        {
            while(((jTEditIBAN.getText()).trim()).length()==22)
            {
                editIBAN = jTEditIBAN.getText().toUpperCase();
            }
            
            if(!editIBAN.equals(null))
            {
                try
                {
                    Connection conection = DriverManager.getConnection(url, user, password);
                    String query4 = "UPDATE fundsdetails SET iban=? WHERE fundcode=" + fundCode;
                    PreparedStatement prep = conection.prepareStatement(query4);
                    prep.setString(1, editIBAN);
                    prep.execute();

                    while(!editIBAN.equals(null))
                    {
                        dE.setValueAt(editIBAN, codeRowIndex, 4);
                        JOptionPane.showMessageDialog(null, "Successfully edited");
                        break;
                    }

                    jCBEditDiv.setSelectedItem("Choose"); 
                    jCBEditChoice.setSelectedItem("Choose");
                    jLEditDate.setVisible(false);
                    jDEditDate.setVisible(false);
                    jDEditDate.setDate(null); 
                    jLEditNote.setVisible(false);
                    jLEditBank.setVisible(false);
                    jCBEditBank.setVisible(false);
                    jCBEditBank.setSelectedItem("Choose");
                    jLEditIBAN.setVisible(false);
                    jTEditIBAN.setVisible(false);
                    jTEditIBAN.setText(null);
                    jLEditBankNum.setVisible(false);
                    jTEditBankNum.setVisible(false);
                    jTEditBankNum.setText(null);
                    jBEdit.setVisible(false);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Invalid IBAN entered");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid IBAN entered, please re-enter");
                jTEditIBAN.setText(null);
            }
        }
        else if((jCBEditChoice.getSelectedItem().toString().toString()).equalsIgnoreCase("Bank Number"))
        {
            while(((jTEditBankNum.getText()).trim()).length()==6)
            {
                editBankNum = Integer.parseInt((jTEditBankNum.getText()).trim());
                break;
            }
            
            if(editBankNum!=0)
            {
                try
                {
                    Connection conection = DriverManager.getConnection(url, user, password);
                    String query4 = "UPDATE fundsdetails SET bankNum=? WHERE fundcode=" + fundCode;
                    PreparedStatement prep = conection.prepareStatement(query4);
                    prep.setInt(1, editBankNum);
                    prep.execute();

                    while(editBankNum!=0)
                    {
                        dE.setValueAt(editBankNum, codeRowIndex, 3);
                        JOptionPane.showMessageDialog(null, "Successfully edited");
                        break;
                    }

                    jCBEditDiv.setSelectedItem("Choose"); 
                    jCBEditChoice.setSelectedItem("Choose");
                    jLEditDate.setVisible(false);
                    jDEditDate.setVisible(false);
                    jDEditDate.setDate(null); 
                    jLEditNote.setVisible(false);
                    jLEditBank.setVisible(false);
                    jCBEditBank.setVisible(false);
                    jCBEditBank.setSelectedItem("Choose");
                    jLEditIBAN.setVisible(false);
                    jTEditIBAN.setVisible(false);
                    jTEditIBAN.setText(null);
                    jLEditBankNum.setVisible(false);
                    jTEditBankNum.setVisible(false);
                    jTEditBankNum.setText(null);
                    jBEdit.setVisible(false);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Bank Number entered");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Bank Number entered, please re-enter");
                jTEditBankNum.setText(null);
            }
        }
    }//GEN-LAST:event_jBEditActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        DefaultTableModel dA = (DefaultTableModel) jTableAll.getModel();
        dA.setRowCount(0);
        storeTable();
        
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date();
        String now = formatter.format(date);
        try {
            Date nowD = formatter.parse(now);
            jDAddDate.setDate(nowD);
        } catch (ParseException ex) {
            Logger.getLogger(ManageFundsRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jPAddRequestFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPAddRequestFocusGained

    }//GEN-LAST:event_jPAddRequestFocusGained

    private void jPEditFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPEditFocusGained

    }//GEN-LAST:event_jPEditFocusGained

    private void jLogoRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoRequestsMouseClicked
        
    }//GEN-LAST:event_jLogoRequestsMouseClicked

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

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
            java.util.logging.Logger.getLogger(InputForFundsRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputForFundsRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputForFundsRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputForFundsRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputForFundsRequests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAddFund;
    public static javax.swing.JButton jBEdit;
    private javax.swing.JButton jBEditDisplay;
    private javax.swing.JComboBox<String> jCBAddBank;
    private javax.swing.JComboBox<String> jCBAddCurr;
    private javax.swing.JComboBox<String> jCBAddDiv;
    private javax.swing.JComboBox<String> jCBEditBank;
    public javax.swing.JComboBox<String> jCBEditChoice;
    private javax.swing.JComboBox<String> jCBEditDiv;
    private com.toedter.calendar.JDateChooser jDAddDate;
    private com.toedter.calendar.JDateChooser jDEditDate;
    private javax.swing.JLabel jLAddAmount;
    private javax.swing.JLabel jLAddBank;
    private javax.swing.JLabel jLAddBankNum;
    private javax.swing.JLabel jLAddCurr;
    private javax.swing.JLabel jLAddDate;
    private javax.swing.JLabel jLAddDiv;
    private javax.swing.JLabel jLAddIBAN;
    private javax.swing.JLabel jLAddPurpose;
    private javax.swing.JLabel jLBankNum6;
    private javax.swing.JLabel jLEditBank;
    private javax.swing.JLabel jLEditBankNum;
    private javax.swing.JLabel jLEditDate;
    private javax.swing.JLabel jLEditDivision;
    private javax.swing.JLabel jLEditHead;
    private javax.swing.JLabel jLEditIBAN;
    private javax.swing.JLabel jLEditNote;
    public javax.swing.JLabel jLEditt;
    private javax.swing.JLabel jLIBANStart;
    private javax.swing.JLabel jLRequestsTitle;
    private javax.swing.JLabel jLogoRequests;
    private javax.swing.JPanel jPAddRequest;
    private javax.swing.JPanel jPEdit;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparatorRequests;
    private javax.swing.JTextField jTAddAmount;
    private javax.swing.JTextField jTAddBankNum;
    private javax.swing.JTextField jTAddIBAN;
    private javax.swing.JTextField jTAddPurpose;
    private javax.swing.JTextField jTEditBankNum;
    private javax.swing.JTextField jTEditIBAN;
    private javax.swing.JTabbedPane jTPInputForFundsRequests;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAll;
    private javax.swing.JTable jTableEdit;
    // End of variables declaration//GEN-END:variables
}