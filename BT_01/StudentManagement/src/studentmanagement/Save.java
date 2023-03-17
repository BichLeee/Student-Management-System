/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class Save {

    public Save(MyButton saveButton, JTable table) {
        saveButton.addActionListener((ActionEvent e) -> {
            int option = JOptionPane.showConfirmDialog(null, "Do you want to save this table?", "Save Data", JOptionPane.OK_CANCEL_OPTION);
            //ok
            try {
                if (option == 0) {
                    BinaryFile.saveTable(Ulti.defaultDataSavingPath, table);//save file
                } //cancel
                else {
                }
            } catch (IOException err) {
                System.out.println(err);
            }

        });
    }

}
