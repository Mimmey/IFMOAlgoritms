package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class TestTask {
    public static void generate(BufferedWriter writer) throws IOException {
        Random random = new Random(Math.round(Math.random() * 100000));
        for (int i = 0; i < 100000; i++) {
            int c = random.nextInt(5);
            switch (c) {
                case 0:
                    writer.write("get ");
                    break;
                case 1:
                    writer.write("prev ");
                    break;
                case 2:
                    writer.write("next ");
                    break;
                case 3:
                    writer.write("put ");
                    break;
                case 4:
                    writer.write("delete ");
                    break;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 2; j++) {
                int number = random.nextInt(2);
                sb.append((char) (number + 'a'));
            }

            writer.write(sb + "");

            if (c == 3) {
                int number = random.nextInt(26);
                char arg = (char)(number + 'a');
                writer.write(" " + arg);
            }

            writer.write("\n");
        }
    }
}
