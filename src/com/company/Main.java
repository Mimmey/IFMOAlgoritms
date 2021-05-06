package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static class Element{
        int key;
        int leftChild;
        int rightChild;
        int height;
        int balance;

        public Element() {
            key = 0;
            leftChild = 0;
            rightChild = 0;
            rightChild = 0;
            height = 0;
            balance = 0;
        }
    }

    public static Scanner scanner;
    public static Element[] elements;
    public static BufferedWriter writer;

    public static void setHeightAndBalance(int index) {
        if (elements[index].rightChild == -1 && elements[index].leftChild == -1) {
            elements[index].height = 0;
            elements[index].balance = 0;
        } else if (elements[index].rightChild == -1) {
            setHeightAndBalance(elements[index].leftChild);
            int leftChildHeight = elements[elements[index].leftChild].height;
            elements[index].height = leftChildHeight + 1;
            elements[index].balance = -1 - leftChildHeight;
        } else if (elements[index].leftChild == -1) {
            setHeightAndBalance(elements[index].rightChild);
            int rightChildHeight = elements[elements[index].rightChild].height;
            elements[index].height = rightChildHeight + 1;
            elements[index].balance = rightChildHeight + 1;
        } else {
            setHeightAndBalance(elements[index].leftChild);
            setHeightAndBalance(elements[index].rightChild);
            int leftChildHeight = elements[elements[index].leftChild].height;
            int rightChildHeight = elements[elements[index].rightChild].height;
            elements[index].height = Math.max(leftChildHeight, rightChildHeight) + 1;
            elements[index].balance = rightChildHeight - leftChildHeight;
        }
    }

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(new File("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));
        int n = scanner.nextInt();

        elements = new Element[n];

        for (int i = 0; i < n; i++) {
            elements[i] = new Element();
        }

        for (int i = 0; i < n; i++) {
            elements[i].key = scanner.nextInt();
            elements[i].leftChild = scanner.nextInt() - 1;
            elements[i].rightChild = scanner.nextInt() - 1;
        }

        setHeightAndBalance(0);

        for (Element i : elements) {
            writer.write(i.balance + "\n");
        }

        scanner.close();
        writer.close();
    }
}
