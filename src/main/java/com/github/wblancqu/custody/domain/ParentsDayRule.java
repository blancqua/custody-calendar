package com.github.wblancqu.custody.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static com.github.wblancqu.custody.domain.Dates.nthDayIn;
import static java.time.DayOfWeek.SUNDAY;

public abstract class ParentsDayRule extends IntervalOverride {

    private final Parent parent;

    public ParentsDayRule(final Month month, final Parent parent) {
        super(month, LocalTime.of(11, 00), month, LocalTime.of(17, 30));
        this.parent = parent;
    }

    @Override
    protected int getStartDayOfMonth(final int year) {
        return nthDayIn(year, startMonth, 2, SUNDAY).getDayOfMonth();
    }

    @Override
    protected int getEndDayOfMonth(final int year) {
        return nthDayIn(year, startMonth, 2, SUNDAY).plusDays(1).getDayOfMonth();
    }

    @Override
    protected Parent beneficiaryParent(final LocalDate date) {
        return parent;
    }

}
