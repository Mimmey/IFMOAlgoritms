package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static ArrayList<Integer> sort(ArrayList<Integer> a, int max, BufferedWriter writer) throws IOException {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<Integer> c = new ArrayList<Integer>();

        for(int i = 0; i < max + 1; i++)
            c.add(0);

        for(int i = 0; i < a.size(); i++)
            a1.add(0);

        for(int i = 0; i < a.size(); i++)
            c.set(a.get(i), c.get(a.get(i)) + 1);

//        writer.write("C at first: ");
//        for(int i : c)
//            writer.write(i + " ");
//        writer.write("\n");

        for(int i = 1; i < c.size(); i++)
            c.set(i, c.get(i - 1) + c.get(i));

//        writer.write("C after sort: ");
//        for(int i : c)
//            writer.write(i + " ");
//        writer.write("\n");

        for(int i = a.size() - 1; i >= 0; i--){
            a1.set(c.get(a.get(i)) - 1, a.get(i));
            c.set(a.get(i), c.get(a.get(i)) - 1);
        }

//        writer.write("C after: ");
//        for(int i : c)
//            writer.write(i + " ");
//        writer.write("\n");
//
//        writer.write("a1 after: ");
//        for(int i : a1)
//            writer.write(i + " ");
//        writer.write("\n");

        return a1;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<Integer> b = new ArrayList<Integer>();

        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();

        int maxa = 0, maxb = 0;

        for(int i = 0; i < n; i++){
            int t = scanner.nextInt();
            a.add(t);
            if(t > maxa)
                maxa = t;
        }

        for(int i = 0; i < m; i++){
            int t = scanner.nextInt();
            b.add(t);
            if(t > maxb);
                maxb = t;
        }

        ArrayList<Integer> c = new ArrayList<Integer>();

        int maxc = 0;

        for(int i = 0; i < a.size(); i++)
            for(int j = 0; j < b.size(); j++) {
                int t = a.get(i) * b.get(j);
                c.add(t);
                if(t > maxc)
                    maxc = t;
            }

//        c = sort(c, maxc, writer);

        a = sort(a, maxa, writer);
        b = sort(b, maxb, writer);

        writer.write("A after changing: ");
        for(int i : a)
            writer.write(i + " ");
        writer.write("\n");

        writer.write("B after changing: ");
        for(int i : b)
            writer.write(i + " ");
        writer.write("\n");

        writer.write("Array Of Multiples: ");
        for(int i = 0; i < a.size(); i++)
            for(int j = 0; j < b.size(); j++)
                writer.write(a.get(i) * b.get(j) + " ");
        writer.write("\n");

//        writer.write("Array Of Multiples: ");
//
//        for(int i : c)
//            writer.write(i + " ");
//        writer.write("\n");

        int cou = 0;

//        for(int i = 0; i < c.size(); i += 10)
//            cou += c.get(i);

        writer.write(cou + "");

        scanner.close();
        writer.close();
    }
}
