/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class MyTableModel extends AbstractTableModel {

    String[] columnNames = {"", "MSSV", "First Name", "Last Name", "Grade", "Image", "Address", "Notes"};

    Object[][] data = null;

    public MyTableModel(String dataPath) throws IOException {
        try {
            data = BinaryFile.readTable(dataPath);
            data = Ulti.addFirstBooleanColumnTo2DArray(data);

        } catch (EOFException | NullPointerException ex) {
        }
    }

    public void removeRow(int row) {
        if (data == null || data.length == 0) {
            return;
        }
        int rowData = data.length;
        Object[][] newData = null;
        try {
            newData = new Object[data.length - 1][data[0].length];
            System.arraycopy(data, 0, newData, 0, row);
            System.arraycopy(data, row + 1, newData, row, rowData - row - 1);
        } catch (IndexOutOfBoundsException ex) {

        }

        data = null;
        data = newData;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else {
            return String.class;
        }
        //return getValueAt(0, columnIndex).getClass();
    }

}
