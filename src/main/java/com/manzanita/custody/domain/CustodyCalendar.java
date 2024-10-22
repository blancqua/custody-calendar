package com.manzanita.custody.domain;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CustodyCalendar {

    private final BaseRule baseRule;
    private final List<Rule> rules;

    public CustodyCalendar(final BaseRule baseRule, final Rule... rules) {
        this(baseRule, List.of(rules));
    }

    public CustodyCalendar(final BaseRule baseRule, final List<Rule> rules) {
        this.baseRule = baseRule;
        this.rules = rules;
    }

    public DaySchedule scheduleOn(final LocalDate date) {
        DaySchedule baseSchedule = this.baseRule.scheduleOn(date);
        for (Rule applicableRule : applicableRulesOn(date)) {
            baseSchedule = applicableRule.applyOn(baseSchedule, date);
        }
        return baseSchedule;
    }

    private List<Rule> applicableRulesOn(final LocalDate date) {
        return rules.stream().filter(r -> r.isApplicableOn(date)).collect(toList());
    }

}
