package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.DaySchedule.fullDay;
import static com.github.wblancqu.custody.domain.DaySchedule.transitionDay;
import static java.time.Month.JANUARY;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyRule implements BaseRule {

    private final DayOfWeek transitionDay;
    private final LocalTime transitionTime;
    private final Parent firstParent;

    public WeeklyRule(final DayOfWeek transitionDay, final LocalTime transitionTime, final Parent firstParent) {
        this.transitionDay = transitionDay;
        this.transitionTime = transitionTime;
        this.firstParent = firstParent;
    }

    @Override
    public DaySchedule scheduleOn(final LocalDate date) {
        LocalDate firstTransitionDay = firstTransitionDayIn(date.getYear());
        if (date.isBefore(firstTransitionDay)) {
            return fullDay(firstParent.otherParent());
        } else {
            long daysBetween = DAYS.between(firstTransitionDay, date);
            long weeksAfterFirstTransitionDay = daysBetween / 7;
            boolean isTransitionDay = daysBetween % 7 == 0;
            Parent parentAtBeginningOfDay = weeksAfterFirstTransitionDay % 2 == 0 ? firstParent : firstParent.otherParent();
            return isTransitionDay ? transitionDay(transitionTime, parentAtBeginningOfDay) : fullDay(parentAtBeginningOfDay);
        }
    }

    private LocalDate firstTransitionDayIn(final int year) {
        LocalDate firstTransitionDay = LocalDate.of(year, JANUARY, 1);
        while (!transitionDay.equals(firstTransitionDay.getDayOfWeek())) {
            firstTransitionDay = firstTransitionDay.plusDays(1);
        }
        return firstTransitionDay;
    }

}
