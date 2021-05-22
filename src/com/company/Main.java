package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        private String value;
        private Integer prev;
        private Integer next;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getPrev() {
            return prev;
        }

        public void setPrev(Integer prev) {
            this.prev = prev;
        }

        public Integer getNext() {
            return next;
        }

        public void setNext(Integer next) {
            this.next = next;
        }

        public Node(String value, Integer prev, Integer next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    static class NodeList {
        private Node[] list;
        private int head;
        private int size;
        private int current;

        public NodeList() {
            list = new Node[500000];
            head = 0;
            size = 0;
        }

        public int getHead() {
            return head;
        }

        public void set(int key, String value) {
            list[key].setValue(value);
        }

        public void add(String value) {
            Integer prev = null;
            if (head > 0) {
                prev = head - 1;
                list[head - 1].setNext(head);
            }
            list[head] = new Node(value, prev, null);
            head++;
            size++;
        }

        public void remove(int index) {
            Node removed = list[index];
            if (!(removed.getNext() == null && removed.getPrev() == null)) {
                if(removed.getPrev() == null) {
                    list[removed.getNext()].setPrev(list[index].getPrev());
                } else if (removed.getNext() == null) {
                    list[removed.getPrev()].setNext(list[index].getNext());
                } else {
                    list[removed.getNext()].setPrev(list[index].getPrev());
                    list[removed.getPrev()].setNext(list[index].getNext());
                }
            }

            list[index] = null;
            size--;

            int rightHead = -1;
            for (int i = 0; i < head; i++) {
                if (list[i] != null) {
                    rightHead = i;
                }
            }

            head = rightHead + 1;
        }

        public String get(int index) {
            if(list[index] == null) {
                return null;
            }

            return list[index].getValue();
        }

        public String getPrev(int index) {
            if (list[index].prev == null) {
                return null;
            }

            return list[list[index].prev].value;
        }

        public String getNext(int index) {
            if (list[index].getNext() == null) {
                return null;
            }

            return list[list[index].getNext()].getValue();
        }

//        public void optimise(Hashtable hashtable) {
//            int first = -1;
//            for (int i = 0; i < head; i++) {
//                if (list[i] != null) {
//                    first = i;
//                    break;
//                }
//            }
//
//            if (first != -1) {
//                for (int i = 0; i < head; i++) {
//                    list[i] = list[i + first];
//                }
//
//                head -= first;
//
//                Set<String> keys = hashtable.keySet();
//                Iterator<String> itr = keys.iterator();
//                String str;
//
//                while (itr.hasNext()) {
//                    str = itr.next();
//                    hashtable.put(str, (Integer) hashtable.get(str) - first);
//                }
//            }
//        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        NodeList list = new NodeList();
        Hashtable hashtable = new Hashtable<String, Integer>();
//        TestTask testTask = new TestTask();
//        testTask.generate(writer);
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String[] arr = scanner.nextLine().split(" ");
            String key = arr[1];
            Integer index = (Integer) hashtable.get(key);
            switch (arr[0]) {
                case "put":
                    String value = arr[2];
                    if (index != null) {
                        list.set(index, value);
                    } else {
                        hashtable.put(key, list.getHead());
                        list.add(value);
                    }
                    break;
                case "get":
                    if (index != null) {
                        writer.write(list.get((int) hashtable.get(key)) + "\n");
                    } else {
                        writer.write("<none>\n");
                    }
                    break;
                case "prev":
                    if (index != null && list.getPrev(index) != null) {
                        writer.write(list.getPrev(index) + "\n");
                    } else {
                        writer.write("<none>\n");
                    } break;
                case "next":
                    if (index != null &&  list.getNext(index) != null) {
                        writer.write(list.getNext(index) + "\n");
                    } else {
                        writer.write("<none>\n");
                    }
                    break;
                case "delete":
                    if (index != null) {
                        hashtable.remove(key);
                        list.remove(index);
                    }
                    break;
                default:
                    throw new Exception();
            }
        }
        scanner.close();
        writer.close();
    }
}
