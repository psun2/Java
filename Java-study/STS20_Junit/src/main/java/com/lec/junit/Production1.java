package com.lec.junit;

import lombok.Data;

import java.util.Arrays;

@Data
public class Production1 {


    public void sortArry(int[] array) {
        Arrays.sort(array); // 오름 차순 정렬 => 3, 4, 5
        // Arrays.sort(array,Collections.reverseOrder()); // 내림 차순 정렬 => 5, 4 , 3
    }
}
