package com.github.wblancqu.custody.application;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.wblancqu.custody.domain.DaySchedule.fullDay;
import static com.github.wblancqu.custody.domain.DaySchedule.transitionDay;
import static com.github.wblancqu.custody.domain.Parent.DAD;
import static com.github.wblancqu.custody.domain.Parent.MOM;
import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LenaCalendarTest {

    private LenaCalendar calendar = LenaCalendar.instance();

    @Test
    void basicRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 1)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 2)), transitionDay(LocalTime.of(17, 30), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 3)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 8)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 9)), transitionDay(LocalTime.of(17, 30), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 10)), fullDay(MOM));
    }

    @Test
    void basicRuleWith53Weeks() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, JANUARY, 2)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, JANUARY, 3)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, JANUARY, 4)), fullDay(DAD));
    }

    @Test
    void summerHolidaysRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 16)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 17)), transitionDay(LocalTime.of(18, 00), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 18)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 25)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 30)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, JULY, 31)), transitionDay(LocalTime.of(18, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, AUGUST, 1)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, AUGUST, 8)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, AUGUST, 13)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, AUGUST, 14)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, AUGUST, 15)), fullDay(MOM));
    }

    @Test
    void christmasRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 23)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 24)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 25)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 26)), transitionDay(LocalTime.of(11, 00), DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 23)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 24)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 25)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 26)), fullDay(MOM));

        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 23)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 24)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 25)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 26)), fullDay(MOM));
    }

    @Test
    void newYearRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 30)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2017, DECEMBER, 31)), transitionDay(LocalTime.of(11, 00), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, JANUARY, 1)), transitionDay(LocalTime.of(18, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, JANUARY, 2)), fullDay(MOM));

        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 30)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2018, DECEMBER, 31)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 1)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JANUARY, 2)), transitionDay(LocalTime.of(17, 30), DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 30)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, DECEMBER, 31)), transitionDay(LocalTime.of(11, 00), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, JANUARY, 1)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, JANUARY, 2)), fullDay(DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2020, DECEMBER, 30)), transitionDay(LocalTime.of(17, 30), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, DECEMBER, 31)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, JANUARY, 1)), transitionDay(LocalTime.of(18, 00), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, JANUARY, 2)), fullDay(DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2024, DECEMBER, 30)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2024, DECEMBER, 31)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2025, JANUARY, 1)), fullDay(MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2025, JANUARY, 2)), fullDay(MOM));
    }

    @Test
    void fathersDayRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JUNE, 9)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, JUNE, 10)), fullDay(DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2020, JUNE, 14)), transitionDay(LocalTime.of(11, 00), DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, JUNE, 15)), transitionDay(LocalTime.of(17, 30), MOM));
    }

    @Test
    void mothersDayRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, MAY, 12)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, MAY, 13)), transitionDay(LocalTime.of(17, 30), DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2020, MAY, 10)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, MAY, 11)), transitionDay(LocalTime.of(17, 30), DAD));
    }

    @Test
    void lenaBirthdayDayRule() {
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, AUGUST, 31)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2019, SEPTEMBER, 1)), transitionDay(LocalTime.of(11, 00), DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2020, AUGUST, 31)), fullDay(DAD));
        assertEquals(calendar.scheduleOn(LocalDate.of(2020, SEPTEMBER, 1)), fullDay(DAD));

        assertEquals(calendar.scheduleOn(LocalDate.of(2021, AUGUST, 31)), transitionDay(LocalTime.of(11, 00), MOM));
        assertEquals(calendar.scheduleOn(LocalDate.of(2021, SEPTEMBER, 1)), fullDay(MOM));
    }

}