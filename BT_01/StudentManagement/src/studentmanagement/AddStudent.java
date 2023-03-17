/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class AddStudent {

    JTable MainTable;

    JFrame ASFrame = null;

    JPanel infoPanel = null;
    JPanel imgPanel = null;
    JLabel imgLabel = null;

    String imgPath = null;

    public JPanel createInfoFieldPanel(JLabel label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        label.setPreferredSize(new Dimension(70, 35));
        panel.add(label);
        panel.add(field);

        return panel;
    }

    public void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                BufferedImage resized = resizeImage(image, 170, 170);
                imgLabel.setIcon(null);
                imgLabel.setIcon(new ImageIcon(resized));
                //save img to images folder
                File outputfile = new File(Main.pathJar+"/data/StudentImage/" + selectedFile.getName());
                ImageIO.write(image, "png", outputfile);
                imgPath = outputfile.getAbsolutePath();
            } catch (IOException err) {
                System.out.println("Error while saving image");
            }
        }
    }

    public BufferedImage resizeImage(BufferedImage myPicture, int width, int height) {
        Image temp = myPicture.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, myPicture.getType());
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();

        return resized;
    }

    public void addDataToMainTable(Object[] data) {
        DefaultTableModel model = (DefaultTableModel) MainTable.getModel();
        model.addRow(data);
    }

    public AddStudent(MyButton addButton, JTable table) {
        MainTable = table;
        addButton.addActionListener((ActionEvent e) -> {
            ASFrame = new JFrame();
            infoPanel = new JPanel();
            imgPanel = new JPanel();
            imgLabel = new JLabel();

            ASFrame.setTitle("Add Student");
            ASFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ASFrame.setResizable(false);
            ASFrame.setSize(700, 400);
            ASFrame.setLocationRelativeTo(null);

            ASFrame.setLayout(new BorderLayout());

            infoPanel.setPreferredSize(new Dimension(400, 300));
            imgPanel.setPreferredSize(new Dimension(250, 300));

            //Label
            JLabel ASLabel = new JLabel();
            ASLabel.setText("ADD STUDENT");
            ASLabel.setForeground(Color.BLACK);
            ASLabel.setVerticalTextPosition(JLabel.CENTER);
            ASLabel.setHorizontalAlignment(JLabel.CENTER);
            ASLabel.setFont(new Font("", Font.BOLD, 20));
            ASLabel.setPreferredSize(new Dimension(300, 60));
            //Form
            //id 
            JTextField idField = new JTextField();
            idField.setPreferredSize(new Dimension(250, 30));
            JLabel idLabel = new JLabel("ID: ");
            idLabel.setLabelFor(idField);
            JPanel idPanel = createInfoFieldPanel(idLabel, idField);
            //name
            JTextField nameField = new JTextField();
            nameField.setPreferredSize(new Dimension(250, 30));
            JLabel nameLabel = new JLabel("Name: ");
            nameLabel.setLabelFor(nameField);
            JPanel namePanel = createInfoFieldPanel(nameLabel, nameField);
            //grade
            JTextField gradeField = new JTextField();
            gradeField.setPreferredSize(new Dimension(250, 30));
            JLabel gradeLabel = new JLabel("Grade: ");
            gradeLabel.setLabelFor(gradeField);
            JPanel gradePanel = createInfoFieldPanel(gradeLabel, gradeField);

            //img
            //address
            JTextField addrField = new JTextField();
            addrField.setPreferredSize(new Dimension(250, 30));
            JLabel addrLabel = new JLabel("Address: ");
            addrLabel.setLabelFor(addrField);
            JPanel addrPanel = createInfoFieldPanel(addrLabel, addrField);

            //notes
            JTextField noteField = new JTextField();
            noteField.setPreferredSize(new Dimension(250, 30));
            JLabel noteLabel = new JLabel("Notes: ");
            noteLabel.setLabelFor(noteField);
            JPanel notePanel = createInfoFieldPanel(noteLabel, noteField);

            //info layout
            infoPanel.setLayout(new FlowLayout());
            infoPanel.add(idPanel);
            infoPanel.add(namePanel);
            infoPanel.add(gradePanel);
            infoPanel.add(addrPanel);
            infoPanel.add(notePanel);

            //img layout
            imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
            imgPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            JButton imgButton = new JButton("Load Image");
            imgButton.addActionListener((event) -> chooseImage());
            imgPanel.add(imgButton);
            BufferedImage myPicture;
            try {
                myPicture = ImageIO.read(new File(Main.pathJar+"/images/user.png"));
                //resize img
                BufferedImage resized = resizeImage(myPicture, 170, 170);
                imgLabel.setIcon(new ImageIcon(resized));

            } catch (IOException ex) {
                //Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgPanel.add(imgLabel);

            //submit button
            JPanel bottomP = new JPanel();
            bottomP.setPreferredSize(new Dimension(1000, 70));
            JButton submitButton = new JButton("SUBMIT");
            submitButton.setPreferredSize(new Dimension(100, 30));
            submitButton.addActionListener((event) -> {

                String id = idField.getText();
                String name = nameField.getText();
                int idx = name.lastIndexOf(' ');
                String fn = name.substring(0, idx);
                String ln = name.substring(idx + 1);
                String grade = gradeField.getText();
                String img = imgPath;
                String addr = addrField.getText();
                String note = noteField.getText();

                Object[] rowData = {false, id, fn, ln, grade, img, addr, note};
                addDataToMainTable(rowData);

                ASFrame.dispose();
            });
            bottomP.add(submitButton);

            //idField.getText();    
            ASFrame.add(ASLabel, BorderLayout.PAGE_START);
            ASFrame.add(infoPanel, BorderLayout.CENTER);
            ASFrame.add(imgPanel, BorderLayout.LINE_END);
            ASFrame.add(bottomP, BorderLayout.PAGE_END);
            ASFrame.setVisible(true);
        });
    }

}
