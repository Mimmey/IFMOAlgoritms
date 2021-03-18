package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.javac.jvm.ByteCodes.swap;


public class Main {

    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static int split(int lhs, int rhs, int pivot, BufferedWriter writer) throws IOException {
        writer.write("\nIn split: ");
        for(int i = lhs; i <= rhs; i++)
            writer.write(arrayList.get(i) + " ");

        writer.write("\n");

        int j = lhs;
        for(int i = lhs; i <= rhs; i++)
            if(arrayList.get(i) < pivot){
                int t = arrayList.get(i);
                arrayList.set(i, arrayList.get(j));
                arrayList.set(j, t);
                j++;
            }
        return j;
    }

    public static void quickSort(int lhs, int rhs, BufferedWriter writer) throws IOException {
        writer.write("\nIn quickSort, lhs: " + lhs + ", rhs: " + rhs + "\n");

        if(rhs == lhs)
            return;
        int pivot = arrayList.get(lhs + (lhs + rhs) / 2);
        int divisor = split(lhs, rhs, pivot, writer);

        writer.write("pivot: " + pivot + "\n");
        writer.write("divisor: " + divisor + "\n\n");

        quickSort(lhs, divisor - 1, writer);
        quickSort(divisor, rhs, writer);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

        int k1, k2;
        k1 = scanner.nextInt();
        k2 = scanner.nextInt();

        int a, b, c;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();

        int t1, t2;
        t1 = scanner.nextInt();
        t2 = scanner.nextInt();

        arrayList.add(t1);
        arrayList.add(t2);

        for(int i = 2; i < n; i++)
            arrayList.add(a * arrayList.get(arrayList.size() - 2) + b * arrayList.get(arrayList.size() - 1) + c);

        quickSort(0, n - 1, writer);

        for(int i = k1; i <= k2; i++)
            writer.write("" + arrayList.get(i - 1) + " ");

        scanner.close();
        writer.close();
    }
}
