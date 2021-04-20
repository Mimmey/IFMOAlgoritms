package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    public static int binarySearchFirstOccurrence(int[] array, int nElements, int number){
        int lhs = -1;
        int rhs = nElements;

        while(rhs > lhs + 1){
            int medium = (rhs + lhs) / 2;
            if(array[medium] < number)
                lhs = medium;
            else
                rhs = medium;
        }

        if(rhs < nElements && array[rhs] == number)
            return rhs + 1;
        return -1;
    }

    public static int binarySearchSecondOccurrence(int[] array, int nElements, int number){
        int lhs = -1;
        int rhs = nElements;

        while(rhs > lhs + 1){
            int medium = (rhs + lhs) / 2;
            if(array[medium] > number)
                rhs = medium;
            else
                lhs = medium;
        }

        if(lhs < nElements && lhs >= 0 && array[lhs] == number)
            return lhs + 1;
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int nElements;
        int nRequests;

        nElements = scanner.nextInt();

        int array[] = new int[nElements];
        for(int i = 0; i < nElements; i++)
            array[i] = scanner.nextInt();

        nRequests = scanner.nextInt();

        for(int i = 0; i < nRequests; i++) {
            int request = scanner.nextInt();
            int first = binarySearchFirstOccurrence(array, nElements, request);
            int second = binarySearchSecondOccurrence(array, nElements, request);

            writer.write(first + " " + second + "\n");
        }

        scanner.close();
        writer.close();
    }
}


//import java.io.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
//
//        int n = Integer.parseInt(br.readLine());
//        int[] mas = new int[n];
//        String[] str = br.readLine().split(" ");
//
//        for (int i = 0; i < n; i++) {
//            mas[i] = Integer.parseInt(str[i]);
//        }
//
//        int m = Integer.parseInt(br.readLine());
//        int[] mas2 = new int[m];
//        str = br.readLine().split(" ");
//
//        for (int i = 0; i < m; i++) {
//            mas2[i] = Integer.parseInt(str[i]);
//        }
//
//        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
//
//            for (int i = 0; i < m ; i++) {
//                int l = 0;
//                int r = n-1;
//
//                while (l<r-1){
//                    int k = (l+r)/2;
//                    if(mas[k]>=mas2[i])
//                        r=k;
//                    else
//                        l=k;
//                }
//
//                if(mas[l] == mas2[i])
//                    bw.write((l+1)+" ");
//                else if(mas[r] == mas2[i])
//                    bw.write((r+1)+" ");
//                else
//                    bw.write(-1+" ");
//
//                l = 0;
//                r = n-1;
//
//                while (l<r-1){
//                    int k = (l+r)/2;
//                    if(mas[k]<=mas2[i])
//                        l=k;
//                    else
//                        r=k;
//                }
//
//                if(mas[r] == mas2[i])
//                    bw.write((r+1)+"\n");
//                else if(mas[l] == mas2[i])
//                    bw.write((l+1)+"\n");
//                else
//                    bw.write(-1+"\n");
//
//
//            }
//
//
//
//
//        }
//
//    }
//
//
//}
