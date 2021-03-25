package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.javac.jvm.ByteCodes.swap;


public class Main {

    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void qSort(int lhs, int rhs, BufferedWriter writer){
        int i, j, pivot, temp;

        pivot = arrayList.get((lhs + rhs) / 2);
        i = lhs;
        j = rhs;

        do{
            while(arrayList.get(i) < pivot)
                i++;
            while(arrayList.get(j) > pivot)
                j--;
            if(i <= j) {
                temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(j));
                arrayList.set(j, temp);
                i++;
                j--;
            }
        }while(j >= i);

        if(lhs < j)
            qSort(lhs, j, writer);
        if(i < rhs)
            qSort(i, rhs, writer);
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

        int amount = k2 - k1 + 1;

        for(int i = 2; i < n; i++) {
            int t = a * arrayList.get(arrayList.size() - 2) + b * arrayList.get(arrayList.size() - 1) + c;
            arrayList.add(t);
        }

        qSort(0, n - 1, writer);

        for(int i = k1; i <= k2; i++)
            writer.write("" + arrayList.get(i - 1) + " ");

        scanner.close();
        writer.close();
    }
}
