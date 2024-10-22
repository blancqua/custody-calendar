package com.manzanita.custody.domain;

import java.time.LocalDate;

public interface BaseRule {

    DaySchedule scheduleOn(LocalDate date);

}
