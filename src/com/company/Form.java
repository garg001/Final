/*
 * Created by JFormDesigner on Sat Aug 08 09:06:36 PDT 2020
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.*;

/**
 * @author Gopesh Garg
 */
public class Form extends JFrame {
    static String num2;

    public Form() {
        initComponents();
        comboBox1.addItem(new ComboItem("Savings-Regular", "Savings-Regular"));
        comboBox1.addItem(new ComboItem("Savings-Deluxe", "Savings-Deluxe"));
    }

    Connection con1;
    PreparedStatement insert;


    public void updatetable() throws ClassNotFoundException, SQLException {

        int c;
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/savings","root","");

        insert = con1.prepareStatement("Select * from savingstable");

        ResultSet rs = insert.executeQuery();



        ResultSetMetaData Res = rs.getMetaData();
        c = Res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table1.getModel();
        df.setRowCount(0);

        while(rs.next()) {
            Vector v2 = new Vector();

            for(int a =1;a<=c;a++){

                v2.add(rs.getString("custno"));
                v2.add(rs.getString("custname"));
                v2.add(rs.getDouble("cdep"));
                v2.add(rs.getInt("nyears"));
                v2.add(rs.getString("savtype"));

            }
            df.addRow(v2);
        } }


    private void Add(MouseEvent e) throws ClassNotFoundException, SQLException {
        String custno,custname,cdep,nyears,savtype;



        custno = textField1.getText();
        custname = textField2.getText();
        cdep=textField3.getText();
        nyears = textField4.getText();
        Object item = comboBox1.getSelectedItem();
        savtype= ((ComboItem)item).getValue();

        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/savings","root","");


        if(e.getSource()==button1) {


            insert = con1.prepareStatement("Select * from savingstable where custno = ?");

            insert.setString(1, custno);

            ResultSet rs = insert.executeQuery();

            if(rs.isBeforeFirst()){

                JOptionPane.showMessageDialog(null,"The custno you are trying to enter already exists ");

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                comboBox1.setSelectedItem("");
                textField1.requestFocus();

                return;
            }


            insert = con1.prepareStatement("insert into savingstable values(?,?,?,?,?)");

            insert.setString(1, custno);
            insert.setString(2, custname);
            insert.setDouble(3, Double.valueOf(cdep));
            insert.setInt(4,Integer.valueOf(nyears) );
            insert.setString(5, savtype);

            insert.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record added");

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            comboBox1.setSelectedItem("");
            textField1.requestFocus();
            updatetable();
        }
        if(e.getSource()==table1) {
        } }


    private void Edit(MouseEvent e) throws ClassNotFoundException, SQLException {
        String custno,custname,cdep,nyears,savtype;

        custno = textField1.getText();
        custname = textField2.getText();
        cdep=textField3.getText();
        nyears = textField4.getText();
        Object item = comboBox1.getSelectedItem();
        savtype= ((ComboItem)item).getValue();

        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/savings","root","");


        insert = con1.prepareStatement("update savingstable set custno=?,custname=?,cdep=?,nyears=?,savtype=? where custno=?");
        insert.setString(1, custno);
        insert.setString(2, custname);
        insert.setDouble(3, Double.valueOf(cdep));
        insert.setInt(4,Integer.valueOf(nyears) );
        insert.setString(5, savtype);

        insert.executeUpdate();

        JOptionPane.showMessageDialog(null, "Record edited");

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        comboBox1.setSelectedItem("");
        textField1.requestFocus();

        updatetable();

    }

    private void Delete(MouseEvent e) throws ClassNotFoundException, SQLException {
        String custno,custname,cdep,nyears,savtype;

        custno = textField1.getText();
        custname = textField2.getText();
        cdep=textField3.getText();
        nyears = textField4.getText();
        Object item = comboBox1.getSelectedItem();
        savtype= ((ComboItem)item).getValue();

        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/savings","root","");


        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?", "Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){

            insert = con1.prepareStatement("delete from savingstable where custno =?");

            insert.setString(1, num2);

        }


        insert.execute();

        JOptionPane.showMessageDialog(null, "Record deleted");

        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        comboBox1.setSelectedItem("");
        textField1.requestFocus();
        updatetable();

    }

    private void table1MouseClicked(MouseEvent e) {
        DefaultTableModel df = (DefaultTableModel)table1.getModel();

        int index1 = table1.getSelectedRow();


        textField1.setText(df.getValueAt(index1,0).toString());

        num2 = textField1.getText();
        textField2.setText(df.getValueAt(index1,1).toString());
        textField3.setText(df.getValueAt(index1,2).toString());
        textField4.setText(df.getValueAt(index1,3).toString());
        comboBox1.setSelectedItem(df.getValueAt(index1,4).toString());

    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Gopesh Garg
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the Customer Number");
        contentPane.add(label1, "cell 0 1");
        contentPane.add(textField1, "cell 5 1 10 1");

        //---- label2 ----
        label2.setText("Enter the Customer Name");
        contentPane.add(label2, "cell 0 2");
        contentPane.add(textField2, "cell 5 2 10 1");

        //---- label3 ----
        label3.setText("Enter the Initial Deposit");
        contentPane.add(label3, "cell 0 3");
        contentPane.add(textField3, "cell 5 3 10 1");

        //---- label4 ----
        label4.setText("Enter the number of years");
        contentPane.add(label4, "cell 0 4");
        contentPane.add(textField4, "cell 5 4 10 1");

        //---- label5 ----
        label5.setText("Choose the type of savings");
        contentPane.add(label5, "cell 0 5");
        contentPane.add(comboBox1, "cell 5 5 10 1");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 7");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 7 7");

        //---- button1 ----
        button1.setText("Add");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Add(e);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        contentPane.add(button1, "cell 0 8 7 1");

        //---- button2 ----
        button2.setText("Edit");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Edit(e);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        contentPane.add(button2, "cell 0 8 7 1");

        //---- button3 ----
        button3.setText("Delete");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Delete(e);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        contentPane.add(button3, "cell 7 8");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Gopesh Garg
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void sss(){
        String[] cols = {"Number", "Name", "Deposit", "Years", "Type of Savings"};
        String[][] data = {{"d1", "d1.1","d1.2","d1.3","d1.4"},{"d2", "d2.1", "d2.2", "d2.3", "d2.4"}};
        DefaultTableModel model = new DefaultTableModel(data, cols);
        table1.setModel(model);
        //  add(table1);
    }

}

class ComboItem
{
    private String key;
    private String value;

    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}