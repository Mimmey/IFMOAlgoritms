package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    //public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static int[] arrayList;
    public static int k;

    public static void qSort(int lhs, int rhs, BufferedWriter writer){
        int i, j, pivot, temp, amount;

        amount = (rhs - lhs) / k;

        pivot = arrayList[lhs + amount / 2 * k];
        i = lhs;
        j = rhs;

        do{
            while(arrayList[i] < pivot)
                i += k;
            while(arrayList[j] > pivot)
                j -= k;
            if(i <= j) {
                temp = arrayList[i];
                arrayList[i] = arrayList[j];
                arrayList[j] = temp;
                i += k;
                j -= k;
            }
        }while(j >= i);

        if(lhs < j)
            qSort(lhs, j, writer);
        if(i < rhs)
            qSort(i, rhs, writer);
    }

    public static boolean is (int n){
        for (int i = 1; i < n; i++)
            if (arrayList[i] < arrayList[i - 1])
                return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;

        n = scanner.nextInt();
        k = scanner.nextInt();

        arrayList = new int[n];

        for(int i = 0; i < n; i++)
            arrayList[i] = scanner.nextInt();

        for (int i = 0; i < k; i++)
            qSort(i, n - 1 - (n - 1 - i) % k, writer);

        writer.write("" + (is(n) ? "YES" : "NO"));

        scanner.close();
        writer.close();
    }
}
