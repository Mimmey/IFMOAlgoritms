package com.company;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        SortedSet set = new TreeSet<Long>();
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            char operation = input.charAt(0);
            Long arg = Long.parseLong(input.substring(2));

            switch (operation) {
                case 'A': set.add(arg); break;
                case 'D': set.remove(arg); break;
                case '?':
                    if (set.contains(arg))
                        writer.write("Y\n");
                    else
                        writer.write("N\n");
                    break;
                default: break;
            }
        }

        scanner.close();
        writer.close();
    }
}
