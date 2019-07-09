package com.skrein.heap;

/**
 * @Author: hujiansong
 * @Date: 2019/6/6 14:31
 * @since: 1.8
 */
public class Heap {

    private static int DEFAULT_CAPACITY = 16;

    private Integer[] root = new Integer[DEFAULT_CAPACITY];

    private int size;

    public Heap() {
    }

    public void insert(int num) {
        if (size > root.length) {
            throw new RuntimeException("heap size is full");
        }

        int parentIndex = (int) Math.floor((size - 1) / 2);
        if (root[parentIndex] != null && num > root[parentIndex]) {
            shiftNode(num);
        } else {
            root[size] = num;
        }
        size++;
    }


    public void delete(int num) {
        if (size <= 0) {
            throw new RuntimeException("heap size is 0");
        }

        if (!checkNumInHeap(num)) {
            throw new RuntimeException("heap don't contain num " + num);
        }



    }

    private boolean checkNumInHeap(int num) {
        for (int i = 0; i < size; i++) {
            if (num == root[size]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将该Node shift到root
     *
     * @param num
     */
    private void shiftNode(int num) {
        int curSize = size;
        do {
            int parentIndex = (int) Math.floor((curSize - 1) / 2);
            if (num > root[parentIndex]) {
                root[curSize] = root[parentIndex];
                root[parentIndex] = num;
                curSize = parentIndex;
            } else {
                return;
            }
        } while (curSize > 0);
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(root[i]);
        }
    }


    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(7);
        heap.insert(2);
        heap.insert(5);
        heap.insert(1);
        heap.insert(11);
        heap.print();
    }


}
