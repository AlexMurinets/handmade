package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        FileReader fileReader = new FileReader("Text.txt");
        FileWriter fileWriter = new FileWriter("TextRes.txt");
        Scanner scanner = new Scanner(fileReader);
        String res = "";
        String name = "";
        String phone = "";
        while (scanner.hasNext()){
            name = scanner.next();
            phone = scanner.next();
            if(name.charAt(0) == 'K' || name.charAt(0) == 'C')
                res += name + " " + phone +  "\n";
        }
        fileWriter.write(res);
        fileReader.close();
        fileWriter.close();
    }
}
