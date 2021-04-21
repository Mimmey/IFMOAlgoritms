package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("input.txt"));

        String[] str =   scanner.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        double[] h = new double[n];
        h[0] = Double.parseDouble(str[1]);

        double next = h[0];
        double previous = 0;

        while (next - previous > 0.0000000001){
            h[1] = (next + previous) / 2;
            boolean isPreviousZero = false;

            for (int i = 2; i < n; i++) {
                h[i] = 2 * h[i-1] - h[i-2] + 2;

                if(h[i] <= 0) {
                    isPreviousZero = true;
                    break;
                }
            }

            if (isPreviousZero)
                previous = h[1];
            else
                next = h[1];
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write(h [n - 1] + "");
        }
    }
}
