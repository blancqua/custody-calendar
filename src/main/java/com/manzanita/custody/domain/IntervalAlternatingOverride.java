package com.manzanita.custody.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class IntervalAlternatingOverride extends IntervalOverride {

    private final int startDayOfMonth;
    private final int endDayOfMonth;
    private final Parent beneficiaryParentInOddYears;

    public IntervalAlternatingOverride(final Month startMonth, final int startDayOfMonth,
                                       final LocalTime startTransitionTime, final Month endMonth,
                                       final int endDayOfMonth, final LocalTime endTransitionTime,
                                       final Parent beneficiaryParentInOddYears) {
        super(startMonth, startTransitionTime, endMonth, endTransitionTime);
        this.startDayOfMonth = startDayOfMonth;
        this.endDayOfMonth = endDayOfMonth;
        this.beneficiaryParentInOddYears = beneficiaryParentInOddYears;
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
        // the year gets decided based on the start of the interval, intervals can go across new year
        boolean oddYear = intervalFor(date).getStartDate().getYear() % 2 == 1;
        return oddYear ? beneficiaryParentInOddYears : beneficiaryParentInOddYears.otherParent();
    }

}
