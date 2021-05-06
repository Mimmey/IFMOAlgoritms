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
        int indexInOutput;

        public Element() {
            key = 0;
            leftChild = 0;
            rightChild = 0;
            rightChild = 0;
            height = 0;
            balance = 0;
            indexInOutput = 0;
        }
    }

    public static Scanner scanner;
    public static Element[] elements;
    public static Element[] sortedElements;
    public static BufferedWriter writer;
    public static int root;
    public static int n;

    public static void bigLeftRotate(int index) {
        int rightChild = elements[index].rightChild;
        int leftChildOfRightChild = elements[rightChild].leftChild;
        int leftChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].leftChild;
        int rightChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].rightChild;

        elements[leftChildOfRightChild].leftChild = index;
        elements[leftChildOfRightChild].rightChild = rightChild;
        elements[index].rightChild = leftChildOfLeftChildOfRightChild;
        elements[rightChild].leftChild = rightChildOfLeftChildOfRightChild;
        root = leftChildOfRightChild;
    }

    public static void printElements(int index) throws IOException {
//        if (index < n + 1) {
//            writer.write(sortedElements[index].key + " ");
//            if (sortedElements[index].leftChild == -1) {
//                writer.write(0 + "");
//            } else {
//                writer.write((2 * index) + "");
//            }
//
//            writer.write(" ");
//
//            if (sortedElements[index].rightChild == -1) {
//                writer.write(0 + " ");
//            } else {
//                writer.write((2 * index + 1) + "");
//            }
//
//            writer.write("\n");
//        }
//        if (2 * index < n + 1) {
//            printElements(2 * index);
//        }
//        if (2 * index + 1 < n + 1) {
//            printElements(2 * index + 1);
//        }

        while (index < n + 1) {
            writer.write(sortedElements[index].key + " ");
            if (sortedElements[index].leftChild == -1) {
                writer.write(0 + "");
            } else {
                writer.write((2 * index) + "");
            }

            writer.write(" ");

            if (sortedElements[index].rightChild == -1) {
                writer.write(0 + " ");
            } else {
                writer.write((2 * index + 1) + "");
            }

            writer.write("\n");
            index++;
        }
    }

    public static void smallLeftRotate(int index) {
        int rightChild = elements[index].rightChild;

        elements[index].rightChild = elements[rightChild].leftChild;
        elements[rightChild].leftChild = index;
        root = rightChild;
    }

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

    public static void setIndicesOfOutput(int index) {
//        if (index == root) {
//            elements[index].indexInOutput = 1;
//        }
//
//        if (elements[index].leftChild != -1) {
//           elements[elements[index].leftChild].indexInOutput = elements[index].indexInOutput * 2;
//           setIndicesOfOutput(elements[index].leftChild);
//        }
//
//        if (elements[index].rightChild != -1) {
//            elements[elements[index].rightChild].indexInOutput = elements[index].indexInOutput * 2 + 1;
//            setIndicesOfOutput(elements[index].rightChild);
//        }
//
//        sortedElements[elements[index].indexInOutput] = elements[index];
        for (int i = index; i < n + 1; i++) {
            if (index == root) {
                elements[i].indexInOutput = 1;
                sortedElements[1] = elements[i];
            }

            if(elements[i].leftChild != -1) {

            }
        }
    }

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(new File("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));
        root = 0;

        n = scanner.nextInt();
        elements = new Element[n];
        sortedElements = new Element[n + 1];

        for (int i = 0; i < n; i++) {
            elements[i] = new Element();
        }

        for (int i = 0; i < n; i++) {
            elements[i].key = scanner.nextInt();
            elements[i].leftChild = scanner.nextInt() - 1;
            elements[i].rightChild = scanner.nextInt() - 1;
        }

        setHeightAndBalance(0);

        if (elements[0].balance == 2 && elements[elements[0].rightChild].balance == -1) {
            bigLeftRotate(0);
        } else if (elements[0].balance == 2) {
            smallLeftRotate(0);
        }

        setIndicesOfOutput(root);
        writer.write(n + "\n");
        printElements(1);

        scanner.close();
        writer.close();
    }
}
