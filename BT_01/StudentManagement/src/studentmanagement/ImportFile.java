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
public class ImportFile {

    static JFrame IFFrame;

    JTable MainTable;

    void createAndShowGUI() {
        IFFrame = new JFrame();
        IFFrame.setTitle("Import CSV File");
        IFFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        IFFrame.setResizable(false);
        IFFrame.setSize(500, 200);
        IFFrame.setLocationRelativeTo(null);

        IFFrame.setLayout(new FlowLayout());
        IFFrame.setVisible(true);

    }

    void addDataToMainTable(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) MainTable.getModel();

        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    public ImportFile(MyButton importButton, JTable table) {
        MainTable = table;
        importButton.addActionListener((ActionEvent e) -> {
            createAndShowGUI();

            //Label
            JLabel IFLabel = new JLabel();
            IFLabel.setText("Enter your csv file path");
            IFLabel.setForeground(Color.BLACK);
            IFLabel.setVerticalTextPosition(JLabel.CENTER);
            IFLabel.setHorizontalAlignment(JLabel.CENTER);
            IFLabel.setFont(new Font("", Font.BOLD, 15));
            IFLabel.setPreferredSize(new Dimension(300, 60));
            //text field
            JTextField pathField = new JTextField();
            pathField.setPreferredSize(new Dimension(300, 30));
            //selectbutton
            MyButton selectBtn = new MyButton();
            selectBtn.setPreferredSize(new Dimension(30, 30));
            selectBtn.setImgIcon(Main.pathJar+"/images/folder.png", new Dimension(25, 25));
            selectBtn.addActionListener((event) -> {
                try {
                    File selected = CSVFile.getFileFromBrowser();
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
                try {
                    String path = pathField.getText();
                    Object[][] data = CSVFile.readTable(path);
                    Object[][] tableData = Ulti.addFirstBooleanColumnTo2DArray(data);
                    addDataToMainTable(tableData);
                    IFFrame.dispose();

                } catch (IOException ex) {
                }

            });
            bottomP.add(okButton);

            IFFrame.add(IFLabel);
            IFFrame.add(pathField);
            IFFrame.add(selectBtn);
            IFFrame.add(bottomP);

        });
    }
}
