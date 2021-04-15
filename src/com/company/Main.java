package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    public static int top;
    public static final int INF = -2000000000;

    public static void minHeapify(int[] array, int index){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int indMin = index;

        if(left < top && array[left] < array[indMin])
            indMin = left;

        if(right < top && array[right] < array[indMin])
            indMin = right;

        if(indMin != index){
            int t = array[indMin];
            array[indMin] = array[index];
            array[index] = t;
            minHeapify(array, indMin);
        }
    }

    public static void insert(int[] array, int a){
        top++;
        array[top - 1] = INF;
        heapDecreaseKey(array, top - 1, a);
    }

    public static int extractMin(int[] array){
        if(top < 1)
            return INF;
        else {
            int min = array[0];
            array[0] = array[top - 1];
            top--;
            minHeapify(array, 0);
            return min;
        }
    }

    public static void heapDecreaseKey(int[] array, int index, int replacement){
        array[index] = replacement;

        while(index > 0 && array[(index + 1) / 2 - 1] > array[index]){
            int t = array[index];
            array[index] = array[(index + 1) / 2 - 1];
            array[(index + 1) / 2 - 1] = t;
            index = (index + 1) / 2 - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

        int array[] = new int[1000000];
        top = 0;

        ArrayList<String> input = new ArrayList<String>();

        for(int i = 0; i <= n; i++){
            input.add(scanner.nextLine());
        }

        input.remove(0);

        for(int i = 0; i < n; i++){
            String str = input.get(i);
            char c = input.get(i).charAt(0);

            if(!str.equals("X"))
                str = str.substring(2);

            switch(c){
                case 'X':
                    int res = extractMin(array);
                    writer.write((res == INF ? "*" : (res + "")) + "\n");
                    break;
                case 'A':
                    int a = Integer.parseInt(str);
                    insert(array, a);
                    break;
                case 'D':
                    int indexOfString = Integer.parseInt(str.substring(0, str.indexOf(' ')));
                    int number = Integer.parseInt(str.substring(str.indexOf(' ') + 1));
                    String command = input.get(indexOfString - 1);
                    int needToBeReplaced = Integer.parseInt(command.substring(2));
                    int index = 0;

                    for(int j = 0; j < top; j++)
                        if(array[j] == needToBeReplaced)
                            index = j;

                    heapDecreaseKey(array, index, number);
                    break;
                default:
                    break;
            }
        }

        scanner.close();
        writer.close();
    }
}
