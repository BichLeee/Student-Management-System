/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class RemoveStudent {
    
    JTable MainTable;

    void removeStudent() {
        int rowCount = MainTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel) MainTable.getModel();
        // Iterate through the rows and columns of the table
        for (int row = 0; row < rowCount; row++) {
            if(model.getValueAt(row, 0)==Boolean.TRUE){//if true
                model.removeRow(row);
                rowCount--;
                row--;
            }
        }
        MainTable.setVisible(true);
    }

    public RemoveStudent(MyButton removeButton,JTable table) {
        MainTable = table;
        removeButton.addActionListener((ActionEvent e) -> {
            int option = JOptionPane.showConfirmDialog(null, "Do you want to remove all selected Student?", "Remove Student", JOptionPane.OK_CANCEL_OPTION);
            //ok
            if (option == 0) {
                removeStudent();
            } //cancel
            else {
            }
        });
    }
}
