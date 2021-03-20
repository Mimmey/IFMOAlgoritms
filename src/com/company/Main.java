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

    public static int runBinarySearchIteratively(int k, int lhs, int rhs) {
        int index = Integer.MAX_VALUE;

        while (lhs <= rhs) {
            int mid = (lhs + rhs) / 2;
            if(mid == 0 && k <= arrayList.get(0))
                return 0;
            else if (arrayList.get(mid) < k) {
                lhs = mid + 1;
            } else if (mid - 1 >= 0 && k < arrayList.get(mid - 1)) {
                rhs = mid - 1;
            } else if (k <= arrayList.get(mid) && k >= arrayList.get(mid - 1)) {
                index = mid;
                break;
            }
        }
        return index;
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

        for(int i = 2; i < k2; i++)
            arrayList.add(a * arrayList.get(arrayList.size() - 2) + b * arrayList.get(arrayList.size() - 1) + c);

        int a1 = arrayList.get(arrayList.size() - 2);
        int a2 = arrayList.get(arrayList.size() - 1);

        qSort(0, arrayList.size() - 1, writer);

//        writer.write("Arr before cycle: ");
//        for(int i : arrayList)
//            writer.write(i + " ");
//        writer.write("\n\n");

        for(int i = k2; i < n; i++){
            int a3 = a * a1 + b * a2 + c;

//            writer.write("a1: " + a1 + "\n");
//            writer.write("a2: " + a2 + "\n");
//            writer.write("a3: " + a3 + "\n");
//
//            writer.write("Arr before getting a3: ");
//            for(int j : arrayList)
//                writer.write(j + " ");
//            writer.write("\n");

            if(a3 < arrayList.get(arrayList.size() - 1)) {
//                if(a3 <= arrayList.get(0))
//                    arrayList.add(0, a3);
//                else if(a3 >= arrayList.get(arrayList.size() - 1))
//                    arrayList.add(a3);
//                else for(int k = 0; k < arrayList.size() - 1; k++)
//                    if(arrayList.get(k) <= a3 && arrayList.get(k + 1) >= a3) {
//                        arrayList.add(k + 1, a3);
//                        break;
//                    }

                int pos = runBinarySearchIteratively(a3, 0, arrayList.size() - 1);
                arrayList.add(pos, a3);
                arrayList.remove(arrayList.size() - 1);
            }

//            writer.write("Arr after getting a3: ");
//            for(int j : arrayList)
//                writer.write(j + " ");
//            writer.write("\n");

            a1 = a2;
            a2 = a3;

//            writer.write("a1 after: " + a1 + "\n");
//            writer.write("a2 after: " + a2 + "\n");
//            writer.write("a3 after: " + a3 + "\n");
        }

        for(int i = k1 - 1; i < k2; i++)
            writer.write(arrayList.get(i) + " ");

        scanner.close();
        writer.close();
    }
}
