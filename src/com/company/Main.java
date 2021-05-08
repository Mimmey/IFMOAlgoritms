package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static class Element {
        int key;
        int parent;
        int leftChild;
        int rightChild;
        int height;
        int balance;
        int indexInOutput;

        public Element(int key, int parent) {
            this.key = key;
            this.parent = parent;
            leftChild = -1;
            rightChild = -1;
            height = 0;
            balance = 0;
            indexInOutput = 0;
        }

        public Element(int key, int leftChild, int rightChild) {
            this.key = key;
            parent = -1;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            height = 0;
            balance = 0;
            indexInOutput = 0;
        }
    }

    public static class Parent {
        int index;
        boolean isRightChild;

        public Parent(int index, boolean isRightChild) {
            this.index = index;
            this.isRightChild = isRightChild;
        }
    }

    public static Scanner scanner;
    public static Element[] elements;
    public static Element[] sortedElements;
    public static BufferedWriter writer;
    public static int root;
    public static int tailElements;
    public static int N = 200002;
    public static int tailSortedElements = 1;

    public static void balanceTree(int index) {
        if (index != -1) {
            if (elements[index].balance > 1 && elements[elements[index].rightChild].balance == -1) {
                bigLeftRotate(index);
            } else if (elements[index].balance > 1) {
                smallLeftRotate(index);
            } else if (elements[index].balance < -1 && elements[elements[index].leftChild].balance == 1) {
                bigRightRotate(index);
            } else if (elements[index].balance < -1) {
                smallRightRotate(index);
            }

            setParents(root);
            balanceTree(elements[index].parent);
        }
    }

    public static void bigLeftRotate(int index) {
        int rightChild = elements[index].rightChild;
        int leftChildOfRightChild = elements[rightChild].leftChild;
        int leftChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].leftChild;
        int rightChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].rightChild;

        elements[leftChildOfRightChild].leftChild = index;
        elements[leftChildOfRightChild].rightChild = rightChild;
        elements[index].rightChild = leftChildOfLeftChildOfRightChild;
        elements[rightChild].leftChild = rightChildOfLeftChildOfRightChild;
        if (index == root) {
            root = leftChildOfRightChild;
        } else {
            if (elements[elements[index].parent].rightChild == index) {
                changeChildrenOfParent(new Parent(elements[index].parent, true), leftChildOfRightChild);
            } else {
                changeChildrenOfParent(new Parent(elements[index].parent, false), leftChildOfRightChild);
            }
        }

        setParents(root);
        setHeightAndBalance(root);

        if (elements[leftChildOfRightChild].balance == -1) {
            elements[index].balance = 0;
            elements[rightChild].balance = 1;
            elements[leftChildOfRightChild].balance = 0;
        } else if (elements[leftChildOfRightChild].balance == 0) {
            elements[index].balance = 0;
            elements[rightChild].balance = 0;
            elements[leftChildOfRightChild].balance = 0;
        } else if (elements[leftChildOfRightChild].balance == 1) {
            elements[index].balance = -1;
            elements[rightChild].balance = 0;
            elements[leftChildOfRightChild].balance = 0;
        }
    }

    public static void bigRightRotate(int index) {
        int leftChild = elements[index].leftChild;
        int rightChildOfLeftChild = elements[leftChild].rightChild;
        int leftChildOfRightChildOfLeftChild = elements[rightChildOfLeftChild].leftChild;
        int rightChildOfRightChildOfLeftChild = elements[rightChildOfLeftChild].rightChild;

        elements[rightChildOfLeftChild].rightChild = index;
        elements[rightChildOfLeftChild].leftChild = leftChild;
        elements[index].leftChild = rightChildOfRightChildOfLeftChild;
        elements[leftChild].rightChild = leftChildOfRightChildOfLeftChild;

        if (index == root) {
            root = rightChildOfLeftChild;
        } else {
            if (elements[elements[index].parent].rightChild == index) {
                changeChildrenOfParent(new Parent(elements[index].parent, true), rightChildOfLeftChild);
            } else {
                changeChildrenOfParent(new Parent(elements[index].parent, false), rightChildOfLeftChild);
            }
        }

        setParents(root);
        setHeightAndBalance(root);

        if (elements[rightChildOfLeftChild].balance == -1) {
            elements[index].balance = 1;
            elements[leftChild].balance = 0;
            elements[rightChildOfLeftChild].balance = 0;
        } else if (elements[rightChildOfLeftChild].balance == 0) {
            elements[index].balance = 0;
            elements[leftChild].balance = 0;
            elements[rightChildOfLeftChild].balance = 0;
        } else if (elements[rightChildOfLeftChild].balance == 1) {
            elements[index].balance = 0;
            elements[leftChild].balance = -1;
            elements[rightChildOfLeftChild].balance = 0;
        }
    }

    public static void changeChildrenOfParent(Parent parent, int index) {
        if (parent.isRightChild) {
            elements[parent.index].rightChild = index;
        } else {
            elements[parent.index].leftChild = index;
        }
    }

    public static Parent findParent(int key, int location) {
        Element locationElement = elements[location];

        if (tailElements == 0) {
            return new Parent(-1, true);
        }

        if (locationElement.key == key) {
            return null;
        }

        if (locationElement.key < key && locationElement.rightChild != -1) {
            return findParent(key, locationElement.rightChild);
        } else if (locationElement.key < key) {
            return new Parent(location, true);
        }

        if (locationElement.leftChild != -1) {
            return findParent(key, locationElement.leftChild);
        } else {
            return new Parent(location, false);
        }
    }

    public static void insertElement(int key) {
        Parent parent = findParent(key, root);

        if (parent != null) {
            elements[tailElements] = new Element(key, parent.index);
            tailElements++;

            if (parent.index != -1) {
                if (parent.isRightChild) {
                    elements[parent.index].rightChild = tailElements - 1;
                } else {
                    elements[parent.index].leftChild = tailElements - 1;
                }
            }

            setParents(root);
            setHeightAndBalance(root);
            balanceTree(tailElements - 1);
        }
    }

    public static void printElements(int index) throws IOException {
        while (index < tailElements + 1) {
            writer.write(sortedElements[index].key + " ");
            if (sortedElements[index].leftChild == -1) {
                writer.write(0 + "");
            } else {
                writer.write(elements[sortedElements[index].leftChild].indexInOutput + "");
            }

            writer.write(" ");

            if (sortedElements[index].rightChild == -1) {
                writer.write(0 + " ");
            } else {
                writer.write(elements[sortedElements[index].rightChild].indexInOutput + "");
            }

            writer.write("\n");
            index++;
        }
    }

    public static void scanElements() {
        for (int i = 0; i < tailElements; i++) {
            elements[i] = new Element(scanner.nextInt(), scanner.nextInt() - 1, scanner.nextInt() - 1);
        }
    }

    public static void smallLeftRotate(int index) {
        int rightChild = elements[index].rightChild;

        elements[index].rightChild = elements[rightChild].leftChild;
        elements[rightChild].leftChild = index;

        if (index == root) {
            root = rightChild;
        } else {
            if (elements[elements[index].parent].rightChild == index) {
                changeChildrenOfParent(new Parent(elements[index].parent, true), rightChild);
            } else {
                changeChildrenOfParent(new Parent(elements[index].parent, false), rightChild);
            }
        }

        setParents(root);
        setHeightAndBalance(root);

        if (elements[rightChild].balance == 1) {
            elements[index].balance = 0;
            elements[rightChild].balance = 0;
        } else if (elements[rightChild].balance == 0) {
            elements[index].balance = 1;
            elements[rightChild].balance = -1;
        }
    }

    public static void smallRightRotate(int index) {
        int leftChild = elements[index].leftChild;

        elements[index].leftChild = elements[leftChild].rightChild;
        elements[leftChild].rightChild = index;

        if (index == root) {
            root = leftChild;
        } else {
            if (elements[elements[index].parent].rightChild == index) {
                changeChildrenOfParent(new Parent(elements[index].parent, true), leftChild);
            } else {
                changeChildrenOfParent(new Parent(elements[index].parent, false), leftChild);
            }
        }

        setParents(root);
        setHeightAndBalance(root);

        if (elements[leftChild].balance == -1) {
            elements[index].balance = 0;
            elements[leftChild].balance = 0;
        } else if (elements[leftChild].balance == 0) {
            elements[index].balance = -1;
            elements[leftChild].balance = 1;
        }
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

    public static void setParents(int index) {
        if (index == root) {
            elements[index].parent = -1;
        }

        if (elements[index].rightChild != -1) {
            elements[elements[index].rightChild].parent = index;
            setParents(elements[index].rightChild);
        }
        if (elements[index].leftChild != -1) {
            elements[elements[index].leftChild].parent = index;
            setParents(elements[index].leftChild);
        }
    }

    public static void setIndicesOfOutput(int index) {
        if (index < tailElements + 1) {
            if (index == root) {
                elements[index].indexInOutput = 1;
                sortedElements[tailSortedElements] = elements[index];
                elements[index].indexInOutput = tailSortedElements;
                tailSortedElements++;
            }

            if (elements[index].leftChild != -1) {
                sortedElements[tailSortedElements] = elements[elements[index].leftChild];
                elements[elements[index].leftChild].indexInOutput = tailSortedElements;
                tailSortedElements++;
            }

            if (elements[index].rightChild != -1) {
                sortedElements[tailSortedElements] = elements[elements[index].rightChild];
                elements[elements[index].rightChild].indexInOutput = tailSortedElements;
                tailSortedElements++;
            }

            if (elements[index].leftChild != -1) {
                setIndicesOfOutput(elements[index].leftChild);
            }

            if (elements[index].rightChild != -1) {
                setIndicesOfOutput(elements[index].rightChild);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(new File("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));
        root = 0;

        tailElements = scanner.nextInt();
        elements = new Element[N];
        sortedElements = new Element[N];

        scanElements();

        if (tailElements != 0) {
            setParents(root);
            setHeightAndBalance(root);
        }

        insertElement(scanner.nextInt());

        setIndicesOfOutput(root);
        writer.write(tailElements + "\n");
        printElements(1);

        scanner.close();
        writer.close();
    }
}
