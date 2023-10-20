/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import Common.Library;
import Model.Person;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class PersonDAO {


/**
 *
 * @author Xuan Vinh
 */
public class PersonDAO {
    private static PersonDAO instance = null;
    Library l;

    public PersonDAO() {
        l = new Library();
    }

    public static PersonDAO Instance() {
        if (instance == null) {
            synchronized (PersonDAO.class) {
                if (instance == null) {
                    instance = new PersonDAO();
                }
            }
        }
        return instance;
    }

    public void findPersonInfo() {
        ArrayList<Person> lp = new ArrayList<>();
        String pathFile = l.checkInputPathFile("Enter path file: ");
        double money = l.checkInputDouble("Enter number");
        lp = getListPerson(pathFile, money);
        if (lp == null) {
            return;
        }
        printListPerson(lp);
    }

    public void copyNewFile() {
        String pathFileInput = l.checkInputPathFile("Enter Source: ");
        String pathFileOutput = l.checkInputPathFile("Enter new file name: ");
        String content = getNewContent(pathFileInput);
        System.out.println(content);
        writeNewContent(pathFileOutput, content);
    }

    public ArrayList<Person> getListPerson(String pathFile, double money) {
        ArrayList<Person> lp = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(pathFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] infoPerson = line.split(";");
                if (getSalary(infoPerson[2]) > money) {
                    lp.add(new Person(infoPerson[0], infoPerson[1],
                            getSalary(infoPerson[2])));
                }
            }
        } catch (Exception e) {
            System.err.println("Can't read file!!!");
            return null;
        }
        return lp;
    }

    public double getSalary(String salary) {
        double salaryResult = 0;
        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        } finally {
            return salaryResult;
        }
    }

    public void printListPerson(ArrayList<Person> lp) {
        if (lp.isEmpty()) {
            System.out.println("No result");
            return;
        }
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Address", "Money");
        for (Person person : lp) {
            System.out.printf("%-20s%-20s%-20.1f\n", person.getName(),
                    person.getAddress(), person.getMoney());
        }
        Collections.sort(lp);
        System.out.println("Max: " + lp.get(0).getName());
        System.out.println("Min: " + lp.get(lp.size() - 1).getName());
    }

    public String getNewContent(String pathFileInput) {
        ArrayList<String> newContentList = new ArrayList<>();
        File file = new File(pathFileInput);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String word = input.next();
                newContentList.add(word + " ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Can’t read file!!!");
        }
        StringBuilder content = new StringBuilder();
        for (String word : newContentList) {
            content.append(word);
        }
        return content.toString();
    }

    public void writeNewContent(String pathFileOutput, String content) {
        FileWriter fileWriter = null;
        File file = new File(pathFileOutput);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
            System.err.println("Write successful!!!");
        } catch (IOException ex) {
            System.err.println("Can’t write file!!!");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
}
