package com.github.wblancqu.custody.domain;

import static com.github.wblancqu.custody.domain.Dates.nthDayIn;
import static com.github.wblancqu.custody.domain.Parent.DAD;
import static com.github.wblancqu.custody.domain.Parent.MOM;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.MAY;

import java.time.LocalDate;
import java.time.LocalTime;

public class MothersDayRule extends ParentsDayRule {

    public MothersDayRule() {
        super(MAY, MOM);
    }

}
