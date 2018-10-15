package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.Dates.nthDayIn;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class DatesTest {

    @Test
    void fathersDay() {
        LocalDate fathersDay = nthDayIn(2019, JUNE, 2, SUNDAY);

        assertEquals(fathersDay, LocalDate.of(2019, JUNE, 9));
    }

    @Test
    void mothersDay() {
        LocalDate fathersDay = nthDayIn(2019, MAY, 2, SUNDAY);

        assertEquals(fathersDay, LocalDate.of(2019, MAY, 12));
    }

}