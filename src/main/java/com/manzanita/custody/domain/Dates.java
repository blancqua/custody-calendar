package com.manzanita.custody.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public final class Dates {

    private Dates() {
    }

    public static LocalDate nthDayIn(int year, Month month, int n, DayOfWeek dayOfWeek) {
        int occurences = 0;
        LocalDate current = LocalDate.of(year, month, 1).minusDays(1);
        while (occurences < n) {
            current = current.plusDays(1);
            if (dayOfWeek.equals(current.getDayOfWeek())) {
                occurences++;
            }
        }
        return current;
    }

}
