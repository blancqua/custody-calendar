package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.DaySchedule.fullDay;
import static com.github.wblancqu.custody.domain.DaySchedule.transitionDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public abstract class IntervalOverride implements Rule {

    protected final Month startMonth;
    private final LocalTime startTransitionTime;
    private final Month endMonth;
    private final LocalTime endTransitionTime;

    public IntervalOverride(final Month startMonth, final LocalTime startTransitionTime,
                            final Month endMonth, final LocalTime endTransitionTime) {
        this.startMonth = startMonth;
        this.startTransitionTime = startTransitionTime;
        this.endMonth = endMonth;
        this.endTransitionTime = endTransitionTime;
    }

    @Override
    public boolean isApplicableOn(final LocalDate date) {
        return intervalFor(date).contains(date);
    }

    @Override
    public DaySchedule applyOn(final DaySchedule daySchedule, final LocalDate date) {
        Parent parent = beneficiaryParent(date);
        if (intervalFor(date).getStartDate().isEqual(date)) {
            return daySchedule.transitionTo(startTransitionTime, parent);
        } else if (intervalFor(date).getEndDate().isEqual(date)) {
            return daySchedule.transitionFrom(endTransitionTime, parent);
        } else {
            return fullDay(parent);
        }
    }

    protected Interval intervalFor(final LocalDate date) {
        LocalDate startDate = LocalDate.of(date.getYear(), startMonth, getStartDayOfMonth(date.getYear()));
        LocalDate endDate = LocalDate.of(date.getYear(), endMonth, getEndDayOfMonth(date.getYear()));
        if (endDate.isBefore(startDate)) {
            LocalDate startDateBefore = startDate.minusYears(1);
            Interval intervalBefore = new Interval(startDateBefore, endDate);
            if (intervalBefore.contains(date)) {
                return intervalBefore;
            } else {
                return new Interval(startDate, endDate.plusYears(1));
            }
        } else {
            return new Interval(startDate, endDate);
        }
    }

    protected abstract int getStartDayOfMonth(int year);
    protected abstract int getEndDayOfMonth(int year);
    protected abstract Parent beneficiaryParent(final LocalDate date);

}
