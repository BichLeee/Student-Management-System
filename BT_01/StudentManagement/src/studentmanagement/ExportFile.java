/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ExportFile {

    static JFrame EPFrame;

    JTable MainTable;

    void createAndShowGUI() {
        EPFrame = new JFrame();
        EPFrame.setTitle("Export CSV File");
        EPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        EPFrame.setResizable(false);
        EPFrame.setSize(500, 300);
        EPFrame.setLocationRelativeTo(null);

        EPFrame.setLayout(new FlowLayout());
        EPFrame.setVisible(true);

    }

    Object[][] convertMainTableToObject() {
        int rowCount = MainTable.getRowCount();
        int columnCount = MainTable.getColumnCount();

        // Create an Object array to hold the table data
        Object[][] data = new Object[rowCount][columnCount - 1];

        // Iterate through the rows and columns of the table and add the data to the Object array
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount - 1; column++) {
                data[row][column] = MainTable.getValueAt(row, column + 1);
            }
        }

        return data;
    }

    public ExportFile(MyButton exportButton, JTable table) {
        MainTable = table;
        exportButton.addActionListener((ActionEvent e) -> {
            createAndShowGUI();

            //CSV path
            JLabel EXLabel = new JLabel();
            EXLabel.setText("Enter your folder");
            EXLabel.setForeground(Color.BLACK);
            EXLabel.setVerticalTextPosition(JLabel.CENTER);
            EXLabel.setHorizontalAlignment(JLabel.CENTER);
            EXLabel.setFont(new Font("", Font.BOLD, 15));
            EXLabel.setPreferredSize(new Dimension(300, 60));
            JTextField pathField = new JTextField();
            pathField.setPreferredSize(new Dimension(300, 30));

            //file name
            JLabel nameLabel = new JLabel();
            nameLabel.setText("Enter the File's name");
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setVerticalTextPosition(JLabel.CENTER);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setFont(new Font("", Font.BOLD, 15));
            nameLabel.setPreferredSize(new Dimension(300, 50));
            JTextField nameField = new JTextField();
            nameField.setPreferredSize(new Dimension(250, 30));
            //selectbutton
            MyButton selectBtn = new MyButton();
            selectBtn.setPreferredSize(new Dimension(30, 30));
            selectBtn.setImgIcon(Main.pathJar+"/images/folder.png", new Dimension(25, 25));
            selectBtn.addActionListener((event) -> {
                try {
                    File selected = CSVFile.getFolderFromBrowser();
                    pathField.setText(selected.getAbsolutePath());


                } catch (Exception er) {

                }

            });
            //ok button
            JPanel bottomP = new JPanel();
            bottomP.setPreferredSize(new Dimension(300, 70));
            JButton okButton = new JButton("  OK  ");
            okButton.setPreferredSize(new Dimension(100, 30));
            okButton.setVerticalAlignment(JButton.CENTER);
            okButton.addActionListener((event) -> {
                //try {
                    String path = pathField.getText();
                    Object[][] tableData = convertMainTableToObject();
                   //[][] data = Ulti.removeFirstBooleanColumnFrom2DArray(tableData);
                    String filename = nameField.getText();
                try {
                    CSVFile.writeTable(path+"/"+filename+".csv", tableData);
                } catch (IOException ex) {
                    Logger.getLogger(ExportFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                    EPFrame.dispose();

                
//                } catch (Exception ex) {
//                }

            });
            bottomP.add(okButton);

            EPFrame.add(EXLabel);
            EPFrame.add(pathField);
            EPFrame.add(selectBtn);
            EPFrame.add(nameLabel);
            EPFrame.add(nameField);
            EPFrame.add(bottomP);

        });
    }

}
