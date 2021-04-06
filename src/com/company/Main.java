package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    //public static ArrayList<Integer> arrayList = new ArrayList<>();
    public static int[] arrayList;
    public static int k1;
    public static int k2;

    public static void qSort(int lhs, int rhs, BufferedWriter writer){
        int i, j, pivot, temp;

        pivot = arrayList[(lhs + rhs) / 2];
        i = lhs;
        j = rhs;

        do{
            while(arrayList[i] < pivot)
                i++;
            while(arrayList[j] > pivot)
                j--;
            if(i <= j) {
                temp = arrayList[i];
                arrayList[i] = arrayList[j];
                arrayList[j] = temp;
                i++;
                j--;
            }
        }while(j >= i);

        if(lhs < j)
            if (k1 - 1 >= lhs && k1 - 1<= j || k2 - 1 >= lhs && k2 - 1 <= j || lhs >= k1 - 1 && j <= k2- 1)
                qSort(lhs, j, writer);
        if(i < rhs)
            if (k1 - 1 >= i && k1 - 1<= rhs || k2 - 1 >= i && k2 - 1 <= rhs || i >= k1 - 1 && rhs <= k2- 1)
                qSort(i, rhs, writer);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

        k1 = scanner.nextInt();
        k2 = scanner.nextInt();

        int a, b, c;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();

        int t1, t2;
        t1 = scanner.nextInt();
        t2 = scanner.nextInt();

        arrayList = new int[n];

//        arrayList.add(t1);
//        arrayList.add(t2);
//
//        for(int i = 2; i < n; i++)
//            arrayList.add(a * arrayList.get(arrayList.size() - 2) + b * arrayList.get(arrayList.size() - 1) + c);
//
//        qSort(0, n - 1, writer);
//
//        for(int i = k1; i <= k2; i++)
//            writer.write("" + arrayList.get(i - 1) + " ");

        arrayList[0] = t1;
        arrayList[1] = t2;

        for(int i = 2; i < n; i++)
            arrayList[i] = a * arrayList[i - 2] + b * arrayList[i - 1] + c;

        qSort(0, n - 1, writer);

//        StringBuilder res = new StringBuilder ("");
//        for (int k = k1; k1 <= k2; k++)
//            res.append("${a[k]} ");
//
//        writer.write(res.substring(0, res.length() - 1));

        for(int i = k1; i <= k2; i++)
           writer.write("" + arrayList[i - 1] + " ");

        scanner.close();
        writer.close();
    }
}
