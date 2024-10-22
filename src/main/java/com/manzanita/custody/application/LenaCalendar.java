package com.manzanita.custody.application;

import com.manzanita.custody.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.manzanita.custody.domain.Parent.DAD;
import static com.manzanita.custody.domain.Parent.MOM;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.*;

public class LenaCalendar extends CustodyCalendar {

    private static final LenaCalendar INSTANCE = new LenaCalendar();

    private LenaCalendar() {
        super(
                baseRule(),
                summerHolidays(),
                christmasEve(),
                christmas(),
                newYear(),
                lenasBirthDay(),
                mothersDay(),
                fathersDay());
    }

    public static final LenaCalendar instance() {
        return INSTANCE;
    }

    private static FathersDayRule fathersDay() {
        return new FathersDayRule();
    }

    private static MothersDayRule mothersDay() {
        return new MothersDayRule();
    }

    private static IntervalAlternatingOverride lenasBirthDay() {
        return new IntervalAlternatingOverride(
                AUGUST, 31, LocalTime.of(11, 00),
                SEPTEMBER, 1, LocalTime.of(11, 00),
                MOM
        );
    }

    private static IntervalAlternatingOverride newYear() {
        return new IntervalAlternatingOverride(
                DECEMBER, 31, LocalTime.of(11, 00),
                JANUARY, 1, LocalTime.of(18, 00),
                DAD
        );
    }

    private static IntervalFixedOverride christmas() {
        return new IntervalFixedOverride(
                DECEMBER, 25, LocalTime.of(11, 00),
                DECEMBER, 26, LocalTime.of(11, 00),
                MOM
        );
    }

    private static IntervalFixedOverride christmasEve() {
        return new IntervalFixedOverride(
                DECEMBER, 24, LocalTime.of(11, 00),
                DECEMBER, 25, LocalTime.of(11, 00),
                DAD
        );
    }

    private static SummerHolidaysRule summerHolidays() {
        return new SummerHolidaysRule(13, DAD, LocalTime.of(18, 00));
    }

    private static WeeklyRule baseRule() {
        return new WeeklyRule(
                LocalDate.of(2017, MAY, 29),
                WEDNESDAY,
                LocalTime.of(17, 30),
                DAD);
    }
}
