/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */
public class MainFrame extends JFrame {

    public static int columnNumber = 7;

    public static JPanel nav;

    public static JTable MainTable;

    static int idxSelectColumn = 0;

    public static JPanel createNav() {
        //NAVIGATION
        nav = new JPanel();
        nav.setBackground(new Color(202, 221, 250));
        //bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        nav.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        nav.setPreferredSize(new Dimension(1000, 80));
        //button
        MyButton AddButton = new MyButton();
        AddButton.myDecoration("Add Student", Main.pathJar+"/images/add.png");
        MyButton RemoveButton = new MyButton();
        RemoveButton.myDecoration("Remove Student", Main.pathJar+"/images/remove.png");
        MyButton ImportButton = new MyButton();
        ImportButton.myDecoration("Import File", Main.pathJar+"/images/import.png");
        MyButton ExportButton = new MyButton();
        ExportButton.myDecoration("Export File", Main.pathJar+"/images/export.png");
        MyButton SaveButton = new MyButton();
        SaveButton.myDecoration("Save File", Main.pathJar+"/images/save.png");

        nav.add(AddButton);
        nav.add(RemoveButton);
        nav.add(ImportButton);
        nav.add(ExportButton);
        nav.add(SaveButton);

        Dimension buttonSize = new Dimension(100, 70);
        AddButton.setPreferredSize(buttonSize);
        RemoveButton.setPreferredSize(buttonSize);
        ImportButton.setPreferredSize(buttonSize);
        ExportButton.setPreferredSize(buttonSize);
        SaveButton.setPreferredSize(buttonSize);

        new AddStudent(AddButton, MainTable);
        new RemoveStudent(RemoveButton, MainTable);
        new ImportFile(ImportButton, MainTable);
        new ExportFile(ExportButton, MainTable);
        new Save(SaveButton, MainTable);
        return nav;
    }

    public static JTable createTable() throws FileNotFoundException, IOException {
        String[] columnNames = {"", "MSSV", "First Name", "Last Name", "Grade", "Image", "Address", "Notes"};
        Object[][] data = BinaryFile.readTable(Ulti.defaultDataSavingPath);
        Object[][] dataTable = Ulti.addFirstBooleanColumnTo2DArray(data);
        DefaultTableModel myModel = new DefaultTableModel(dataTable, columnNames);

        JTable table = new JTable(myModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == idxSelectColumn) {
                    return Boolean.class;
                }
                return String.class;
            }

            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == idxSelectColumn) {
                    return new DefaultTableCellRenderer() {
                        private final JCheckBox checkbox = new JCheckBox();

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            checkbox.setSelected((Boolean) value);
                            checkbox.setHorizontalAlignment(JLabel.CENTER);
                            return checkbox;
                        }
                    };
                } else {
                    return super.getCellRenderer(row, column);
                }
            }

            @Override
            protected void createDefaultRenderers() {
                super.createDefaultRenderers();
                TableCellRenderer renderer = getDefaultRenderer(Boolean.class);
                if (renderer instanceof DefaultTableCellRenderer) {
                    ((DefaultTableCellRenderer) renderer).setHorizontalAlignment(JLabel.CENTER);
                }
            }

            @Override
            protected void createDefaultEditors() {
                super.createDefaultEditors();
                DefaultCellEditor editor = (DefaultCellEditor) getDefaultEditor(Boolean.class);
                JCheckBox checkbox = (JCheckBox) editor.getComponent();
                checkbox.setHorizontalAlignment(JLabel.CENTER);
            }
        };

        table.setAutoCreateRowSorter(true);
        //set column width
        int[] columnWidth = {40, 100, 150, 80, 80, 100, 200, 100};
        for (int i = 0; i < columnNumber; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidth[i]);
            column.setMinWidth(columnWidth[i]);
        }

        //set row height
        table.setRowHeight(28);
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 32));
        table.getTableHeader().setFont(new Font("", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(202, 221, 250));
        //set column alignment
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        return table;
    }

    public MainFrame() throws FileNotFoundException, IOException {
        //FRAME
        this.setTitle("Student Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon(Main.pathJar+"/images/school.png", "school");
        this.setIconImage(img.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        //LABEL_table
        JLabel label = new JLabel();
        label.setText("STUDENT LIST");
        label.setForeground(Color.BLACK);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("", Font.BOLD, 30));
        label.setPreferredSize(new Dimension(300, 60));
        //TABLE
        MainTable = createTable();
        JScrollPane scrollPane = new JScrollPane(MainTable);
        scrollPane.getViewport().setBackground(null);
        JPanel panelTable = new JPanel();
        panelTable.setBounds(0, 0, 800, 400);
        panelTable.setLayout(new BorderLayout());
        panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));
        //panelTable.add(Box.createRigidArea(new Dimension(0,5)));
        panelTable.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(800, 450));

        //NAVIGATION
        nav = createNav();

        //frameAdd
        this.getContentPane().add(nav, BorderLayout.SOUTH);
        this.getContentPane().add(label, BorderLayout.NORTH);
        this.add(panelTable);
        this.setVisible(true);

    }

}
