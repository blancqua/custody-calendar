package com.github.wblancqu.custody.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class IntervalFixedOverride extends IntervalOverride {

    private final int startDayOfMonth;
    private final int endDayOfMonth;
    private final Parent parent;

    public IntervalFixedOverride(final Month startMonth, final int startDayOfMonth,
                                 final LocalTime startTransitionTime, final Month endMonth,
                                 final int endDayOfMonth, final LocalTime endTransitionTime,
                                 final Parent parent) {
        super(startMonth, startTransitionTime, endMonth, endTransitionTime);
        this.startDayOfMonth = startDayOfMonth;
        this.endDayOfMonth = endDayOfMonth;
        this.parent = parent;
    }

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
        return parent;
    }

}
