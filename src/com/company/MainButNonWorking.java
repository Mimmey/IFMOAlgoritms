//package com.company;
//
//import java.io.*;
//import java.util.Scanner;
//
//public class Main {
//    public static class Element {
//        int key;
//        Parent parent;
//        int leftChild;
//        int rightChild;
//        int height;
//        int balance;
//        int indexInOutput;
//
//        public Element(int key, Parent parent) {
//            this.key = key;
//            this.parent = parent;
//            leftChild = -1;
//            rightChild = -1;
//            height = 0;
//            balance = 0;
//            indexInOutput = 0;
//        }
//
//        public Element(int key, int leftChild, int rightChild) {
//            this.key = key;
//            parent = new Parent(-1, false);
//            this.leftChild = leftChild;
//            this.rightChild = rightChild;
//            height = 0;
//            balance = 0;
//            indexInOutput = 0;
//        }
//    }
//
//    public static class Parent {
//        int index;
//        boolean isRightChild;
//
//        public Parent(int index, boolean isRightChild) {
//            this.index = index;
//            this.isRightChild = isRightChild;
//        }
//    }
//
//    public static Scanner scanner;
//    public static Element[] elements;
//    public static Element[] sortedElements;
//    public static BufferedWriter writer;
//    public static int root;
//    public static int tailElements;
//    public static int N = 200002;
//    public static int tailSortedElements = 1;
//
//    public static void balanceTree(int index) {
//        if (index != -1) {
//            if (elements[index].balance > 1 && elements[elements[index].rightChild].balance == -1) {
//                bigLeftRotate(index);
//            } else if (elements[index].balance > 1) {
//                smallLeftRotate(index);
//            } else if (elements[index].balance < -1 && elements[elements[index].leftChild].balance == 1) {
//                bigRightRotate(index);
//            } else if (elements[index].balance < -1) {
//                smallRightRotate(index);
//            }
//
//            setParents(root);
//            balanceTree(elements[index].parent.index);
//        }
//    }
//
//    public static void bigLeftRotate(int index) {
//        int rightChild = elements[index].rightChild;
//        int leftChildOfRightChild = elements[rightChild].leftChild;
//        int leftChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].leftChild;
//        int rightChildOfLeftChildOfRightChild = elements[leftChildOfRightChild].rightChild;
//
//        elements[leftChildOfRightChild].leftChild = index;
//        elements[leftChildOfRightChild].rightChild = rightChild;
//        elements[index].rightChild = leftChildOfLeftChildOfRightChild;
//        elements[rightChild].leftChild = rightChildOfLeftChildOfRightChild;
//        if (index == root) {
//            root = leftChildOfRightChild;
//        } else {
//            changeChildrenOfParent(elements[index].parent, leftChildOfRightChild);
//        }
//
//        setParents(root);
//        setHeightAndBalance(root);
//
////        if (elements[leftChildOfRightChild].balance == -1) {
////            elements[index].balance = 0;
////            elements[rightChild].balance = 1;
////            elements[leftChildOfRightChild].balance = 0;
////        } else if (elements[leftChildOfRightChild].balance == 0) {
////            elements[index].balance = 0;
////            elements[rightChild].balance = 0;
////            elements[leftChildOfRightChild].balance = 0;
////        } else if (elements[leftChildOfRightChild].balance == 1) {
////            elements[index].balance = -1;
////            elements[rightChild].balance = 0;
////            elements[leftChildOfRightChild].balance = 0;
////        }
//    }
//
//    public static void bigRightRotate(int index) {
//        int leftChild = elements[index].leftChild;
//        int rightChildOfLeftChild = elements[leftChild].rightChild;
//        int leftChildOfRightChildOfLeftChild = elements[rightChildOfLeftChild].leftChild;
//        int rightChildOfRightChildOfLeftChild = elements[rightChildOfLeftChild].rightChild;
//
//        elements[rightChildOfLeftChild].rightChild = index;
//        elements[rightChildOfLeftChild].leftChild = leftChild;
//        elements[index].leftChild = rightChildOfRightChildOfLeftChild;
//        elements[leftChild].rightChild = leftChildOfRightChildOfLeftChild;
//
//        if (index == root) {
//            root = rightChildOfLeftChild;
//        } else {
//            changeChildrenOfParent(elements[index].parent, rightChildOfLeftChild);
//        }
//
//        setParents(root);
//        setHeightAndBalance(root);
//
////        if (elements[rightChildOfLeftChild].balance == -1) {
////            elements[index].balance = 1;
////            elements[leftChild].balance = 0;
////            elements[rightChildOfLeftChild].balance = 0;
////        } else if (elements[rightChildOfLeftChild].balance == 0) {
////            elements[index].balance = 0;
////            elements[leftChild].balance = 0;
////            elements[rightChildOfLeftChild].balance = 0;
////        } else if (elements[rightChildOfLeftChild].balance == 1) {
////            elements[index].balance = 0;
////            elements[leftChild].balance = -1;
////            elements[rightChildOfLeftChild].balance = 0;
////        }
//    }
//
//    public static void changeChildrenOfParent(Parent parent, int index) {
//        if(parent.index != -1) {
//            if (parent.isRightChild) {
//                elements[parent.index].rightChild = index;
//            } else {
//                elements[parent.index].leftChild = index;
//            }
//        }
//    }
//
//    public static int findIndex(int key, int location) {
//        if (location < 0) {
//            return -1;
//        }
//
//        Element locationElement = elements[location];
//        if (tailElements == 0) {
//            return -1;
//        }
//
//        if (locationElement.key == key) {
//            return location;
//        }
//
//        if (locationElement.key < key && locationElement.rightChild != -1) {
//            return findIndex(key, locationElement.rightChild);
//        }
//
//        if (locationElement.leftChild != -1) {
//            return findIndex(key, locationElement.leftChild);
//        }
//
//        return -1;
//    }
//
//    public static Parent findParent(int key, int location) {
//        if (tailElements == 0 || location == -1) {
//            return new Parent(-1, true);
//        }
//
//        Element locationElement = elements[location];
//
//        if (locationElement.key == key) {
//            return null;
//        }
//
//        if (locationElement.key < key && locationElement.rightChild != -1) {
//            return findParent(key, locationElement.rightChild);
//        } else if (locationElement.key < key) {
//            return new Parent(location, true);
//        }
//
//        if (locationElement.leftChild != -1) {
//            return findParent(key, locationElement.leftChild);
//        } else {
//            return new Parent(location, false);
//        }
//    }
//
//    public static int findRighterFromLeftSubtree(int index) {
//        index = elements[index].leftChild;
//        while (elements[index].rightChild != -1) {
//            index = elements[index].rightChild;
//        }
//        return index;
//    }
//
//    public static void insertElement(int key) throws IOException {
//        Parent parent = findParent(key, root);
//
//        if (parent != null) {
//            elements[tailElements] = new Element(key, parent);
//            tailElements++;
//
//            if (parent.index != -1) {
//                if (parent.isRightChild) {
//                    elements[parent.index].rightChild = tailElements - 1;
//                } else {
//                    elements[parent.index].leftChild = tailElements - 1;
//                }
//            } else if (tailElements == 1){
//                root = 0;
//            }
//
//            setParents(root);
//            setHeightAndBalance(root);
//            balanceTree(tailElements - 1);
//        }
//    }
//
//    public static void printElements(int index) throws IOException {
//        while (index < tailSortedElements) {
//            writer.write(sortedElements[index].key + " ");
//            if (sortedElements[index].leftChild == -1) {
//                writer.write(0 + "");
//            } else {
//                writer.write(elements[sortedElements[index].leftChild].indexInOutput + "");
//            }
//
//            writer.write(" ");
//
//            if (sortedElements[index].rightChild == -1) {
//                writer.write(0 + " ");
//            } else {
//                writer.write(elements[sortedElements[index].rightChild].indexInOutput + "");
//            }
//
//            writer.write("\n");
//            index++;
//        }
//    }
//
//    public static void removeElement(int key) {
//        int index = findIndex(key, root);
//        if (index != -1) {
//            tailSortedElements--;
//            Parent starter = elements[index].parent;
//            if (elements[index].leftChild == -1 && elements[index].rightChild == -1) {
//                if (index != root) {
//                    if (elements[index].parent.isRightChild) {
//                        elements[elements[index].parent.index].rightChild = -1;
//                    } else {
//                        elements[elements[index].parent.index].leftChild = -1;
//                    }
//                    shiftFromIndex(index);
//                    setHeightAndBalance(root);
//                    if (starter.index != -1 && elements[starter.index].rightChild != -1 && starter.isRightChild) {
//                        balanceTree(elements[starter.index].rightChild);
//                    } else if (starter.index != -1 && elements[starter.index].leftChild != -1 && starter.isRightChild) {
//                        balanceTree(elements[starter.index].leftChild);
//                    } else if (starter.index != -1) {
//                        balanceTree(index);
//                    } else {
//                        balanceTree(root);
//                    }
//                    balanceTree(starter.index);
//                } else {
//                    tailElements--;
//                    root = -1;
//                }
//            } else if (elements[index].leftChild != -1 && elements[index].rightChild != -1) {
//                int remover = findRighterFromLeftSubtree(index);
//                starter = elements[remover].parent;
//                elements[index].key = elements[remover].key;
//
//                if (elements[remover].leftChild != -1 && elements[remover].parent.isRightChild) {
//                    elements[elements[remover].parent.index].rightChild = elements[remover].leftChild;
//                } else if (elements[remover].leftChild != -1 && !(elements[remover].parent.isRightChild)) {
//                    elements[elements[remover].parent.index].leftChild = elements[remover].leftChild;
//                } else {
//                    if (elements[remover].parent.isRightChild) {
//                        elements[elements[remover].parent.index].rightChild = -1;
//                    } else {
//                        elements[elements[remover].parent.index].leftChild = -1;
//                    }
//                }
//
//                int temp = index;
//                while (elements[temp].leftChild != -1 && elements[elements[temp].leftChild].key > elements[temp].key) {
//                    int temp2 = elements[elements[temp].leftChild].key;
//                    elements[elements[temp].leftChild].key = elements[temp].key;
//                    elements[temp].key = temp2;
//                    temp = elements[temp].leftChild;
//                }
//
//                shiftFromIndex(remover);
//                setHeightAndBalance(root);
//                if (starter.index != -1 && elements[starter.index].rightChild != -1 && starter.isRightChild) {
//                    balanceTree(elements[starter.index].rightChild);
//                } else if (starter.index != -1 && elements[starter.index].leftChild != -1 && starter.isRightChild) {
//                    balanceTree(elements[starter.index].leftChild);
//                } else if (starter.index != -1) {
//                    balanceTree(index);
//                } else {
//                    balanceTree(root);
//                }
//                balanceTree(starter.index);
//            } else {
//                int remover;
//                if (elements[index].leftChild != -1) {
//                    remover = elements[index].leftChild;
//                    elements[index].leftChild = -1;
//                } else {
//                    remover = elements[index].rightChild;
//                    elements[index].rightChild = -1;
//                }
//                elements[index].key = elements[remover].key;
//                shiftFromIndex(remover);
//                setHeightAndBalance(root);
//                if (starter.index != -1 && elements[starter.index].rightChild != -1 && starter.isRightChild) {
//                    balanceTree(elements[starter.index].rightChild);
//                } else if (starter.index != -1 && elements[starter.index].leftChild != -1 && starter.isRightChild) {
//                    balanceTree(elements[starter.index].leftChild);
//                } else if (starter.index != -1) {
//                    balanceTree(index);
//                } else {
//                    balanceTree(root);
//                }
//                balanceTree(starter.index);
//            }
//        }
//    }
//
//    public static void scanElements() {
//        for (int i = 0; i < tailElements; i++) {
//            elements[i] = new Element(scanner.nextInt(), scanner.nextInt() - 1, scanner.nextInt() - 1);
//        }
//    }
//
//    public static void shiftFromIndex(int index) {
//        Element before = elements[index];
//        elements[index] = elements[tailElements - 1];
//        if ((tailElements - 1 != index) && index != elements[index].leftChild && index != elements[index].rightChild) {
//            if (elements[tailElements - 1].parent.index != -1) {
//                if (elements[tailElements - 1].parent.isRightChild) {
//                    elements[elements[tailElements - 1].parent.index].rightChild = index;
//                } else {
//                    elements[elements[tailElements - 1].parent.index].leftChild = index;
//                }
//            }
//        }
//
//        if (elements[index].parent.index == index) {
//            if (elements[index].parent.isRightChild) {
//                elements[index].rightChild = -1;
//            } else {
//                elements[index].leftChild = -1;
//            }
//            elements[index].parent = before.parent;
//            if (before.parent.isRightChild) {
//                elements[before.parent.index].rightChild = index;
//            } else {
//                elements[before.parent.index].leftChild = index;
//            }
//        }
//
//        if (tailElements - 1 == root) {
//            root = index;
//        }
//
//        tailElements--;
//        setParents(root);
//    }
//
//    public static void smallLeftRotate(int index) {
//        int rightChild = elements[index].rightChild;
//
//        elements[index].rightChild = elements[rightChild].leftChild;
//        elements[rightChild].leftChild = index;
//
//        if (index == root) {
//            root = rightChild;
//        } else {
//            changeChildrenOfParent(elements[index].parent, rightChild);
//        }
//
//        setParents(root);
//        setHeightAndBalance(root);
//
////        if (elements[rightChild].balance == 1) {
////            elements[index].balance = 0;
////            elements[rightChild].balance = 0;
////        } else if (elements[rightChild].balance == 0) {
////            elements[index].balance = 1;
////            elements[rightChild].balance = -1;
////        }
//    }
//
//    public static void smallRightRotate(int index) {
//        int leftChild = elements[index].leftChild;
//
//        elements[index].leftChild = elements[leftChild].rightChild;
//        elements[leftChild].rightChild = index;
//
//        if (index == root) {
//            root = leftChild;
//        } else {
//            changeChildrenOfParent(elements[index].parent, leftChild);
//        }
//
//        setParents(root);
//        setHeightAndBalance(root);
//
////        if (elements[leftChild].balance == -1) {
////            elements[index].balance = 0;
////            elements[leftChild].balance = 0;
////        } else if (elements[leftChild].balance == 0) {
////            elements[index].balance = -1;
////            elements[leftChild].balance = 1;
////        }
//    }
//
//    public static void setHeightAndBalance(int index) {
//        if (elements[index].rightChild == -1 && elements[index].leftChild == -1) {
//            elements[index].height = 0;
//            elements[index].balance = 0;
//        } else if (elements[index].rightChild == -1) {
//            setHeightAndBalance(elements[index].leftChild);
//            int leftChildHeight = elements[elements[index].leftChild].height;
//            elements[index].height = leftChildHeight + 1;
//            elements[index].balance = -1 - leftChildHeight;
//        } else if (elements[index].leftChild == -1) {
//            setHeightAndBalance(elements[index].rightChild);
//            int rightChildHeight = elements[elements[index].rightChild].height;
//            elements[index].height = rightChildHeight + 1;
//            elements[index].balance = rightChildHeight + 1;
//        } else {
//            setHeightAndBalance(elements[index].leftChild);
//            setHeightAndBalance(elements[index].rightChild);
//            int leftChildHeight = elements[elements[index].leftChild].height;
//            int rightChildHeight = elements[elements[index].rightChild].height;
//            elements[index].height = Math.max(leftChildHeight, rightChildHeight) + 1;
//            elements[index].balance = rightChildHeight - leftChildHeight;
//        }
//    }
//
//    public static void setParents(int index) {
//        if (root != -1) {
//            if (index == root) {
//                elements[index].parent = new Parent(-1, false);
//            }
//
//            if (elements[index].rightChild != -1) {
//                elements[elements[index].rightChild].parent = new Parent(index, true);
//                setParents(elements[index].rightChild);
//            }
//            if (elements[index].leftChild != -1) {
//                elements[elements[index].leftChild].parent = new Parent(index, false);
//                setParents(elements[index].leftChild);
//            }
//        }
//    }
//
//    public static void setIndicesOfOutput(int index) {
//        if (index < tailElements) {
//            if (index == root) {
//                tailSortedElements = 1;
//                elements[index].indexInOutput = tailSortedElements;
//                sortedElements[tailSortedElements] = elements[index];
//                elements[index].indexInOutput = tailSortedElements;
//                tailSortedElements++;
////            } else {
////                elements[index].indexInOutput = tailSortedElements;
////                sortedElements[tailSortedElements] = elements[index];
////                elements[index].indexInOutput = tailSortedElements;
////                tailSortedElements++;
//            }
//
//            if (elements[index].leftChild != -1) {
//                sortedElements[tailSortedElements] = elements[elements[index].leftChild];
//                elements[elements[index].leftChild].indexInOutput = tailSortedElements;
//                tailSortedElements++;
//            }
//
//            if (elements[index].rightChild != -1) {
//                sortedElements[tailSortedElements] = elements[elements[index].rightChild];
//                elements[elements[index].rightChild].indexInOutput = tailSortedElements;
//                tailSortedElements++;
//            }
//
//            if (elements[index].leftChild != -1) {
//                setIndicesOfOutput(elements[index].leftChild);
//            }
//
//            if (elements[index].rightChild != -1) {
//                setIndicesOfOutput(elements[index].rightChild);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        scanner = new Scanner(new File("input.txt"));
//        writer = new BufferedWriter(new FileWriter("output.txt"));
////        Test.generate(writer);
//        root = -1;
//
//        tailElements = 0;
//        elements = new Element[N];
//        sortedElements = new Element[N];
//
////        scanElements();
////
////        if (tailElements != 0) {
////            setParents(root);
////            setHeightAndBalance(root);
////        }
//
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; i++) {
//            String input = scanner.nextLine();
//            char operation = input.charAt(0);
//            int argument = Integer.parseInt(input.substring(2));
//
//            switch (operation) {
//                case 'A':
//                    insertElement(argument);
//                    if (root >= 0) {
//                        writer.write(elements[root].balance + "\n");
//                    } else {
//                        writer.write("0\n");
//                    }
////                    writer.write("Iteration " + (i + 2) + ": Tree:\n");
////                    if (tailElements > 0) {
////                        setIndicesOfOutput(root);
////                        printElements(1);
////                    }
////                    writer.write("End tree\n");
//                    break;
//                case 'D':
//                    removeElement(argument);
//                    if (root >= 0) {
//                        writer.write(elements[root].balance + "\n");
//                    } else {
//                        writer.write("0\n");
//                    }
////                    writer.write("Iteration " + (i + 2) + ": Tree:\n");
////                    if (tailElements > 0) {
////                        setIndicesOfOutput(root);
////                        printElements(1);
////                    }
////                    writer.write("End tree\n");
//                    break;
//                case 'C':
//                    writer.write((findIndex(argument, root) == -1 ? 'N' : 'Y') + "\n");
//                    break;
//            }
//
//        }
////        writer.write(tailElements + "\n");
////        if (tailElements > 0) {
////            setIndicesOfOutput(root);
////            printElements(1);
////        }
//
//        scanner.close();
//        writer.close();
//    }
//}
