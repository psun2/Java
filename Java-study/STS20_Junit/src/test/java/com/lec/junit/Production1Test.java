package com.lec.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Production1Test {

    @Test
    @DisplayName("Production1")
    void test() {
        int[][] arr = { //
                { 40, 20, 10, 30 }, //
                { 4, 5, 6, 2, 1, 0 }, //
                { 400, 300, 200 }, //
                { 50 }, //
                {} //
        };

        int[][] expected = { // 결과, 기대값
                { 10, 20, 30, 40 }, //
                { 0, 1, 2, 4, 5, 6 }, //
                { 200, 300, 400 }, //
                { 50 }, //
                {} //
        };

        Production1 p1 = new Production1();

        for (int[] array : arr) {
            p1.sortArry(array); // arr 정렬
        }

        assertArrayEquals(arr, expected);

        Assertions.assertThat(arr).isEqualTo(expected);

    }

}