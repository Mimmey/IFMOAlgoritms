package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    static class Element {
        int key;
        int left;
        int right;

        public Element(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public static Element[] elements;
    public static int cou = 0;
    public static int all = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();

        all = n;
        elements = new Element[n + 1];

        for (int i = 1; i <= n; i++) {
            int input1 = scanner.nextInt();
            int input2 = scanner.nextInt();
            int input3 = scanner.nextInt();

            elements[i] = new Element(input1, input2, input3);
        }

        int m = scanner.nextInt();
        int[] keys = new int[m];

        for (int i = 0; i < m ; i++) {
            keys[i] = scanner.nextInt();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < m ; i++) {
                whereItIs(1, keys[i]);
                bufferedWriter.write(all + "\n");
            }
        }
    }

    public static void amount(Element element) {
        if(element == null)
            return;
        cou++;

        amount(elements[element.left]);
        amount(elements[element.right]);
    }

    public static void whereItIs(int index, int key) {
        if (elements[index] == null) {
            return;
        }

        if (elements[index].key == key) {
            amount(elements[index]);
            elements[index] = null;
            all -= cou;
            cou = 0;
            return;
        }

        if(key < elements[index].key) {
            whereItIs(elements[index].left, key);
        }

        if(key > elements[index].key) {
            whereItIs(elements[index].right, key);
        }

    }

}
