package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.javac.jvm.ByteCodes.swap;


public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));


        int n = scanner.nextInt();
        int[] queue = new int[1000000];
        int head = 0;
        int tail = 0;

        for(int i = 0; i < n; i++){
            char c = scanner.next().charAt(0);
            switch (c) {
                case '+':
                    int t = scanner.nextInt();
                    queue[tail] = t;
                    tail++;
                    break;
                case '-':
                    writer.write(queue[head] + "\n");
                    head++;
                    break;
            }
        }


        scanner.close();
        writer.close();
    }
}
