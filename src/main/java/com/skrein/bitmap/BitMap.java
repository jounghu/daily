package com.skrein.bitmap;

import java.util.List;

/**
 * @author :hujiansong
 * @date :2019/7/1 9:54
 * @since :1.8
 */
public class BitMap {

    private long[] words;

    private int size;

    public BitMap(int size) {
        this.size = size;
        this.words = new long[(getWordIndex(size - 1) + 1)];
    }

    private int getWordIndex(int i) {
        return i >> 6;
    }

    public boolean getBit(int bitIndex) {
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public void setBit(int bitIndex) {
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    public int getValueSize() {
        int count = 0;
        for (long word : this.words) {
            while (word != 0) {
                word = word & (word - 1);
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        BitMap manBitMap = new BitMap(128);
        manBitMap.setBit(1);
        manBitMap.setBit(2);
        manBitMap.setBit(126);

        BitMap phoneBitMap = new BitMap(128);
        phoneBitMap.setBit(1);
        phoneBitMap.setBit(2);
        phoneBitMap.setBit(125);


        int valueSize = manBitMap.getValueSize();
        System.out.println(valueSize);

    }
}
