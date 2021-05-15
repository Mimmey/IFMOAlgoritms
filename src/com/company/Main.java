package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

        String [] request = new String[100000];
        char[] q = new char[100000];
        int head = 0;
        int tail = 0;
        char[] reg = new char[200];

        HashMap<String,Integer> map = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));

        int length = 0;

        while (bufferedReader.ready()) {
            request[length] = bufferedReader.readLine();

            if(request[length].charAt(0) == ':'){
                map.put(request[length].substring(1),length);
            }

            length++;
        }

        bufferedReader.close();

        int[] indices = new int[100000];

        for (int i = 0; i < length; i++) {
            switch (request[i].charAt(0)) {
                case 'J':
                    indices[i] = map.get(request[i].substring(1));
                    continue;
                case 'Z':
                    indices[i] = map.get(request[i].substring(2));
                    break;
                case 'E':
                case 'G':
                    indices[i] = map.get(request[i].substring(3));
                    break;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int cou = 0;
        while (cou < length){
            char s = request[cou].charAt(0);

            if (s >= '0' && s <= '9'){
                q[tail] = (char) Integer.parseInt(request[cou]);
                tail++;
                cou++;
                continue;
            }

            switch (s){
                case '+':
                    q[tail] = q[head] += q[head + 1];
                    head += 2;
                    tail++;
                    break;
                case '-':
                    q[tail] = q[head] -= q[head + 1];
                    head += 2;
                    tail++;
                    break;
                case '*':
                    q[tail] = q[head] *= q[head + 1];
                    head += 2;
                    tail++;
                    break;
                case '/':
                    q[tail] = (q[head + 1] == 0) ? 0 : (q[head] /= q[head + 1]);
                    head += 2;
                    tail++;
                    break;
                case '%':
                    q[tail] = (q[head + 1] == 0) ? 0 : (q[head] %= q[head + 1]);
                    head += 2;
                    tail++;
                    break;
                case '>':
                    reg[request[cou].charAt(1)] = q[head];
                    head++;
                    break;
                case '<':
                    q[tail] = reg[request[cou].charAt(1)];
                    tail++;
                    break;
                case 'P':
                    if (request[cou].length() == 1) {
                        writer.write((int)q[head] + "\n");
                        head++;
                    }
                    else {
                        writer.write((int)reg[request[cou].charAt(1)] + "\n");
                    }
                    break;
                case 'C':
                    if (request[cou].length() == 1) {
                        writer.write((char)(q[head] % 256) );
                        head++;
                    }
                    else {
                        writer.write((char)(reg[request[cou].charAt(1)] % 256));
                    }
                    break;
                case 'J':
                    cou = indices[cou];
                    continue;
                case 'Z':
                    if (reg[request[cou].charAt(1)] == 0){
                        cou = indices[cou];
                        continue;
                    }
                    break;
                case 'E':
                    if (reg[request[cou].charAt(1)] == reg[request[cou].charAt(2)]) {
                        cou = indices[cou];
                        continue;
                    }
                    break;
                case 'G':
                    if (reg[request[cou].charAt(1)] > reg[request[cou].charAt(2)]) {
                        cou = indices[cou];
                        continue;
                    }
                    break;
                case 'Q':
                    cou = length;
                    continue;
            }
            cou++;
        }
        writer.close();
    }
}
