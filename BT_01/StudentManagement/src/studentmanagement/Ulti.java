/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

/**
 *
 * @author HP
 */
public class Ulti {
    
    public static   String defaultDataSavingPath = Main.pathJar+"/data/data.bin";

    public Ulti() {
    }

    public static Object[][] addFirstBooleanColumnTo2DArray(Object[][] arr) {
        if(arr==null)
            return null;
        int row = arr.length;
        System.out.println(row);
        if (row == 0) {
            return arr;
        }
        int col = arr[0].length;
        System.out.println(col);
        Object[][] result = new Object[row][col + 1];
        for (int i = 0; i < row; i++) {
            result[i][0] = false;
            for (int j = 0; j < col; j++) {
                try {
                    result[i][j + 1] = arr[i][j];
                } catch (ArrayIndexOutOfBoundsException err) {
                    System.out.println("col: " + j);
                }
            }
        }
        return result;
    }

    public static Object[][] removeFirstBooleanColumnFrom2DArray(Object[][] arr) {
        int row = arr.length;
        if (row == 0) {
            return arr;
        }
        int col = arr[0].length;

        Object[][] result = new Object[row][col - 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {
                try {
                    result[i][j] = arr[i][j + 1];
                } catch (ArrayIndexOutOfBoundsException err) {
                    //System.out.println("col: " + j);
                }
            }
        }
        return result;
    }
}
