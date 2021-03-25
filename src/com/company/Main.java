package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        Stack<Integer> list = new Stack<Integer>();
        int n = scanner.nextInt();

        for(int i = 0; i < n; i++){
            char c = scanner.next().charAt(0);
            switch (c) {
                case '+':
                    int t = scanner.nextInt();
                    list.push(t);
                    break;
                case '-':
                    writer.write(list.pop() + "\n");
                    break;
            }
        }


        scanner.close();
        writer.close();
    }
}
