/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentmanagement;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author HP
 */
public class Main {

    public static MainFrame mainFrame;
    
    public static String pathJar;

    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO code application logic here
        pathJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath();
        mainFrame = new MainFrame();
        
    }
}
