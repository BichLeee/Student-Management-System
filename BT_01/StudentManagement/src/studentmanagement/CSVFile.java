/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HP
 */
public class CSVFile {

    public static Object[][] readTable(String path) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String[]> data = new ArrayList<String[]>();

        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            data.add(row);
        }

        Object[][] dataObj = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataObj[i] = data.get(i);
        }

        return dataObj;
    }

    public static void writeTable(String path, Object[][] data) throws IOException {
        // Loop through each row and write to CSV
        try (FileWriter writer = new FileWriter(path)) {
            // Loop through each row and write to CSV
            for (Object[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == null) {
                        continue;
                    }
                    writer.write(row[i].toString());
                    if (i != row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            // Close the FileWriter
        } catch (Exception ex) {
        }
    }

    public CSVFile() {

    }

    public static File getFileFromBrowser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV File", "csv");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        return null;
    }

    public static File getFolderFromBrowser() {
        JFileChooser folderSelector = new JFileChooser();
        folderSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = folderSelector.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderSelector.getSelectedFile();
            return selectedFolder;
        }
        return null;
    }
}
