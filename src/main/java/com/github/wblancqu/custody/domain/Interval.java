package com.github.wblancqu.custody.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Interval {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Interval(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean contains(final LocalDate date) {
        return date.isAfter(startDate.minusDays(1)) &&
               date.isBefore(endDate.plusDays(1));
    }

    protected LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Interval interval = (Interval)o;
        return Objects.equals(startDate, interval.startDate) &&
               Objects.equals(endDate, interval.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "Interval{" +
               "startDate=" + startDate +
               ", endDate=" + endDate +
               '}';
    }

}
