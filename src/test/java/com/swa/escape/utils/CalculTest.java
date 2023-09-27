package com.swa.escape.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculTest {

    public static void main(String[] args) {

        // 대전역 - 유온역 거리 계산
        System.out.println("true = " + Calcul.haversine(36.332165597, 127.434310227, 36.353707, 127.341349));
        // 서울역 - 대전역 거리 계산
        System.out.println("true = " + Calcul.haversine(37.554703, 126.970724, 36.353707, 127.341349));

    }

}