package com.github.wblancqu.custody.application;

import static com.github.wblancqu.custody.domain.Parent.DAD;
import static com.github.wblancqu.custody.domain.Parent.MOM;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.SEPTEMBER;

import com.github.wblancqu.custody.domain.CustodyCalendar;
import com.github.wblancqu.custody.domain.FathersDayRule;
import com.github.wblancqu.custody.domain.IntervalAlternatingOverride;
import com.github.wblancqu.custody.domain.IntervalFixedOverride;
import com.github.wblancqu.custody.domain.MothersDayRule;
import com.github.wblancqu.custody.domain.SummerHolidaysRule;
import com.github.wblancqu.custody.domain.WeeklyRule;
import java.time.LocalTime;

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
        return new WeeklyRule(WEDNESDAY, LocalTime.of(17, 30), DAD);
    }
}
