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

        if(lhs < nElements && array[lhs] == number)
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
