package com.api.springdiscoveryservice;

import java.time.LocalDateTime;

public class DateLibraryTest {
    public static void main(String[] args) {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        System.out.println("어제 : " + yesterday);
        System.out.println("오늘 : " + today);
        System.out.println("내일 : " + tomorrow);
    }
}
