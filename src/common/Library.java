/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Library {
        protected Scanner sc;

    public Library() {
        sc = new Scanner(System.in);
    }
    public int getInt(String promt, int m, int n) {
        int a = -1;
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + m + " and " + n);
            }
        }
        return a;
    }
    
//    public int getIntNoLimit(String promt) {
//        int a = -1;
//        while (true) {
//            System.out.print(promt + ": ");
//            try {
//                String s = sc.nextLine();
//                a = Integer.parseInt(s);
//                return a;
//            } catch (Exception e) {
//                System.out.println("Invalid input");
//            }
//        }
//    }

//    public String getString(String mes){
//        System.out.print(mes);
//        return sc.nextLine();
//    }
    
    public String inputString(String mes) {
        System.out.print(mes);
        while (true) {
            String result = sc.nextLine();
            if (result.isEmpty()) {
//                System.err.println("Not empty!");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }
    
    public double checkInputDouble(String promt) {
        //loop until user input correct
        while (true) {
            System.out.print(promt + ": ");
            try {
                double result = Double.parseDouble(sc.nextLine());
                if (result > 0) {
                    return result;
                } else {
                    return 0;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
    
//    public String checkInputPathFile(String promt) {
//        System.out.print(promt);
//        while (true) {
//            String result = sc.nextLine().trim();
//            File file = new File(result);
//            //check file exist or not and path must be file
//            if (!file.exists() || !file.isFile()) {
//                System.err.println("Path doesn't exist");
//                System.out.print("Enter again: ");
//            } else {
//                return result;
//            }
//        }
//    }
    
    public String checkInputPathFile(String promt) {
//        Person p = new Person();
        System.out.print(promt);
        while (true) {
            String result = sc.nextLine().trim();
            File file = new File(result);
            
            //check file exist or not and path must be file
            if (!file.exists() || !file.isFile()) {
                System.err.println("Path doesn't exist");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }
    
    public boolean checkInputYN(String mes) {
        System.out.print(mes);
        while (true) {
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
}
