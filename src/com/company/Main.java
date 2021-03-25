package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sun.tools.javac.jvm.ByteCodes.swap;


public class Main {

    public static final int N = 500;
    public static final int N1 = 10000;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n = scanner.nextInt();

        for(int i = 0; i < n; i++) {
            char[] stack = new char[N1];
            int top = 0;

            String str = scanner.next();
            boolean is = true;

            for (int j = 0; j < str.length(); j++) {
                if(is == false)
                    break;
                switch (str.charAt(j)) {
                    case '[':
                        stack[top] = '[';
                        top++;
                        break;
                    case '(':
                        stack[top] = '(';
                        top++;
                        break;
                    case ']':
                        if(top == 0) {
                            writer.write("NO\n");
                            is = false;
                            continue;
                        }
                        if (stack[top - 1] == '[') {
                            top--;
                            break;
                        } else {
                            writer.write("NO\n");
                            is = false;
                            continue;
                        }
                    case ')':
                        if(top == 0) {
                            writer.write("NO\n");
                            is = false;
                            continue;
                        }
                        if (stack[top - 1] == '(') {
                            top--;
                            break;
                        } else {
                            writer.write("NO\n");
                            is = false;
                            continue;
                        }
                    }
            }
            if(is && top != 0)
                writer.write("NO\n");
            else if(is)
                writer.write("YES\n");
        }

//
        scanner.close();
        writer.close();
    }
}