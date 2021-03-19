package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

//        for (int i = 0; i < n; i++) {
//            arrayList.add(i + 1);
//        }

        for(int i = 1; i <= n; i++)
            arrayList.add(arrayList.size()/2, i);

        for(int i: arrayList) {
            writer.write(i + " ");
        }

        scanner.close();
        writer.close();
    }
}