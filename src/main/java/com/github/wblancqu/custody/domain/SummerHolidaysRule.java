package com.github.wblancqu.custody.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.github.wblancqu.custody.domain.DaySchedule.fullDay;
import static com.github.wblancqu.custody.domain.DaySchedule.transitionDay;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.JULY;
import static java.time.temporal.ChronoUnit.DAYS;

public class SummerHolidaysRule implements Rule {

    private final int lowerBoundMondayInJuly;
    private final Parent firstParentInOddYears;
    private final LocalTime transitionTime;

    public SummerHolidaysRule(final int lowerBoundMondayInJuly,
                              final Parent firstParentInOddYears,
                              final LocalTime transitionTime) {
        this.lowerBoundMondayInJuly = lowerBoundMondayInJuly;
        this.firstParentInOddYears = firstParentInOddYears;
        this.transitionTime = transitionTime;
    }

    @Override
    public boolean isApplicableOn(final LocalDate date) {
        LocalDate startDate = startDateIn(date.getYear());
        return date.isAfter(startDate.minusDays(1))
               && date.isBefore(startDate.plusDays(29));
    }

    @Override
    public DaySchedule applyOn(final DaySchedule daySchedule, final LocalDate date) {
        boolean oddYear = date.getYear() % 2 == 1;
        Parent firstParent = oddYear ? firstParentInOddYears : firstParentInOddYears.otherParent();
        LocalDate startDate = startDateIn(date.getYear());
        long daysBetween = DAYS.between(startDate, date);
        if (daysBetween == 0) {
            return daySchedule.transitionTo(transitionTime, firstParent);
        } else if (daysBetween < 14) {
            return fullDay(firstParent);
        } else if (daysBetween == 14) {
            return transitionDay(transitionTime, firstParent.otherParent());
        } else if (daysBetween < 28) {
            return fullDay(firstParent.otherParent());
        } else {
            return daySchedule.transitionFrom(transitionTime, firstParent.otherParent());
        }
    }

    private LocalDate startDateIn(final int year) {
        LocalDate startDate = LocalDate.of(year, JULY, lowerBoundMondayInJuly);
        while (!MONDAY.equals(startDate.getDayOfWeek())) {
            startDate = startDate.plusDays(1);
        }
        return startDate;
    }

}
