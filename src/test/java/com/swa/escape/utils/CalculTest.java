package com.swa.escape.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculTest {

//    public static void main(String[] args) {
//
//        // 대전역 - 유온역 거리 계산
//        System.out.println("true = " + Calcul.haversine(36.332165597, 127.434310227, 36.353707, 127.341349));
//        // 서울역 - 대전역 거리 계산
//        System.out.println("true = " + Calcul.haversine(37.554703, 126.970724, 36.353707, 127.341349));
//
//    }
    @Test
    @DisplayName("거리 계산 테스트")
    public void TestDIST() throws Exception{
        // given
        double lat1 = 36.332165597;
        double lon1 = 127.434310227;
        double lat2 = 36.353707;
        double lon2 = 127.341349;
        // when
        double haversine = Calcul.haversine(lat1, lon1, lat2, lon2);
        boolean ok = (8.5 < haversine && haversine < 9);
        System.out.println("haversine = " + haversine);
        Assertions.assertThat(ok).isEqualTo(true);
        // then
    }
}