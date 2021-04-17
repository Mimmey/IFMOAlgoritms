package com.company;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    public static int top;
    public static final int INF = -2147483648;

    public static void minHeapify(int[][] array, int index){

        while(true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int indMin = index;

            if (left < top && array[left][0] < array[indMin][0])
                indMin = left;

            if (right < top && array[right][0] < array[indMin][0])
                indMin = right;

            if(indMin == index)
                break;
            else {
                int t = array[indMin][0];
                int t2 = array[indMin][1];
                array[indMin][0] = array[index][0];
                array[indMin][1] = array[index][1];
                array[index][0] = t;
                array[index][1] = t2;
                index = indMin;
            }
        }
    }

    public static void insert(int[][] array, int a, int i){
        top++;
        array[top - 1][0] = INF;
        array[top - 1][1] = i;
        heapDecreaseKey(array, top - 1, a);
    }

    public static int extractMin(int[][] array){
        if(top < 1)
            return INF;
        else {
            int min = array[0][0];
            array[0][0] = array[top - 1][0];
            array[0][1] = array[top - 1][1];
            top--;
            minHeapify(array, 0);
            return min;
        }
    }

    public static void heapDecreaseKey(int[][] array, int index, int replacement){
        array[index][0] = replacement;

        while(index > 0 && array[(index - 1) / 2][0] > array[index][0]){
            int t = array[index][0];
            int t2 = array[index][1];
            array[index][0] = array[(index - 1) / 2][0];
            array[index][1] = array[(index - 1) / 2][1];
            array[(index - 1) / 2][0] = t;
            array[(index - 1) / 2][1] = t2;
            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        int n;
        n = scanner.nextInt();

        int array[][] = new int[1000000][2];
        top = 0;

//        ArrayList<String> input = new ArrayList<String>();
//        String[] input = new String[1000000];
        scanner.nextLine();

//        for(int i = 0; i < n; i++){
//            input.add(scanner.nextLine());
//            input[i] = scanner.nextLine();
//        }

//        input.remove(0);

        for(int i = 0; i < n; i++){
//            String str = input.get(i);
//            String str = input[i];
            String str = scanner.nextLine();
            char c = str.charAt(0);

            if(!str.equals("X"))
                str = str.substring(2);

            switch(c){
                case 'X':
                    int res = extractMin(array);
                    writer.write((res == INF ? "*" : (res + "")) + "\n");
                    break;
                case 'A':
                    int a = Integer.parseInt(str);
                    insert(array, a, i);
                    break;
                case 'D':
                    int indexOfString = Integer.parseInt(str.substring(0, str.indexOf(' ')));
                    int number = Integer.parseInt(str.substring(str.indexOf(' ') + 1));
                    indexOfString--;
                    int index = 0;
                    for(int j = 0; j < top; j++)
                        if(array[j][1] == indexOfString) {
                            index = j;
                            break;
                        }

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
