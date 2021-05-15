package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    enum Side {
        RIGHT, LEFT;
    };

    public static class Element {
        int key;
        Element[] children;
        int height;

        public Element(int key, Element left, Element right) {
            children = new Element[2];
            children[0] = left;
            children[1] = right;
            this.key = key;
            this.height = 1;
        }
    }

    public static class Tree {
        Element root = null;

        public int getBalance(Element element) {
            if (element == null) {
                return 0;
            }

            return (element.children[1] != null ? element.children[1].height : 0) -
                    (element.children[0] != null ? element.children[0].height : 0);
        }

        public void setHeight(Element element) {
            element.height = Math.max(getLeftHeight(element), getRightHeight(element)) + 1;
        }

        public int getRightHeight(Element element) {
            return (element.children[1] == null ? 0 : element.children[1].height);
        }

        public int getLeftHeight(Element element) {
            return (element.children[0] == null ? 0 : element.children[0].height);
        }

        public Element rotate(Element element, Side side) {
            Element rotated = element.children[side == Side.LEFT ? 1 : 0];
            element.children[side == Side.LEFT ? 1 : 0] = rotated.children[side == Side.LEFT ? 0 : 1];
            rotated.children[side == Side.LEFT ? 0 : 1] = element;
            setHeight(element);
            setHeight(rotated);
            return rotated;
        }

        public Element setBalance(Element element) {
            setHeight(element);
            if (getBalance(element) > 1) {
                if (getBalance(element.children[1]) < 0) {
                    element.children[1] = rotate(element.children[1], Side.RIGHT);
                }
                return rotate(element, Side.LEFT);
            }

            if (getBalance(element) < -1) {
                if (getBalance(element.children[0]) > 0) {
                    element.children[0] = rotate(element.children[0], Side.LEFT);
                }
                return rotate(element, Side.RIGHT);
            }

            return element;
        }

        Element insert(Element root, int key) {
            if (root == null) {
                return new Element(key, null, null);
            }
            root.children[key < root.key ? 0 : 1] = insert(root.children[key < root.key ? 0 : 1], key);
            return setBalance(root);
        }

        Element findMax(Element root) {
            return root.children[1] == null ? root : findMax(root.children[1]);
        }

        Element remove(Element root, int key) {
            if (root == null) {
                return null;
            }

            if (key != root.key) {
                root.children[key < root.key ? 0 : 1] = remove(root.children[key < root.key ? 0 : 1], key);
            } else {
                if (root.children[0] == null && root.children[1] == null) {
                    return null;
                }

                if (root.children[0] == null) {
                    root = root.children[1];
                    return setBalance(root);
                }

                Element righter = findMax(root.children[0]);
                root.key = righter.key;
                root.children[0] = remove(root.children[0], righter.key);
            }
            return setBalance(root);
        }

        Element search(Element root, int key) {
            if (root == null || key == root.key) {
                return root;
            }
            return search(root.children[key < root.key ? 0 : 1], key);
        }
    }

    public static Scanner scanner;
    public static BufferedWriter writer;
    public static int N = 200002;


    public static void main(String[] args) throws IOException {
        scanner = new Scanner(new File("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));

        int n = scanner.nextInt();
        scanner.nextLine();
        Tree tree = new Tree();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            char operation = input.charAt(0);
            int argument = Integer.parseInt(input.substring(2));

            switch (operation) {
                case 'A':
                    if (tree.search(tree.root, argument) == null) {
                        tree.root = tree.insert(tree.root, argument);
                    }
                    writer.write(tree.getBalance(tree.root) + "\n");
                    break;
                case 'D':
                    if (tree.search(tree.root, argument) != null) {
                        tree.root = tree.remove(tree.root, argument);
                    }
                    writer.write(tree.getBalance(tree.root) + "\n");
                    break;
                case 'C':
                    writer.write((tree.search(tree.root, argument) != null) ? "Y\n" : "N\n");
                    break;
            }

        }

        scanner.close();
        writer.close();
    }
}
