package com.github.wblancqu.custody.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.github.wblancqu.custody.domain.Parent.MOM;
import static java.time.LocalTime.NOON;
import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.*;

class IntervalOverrideTest {

    @Test
    void intervalWithinSameYear() {
        IntervalOverride rule = intervalOverride(DECEMBER, 1, DECEMBER, 31);

        Interval expectedInterval = new Interval(LocalDate.of(2018, DECEMBER, 1),
                                                 LocalDate.of(2018, DECEMBER, 31));

        assertTrue(rule.isApplicableOn(LocalDate.of(2018, DECEMBER, 31)));
        assertEquals(rule.intervalFor(LocalDate.of(2018, DECEMBER, 31)), expectedInterval);

        assertFalse(rule.isApplicableOn(LocalDate.of(2018, AUGUST, 31)));
        assertEquals(rule.intervalFor(LocalDate.of(2018, AUGUST, 31)), expectedInterval);
    }

    @Test
    void intervalOverNewYear() {
        IntervalOverride rule = intervalOverride(DECEMBER, 1, JANUARY, 31);

        Interval expectedInterval = new Interval(LocalDate.of(2018, DECEMBER, 1),
                                                 LocalDate.of(2019, JANUARY, 31));

        assertTrue(rule.isApplicableOn(LocalDate.of(2018, DECEMBER, 31)));
        assertEquals(rule.intervalFor(LocalDate.of(2018, DECEMBER, 31)), expectedInterval);

        assertTrue(rule.isApplicableOn(LocalDate.of(2019, JANUARY, 31)));
        assertEquals(rule.intervalFor(LocalDate.of(2019, JANUARY, 31)), expectedInterval);

        assertFalse(rule.isApplicableOn(LocalDate.of(2018, AUGUST, 31)));
        assertEquals(rule.intervalFor(LocalDate.of(2018, AUGUST, 31)), expectedInterval);
    }

    private IntervalOverride intervalOverride(final Month startMonth, final int startDayOfMonth,
                                              final Month endMonth, final int endDayOfMonth) {
        return new IntervalOverride(startMonth, NOON,
                                    endMonth, NOON) {

            @Override
            protected int getStartDayOfMonth(final int year) {
                return startDayOfMonth;
            }

            @Override
            protected int getEndDayOfMonth(final int year) {
                return endDayOfMonth;
            }

            @Override
            protected Parent beneficiaryParent(final LocalDate date) {
                return MOM;
            }
        };
    }

}