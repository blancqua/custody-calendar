package com.manzanita.custody.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.manzanita.custody.domain.DaySchedule.fullDay;
import static com.manzanita.custody.domain.DaySchedule.transitionDay;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.ChronoUnit.DAYS;

public class WeeklyRule implements BaseRule {

    private final LocalDate startDate;
    private final DayOfWeek transitionDay;
    private final LocalTime transitionTime;
    private final Parent firstParent;

    public WeeklyRule(final LocalDate startDate, final DayOfWeek transitionDay, final LocalTime transitionTime, final Parent firstParent) {
        if (!DayOfWeek.from(startDate).equals(MONDAY)) {
            throw new IllegalArgumentException("Please provide a Monday as start date");
        }
        this.startDate = startDate;
        this.transitionDay = transitionDay;
        this.transitionTime = transitionTime;
        this.firstParent = firstParent;
    }

    @Override
    public DaySchedule scheduleOn(final LocalDate date) {
        Parent parentAtBeginningOfWeek = parentAtBeginningOfWeek(date);
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek < transitionDay.getValue()) {
            return fullDay(parentAtBeginningOfWeek);
        } else if (dayOfWeek > transitionDay.getValue()) {
            return fullDay(parentAtBeginningOfWeek.otherParent());
        } else {
            return transitionDay(transitionTime, parentAtBeginningOfWeek.otherParent());
        }
    }

    private Parent parentAtBeginningOfWeek(final LocalDate date) {
        return isAlternateWeek(date) ? firstParent.otherParent() : firstParent;
    }

    private boolean isAlternateWeek(final LocalDate date) {
        // not basing on week number but on start of schedule to avoid leaps when years have 53 weeks
        return DAYS.between(startDate, date) % 14 >= 7;
    }

}
