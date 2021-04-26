package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void checkLength(Element element, int current){
        if (element.left != 0){
            checkLength(elements[element.left],current + 1);
        }

        if (element.right != 0){
            checkLength(elements[element.right],current + 1);
        }

        if (height < current) {
            height = current;
        }
    }

    static class Element{
        int key;
        int left;
        int right;

        public Element(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static int height = 0;
    static Element[] elements;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        elements = new Element[n + 1];

        for (int i = 1; i <= n ; i++) {
            int input1 = scanner.nextInt();
            int input2 = scanner.nextInt();
            int input3 = scanner.nextInt();
            elements[i] = new Element(input1, input2, input3);
        }

        if(n != 0) {
            checkLength(elements[1], 1);
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))){
            bufferedWriter.write(height + "");
        }

    }


}
