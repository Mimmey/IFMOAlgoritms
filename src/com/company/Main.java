package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));


        int n = scanner.nextInt();
        int[] stack = new int[1000000];
        int top = 0;

        for(int i = 0; i < n; i++){
            char c = scanner.next().charAt(0);
            switch (c) {
                case '+':
                    int t = scanner.nextInt();
                    stack[top] = t;
                    top++;
                    break;
                case '-':
                    writer.write(stack[top - 1] + "\n");
                    top--;
                    break;
            }
        }


        scanner.close();
        writer.close();
    }
}