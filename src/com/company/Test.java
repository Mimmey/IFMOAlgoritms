package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Test {
    public static void generate(BufferedWriter writer) throws IOException {
        Random random = new Random(Math.round(Math.random() * 100000));
        for (int i = 0; i < 200000; i++) {
            int c = random.nextInt(3);
            switch (c) {
                case 0:
                    writer.write("A ");
                    break;
                case 1:
                    writer.write("D ");
                    break;
                case 2:
                    writer.write("C ");
                    break;
            }

            int number = random.nextInt(5);
            writer.write(number + "\n");
        }
    }
}
