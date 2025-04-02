package com.datastructure.demo.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {
    // 基數排序 最低有效數字 LSD (Least significant digit)
    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];
        phoneNumbers[0] = "13812345678";
        phoneNumbers[1] = "13512345678";
        phoneNumbers[2] = "13612345678";
        phoneNumbers[3] = "13912345678";
        phoneNumbers[4] = "13712345678";
        phoneNumbers[5] = "13212345678";
        phoneNumbers[6] = "13012345678";
        phoneNumbers[7] = "13112345678";
        phoneNumbers[8] = "13412345678";
        phoneNumbers[9] = "13312345678";

//        String[] phoneNumbers = new String[6];
//        phoneNumbers[0] = "158";
//        phoneNumbers[1] = "151";
//        phoneNumbers[2] = "135";
//        phoneNumbers[3] = "138";
//        phoneNumbers[4] = "139";
//        phoneNumbers[5] = "157";
        RadixSort.radixSort(phoneNumbers, 11);
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }

    }

    private static void radixSort(String[] phoneNumbers, int length) {
        ArrayList<String>[] buckets = new ArrayList[128];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i = 2; i >= 0; i--) {
            // '0' -> 48 '1' -> 49 個位數 十位 ...
            for (String s : phoneNumbers) {
                buckets[s.charAt(i) - '0'].add(s);
            }
            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    phoneNumbers[k++] = s;
                }
                bucket.clear();
            }
            System.out.println(Arrays.toString(phoneNumbers));
        }
    }
}
