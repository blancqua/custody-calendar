package com.github.wblancqu.custody.domain;

import java.time.LocalTime;
import java.util.Objects;

public class DaySchedule {

    private final Parent parentAtStartOfDay;
    private final LocalTime transitionTime;

    private DaySchedule(final Parent parentAtStartOfDay, final LocalTime transitionTime) {
        this.parentAtStartOfDay = parentAtStartOfDay;
        this.transitionTime = transitionTime;
    }

    public static DaySchedule fullDay(final Parent parent) {
        return new DaySchedule(parent, null);
    }

    public static DaySchedule transitionDay(final LocalTime transitionTime, final Parent toParent) {
        return new DaySchedule(toParent.otherParent(), transitionTime);
    }

    public Parent parentAtStartOfDay() {
        return parentAtStartOfDay;
    }

    public Parent parentAtEndOfDay() {
        return transitionTime == null ? parentAtStartOfDay : parentAtStartOfDay.otherParent();
    }

    public DaySchedule transitionTo(final LocalTime transitionTime, final Parent parent) {
        return parent.equals(this.parentAtStartOfDay())
               ? fullDay(parent)
               : transitionDay(transitionTime, parent);
    }

    public DaySchedule transitionFrom(final LocalTime transitionTime, final Parent parent) {
        return parent.equals(this.parentAtEndOfDay())
               ? fullDay(parent)
               : transitionDay(transitionTime, parent.otherParent());

    }

    public boolean isTransitionDay() {
        return transitionTime != null;
    }

    public LocalTime getTransitionTime() {
        return transitionTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DaySchedule that = (DaySchedule)o;
        return parentAtStartOfDay == that.parentAtStartOfDay &&
               Objects.equals(transitionTime, that.transitionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentAtStartOfDay, transitionTime);
    }

    @Override
    public String toString() {
        return "DaySchedule{" +
               "parentAtStartOfDay=" + parentAtStartOfDay +
               ", transitionTime=" + transitionTime +
               '}';
    }
}
