package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static List<Integer> merge(List<Integer> lhs, List<Integer> rhs, int l, int r, BufferedWriter writer) throws IOException{
        int i = 0;
        int j = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(i < lhs.size() || j < rhs.size()){
            if(j == rhs.size() || i < lhs.size() && lhs.get(i) <= rhs.get(j)){
                arrayList.add(lhs.get(i));
                i++;
            }else{
                arrayList.add(rhs.get(j));
                j++;
            }
        }

//        writer.write("Arr: ");
//        for(int k : arrayList)
//            writer.write(k + " ");
//        writer.write("\n");

        writer.write((l + 1) + " " + (r + 1) + " " + arrayList.get(0) + " " + arrayList.get(arrayList.size() - 1) + "\n");

        return arrayList;
    }

    public static List<Integer> merge_sort(List<Integer> arrayList, int l, int r, BufferedWriter writer) throws IOException{
        if(arrayList.size() == 1)
            return arrayList;

        List<Integer> lhs = new ArrayList<>();
        lhs = new ArrayList<Integer> (arrayList.subList(0, arrayList.size() / 2));


        List<Integer> rhs = new ArrayList<>();
        rhs = new ArrayList<Integer> (arrayList.subList(arrayList.size() / 2, arrayList.size()));

//        writer.write("\n");
//        writer.write("lhs: \n");
//        for(int k : lhs)
//            writer.write(k + " ");
//        writer.write("\n");
//
//        writer.write("rhs: \n");
//        for(int k : rhs)
//            writer.write(k + " ");
//        writer.write("\n");
//
//        writer.write("l: " + l + ", " + "r: " + r + "\n");
//        writer.write("\n");

        //System.out.println((l + 1) + " " +  (l + arrayList.size() / 2) + " " + arrayList.get(0) + " " + arrayList.get(arrayList.size() / 2 - 1));
        lhs = merge_sort(lhs, l, l + arrayList.size() / 2 - 1, writer);

        //System.out.println((l + arrayList.size() / 2 + 1) + " " + (r + 1) + " " + arrayList.get(arrayList.size() / 2) + " " + arrayList.get(arrayList.size() - 1));
        rhs = merge_sort(rhs, l + arrayList.size() / 2, r, writer);
        try {
            return merge(lhs, rhs, l, r, writer);
        }catch (Exception e){}

        return null;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        List<Integer> arrayList = new ArrayList<Integer>();
        int n;

        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            arrayList.add(scanner.nextInt());
        }

        List sorted = new ArrayList();
//        sorted = merge_sort(arrayList, 0, arrayList.size() - 1, writer);

//        for(int i: (ArrayList<Integer>)sorted) {
//            writer.write(i + " ");
//        }

//        System.out.println(couInv);
//        writer.write(couInv);

        scanner.close();
        writer.close();
    }
}
