package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Main {

    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

//        arrayList.add(3);
//        arrayList.add(2);
//
//        switch(n){
//            case 1:
//                writer.write("1");
//                break;
//            case 2:
//                writer.write("1 2");
//                break;
//            case 3:
//                writer.write("1 3 2");
//                break;
//            case 4:
//                writer.write("1 4 2 3");
//                break;
//            default:
//                writer.write("1 ");
//
//                for(int i = 4; i <= n; i += 2)
//                    writer.write(i + " ");
//
//                for(int i = 5; i <= n; i += 2) {
//                    arrayList.add(arrayList.get(0));
//                    arrayList.remove(0);
//
//                    arrayList.add(arrayList.get(0));
//                    arrayList.remove(0);
//
//
//                    arrayList.add(0, i);
//                }
//
//                if(n % 2 == 0){
//                    arrayList.add(arrayList.get(0));
//                    arrayList.remove(0);
//                }
//
//
//                for(int i : arrayList)
//                    writer.write(i + " ");
//        }

        arrayList.add(1);
        arrayList.add(2);

        for(int i = 3; i <= n; i++) {
            arrayList.add(arrayList.size() / 2, i);
            int index = arrayList.size() / 2 + arrayList.size() % 2;
            int removed = arrayList.get(index);
            arrayList.remove(index);
            arrayList.add(removed);

            writer.write("n = " + i + ": ");
            if(n == 1)
                writer.write('1');
            else for(int j: arrayList) {
                writer.write(j + " ");
            }

            writer.write('\n');

        }

//        if(n == 1)
//            writer.write('1');
//        else for(int i: arrayList) {
//            writer.write(i + " ");
//        }

        scanner.close();
        writer.close();
    }
}