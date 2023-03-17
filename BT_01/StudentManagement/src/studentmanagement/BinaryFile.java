/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;


import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class BinaryFile {

    public static void saveTable(String path, JTable table) throws FileNotFoundException, IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            Object[][] data = new Object[table.getRowCount()][table.getColumnCount()];
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 1; j < table.getColumnCount(); j++) {
                    data[i][j-1] = table.getValueAt(i, j);
                }
            }
            oos.writeObject(data);
            System.out.println("Table data written to data.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object[][] readTable(String path) throws FileNotFoundException, IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object[][] data = (Object[][]) ois.readObject();
            //DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            return data;
        } catch (IOException | ClassNotFoundException e) {
        }
        return null;
    }

    public BinaryFile() {

    }
}
