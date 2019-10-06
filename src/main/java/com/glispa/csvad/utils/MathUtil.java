package com.glispa.csvad.utils;

public class MathUtil {
    public static int getDistributedAdAmount(int count, int weight) {
        return (int)(Math.ceil(count * weight/100.0));
    }
}