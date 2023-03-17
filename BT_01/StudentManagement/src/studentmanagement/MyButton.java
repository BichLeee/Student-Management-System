/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author HP
 */
public class MyButton extends JButton {

    public void myDecoration(String text, String img) {
        this.setText(text);
        this.setBackground(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setFocusPainted(false);
        this.setBorder(null);
        
        setImgIcon(img, new Dimension(30,30));
    }

    public MyButton() {

    }

    public void setImgIcon(String path, Dimension dimension) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(newImage));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
    }
}
