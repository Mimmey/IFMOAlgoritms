package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();
        int array[] = new int[n];

        for(int i = 0; i < n; i++)
            array[i] = scanner.nextInt();

        boolean is = true;

        for(int i = 0; i < n; i++) {
            if (2 * i + 1 < n && array[2 * i + 1] < array[i] || 2 * i + 2 < n && array[2 * i + 2] < array[i]) {
//                writer.write(i + " ");
                is = false;
                break;
            }
        }

//        5
//        1 3 2 5 4

        writer.write(is ? "YES" : "NO");

        scanner.close();
        writer.close();
    }
}
