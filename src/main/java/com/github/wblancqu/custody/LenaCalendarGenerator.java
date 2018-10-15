package com.github.wblancqu.custody;

import static com.github.wblancqu.custody.domain.Parent.DAD;
import static java.time.Month.JANUARY;
import static java.time.ZoneId.systemDefault;
import static java.util.stream.Collectors.toList;
import static net.fortuna.ical4j.model.property.CalScale.GREGORIAN;
import static net.fortuna.ical4j.model.property.Version.VERSION_2_0;

import com.github.wblancqu.custody.application.LenaCalendar;
import com.github.wblancqu.custody.domain.DaySchedule;
import com.github.wblancqu.custody.domain.Parent;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.TzId;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class LenaCalendarGenerator {

    private static final LenaCalendarGenerator INSTANCE = new LenaCalendarGenerator();
    private static final TzId TIME_ZONE_ID;
    private static final UidGenerator UID_GENERATOR = new RandomUidGenerator();

    static {
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone(systemDefault().getId());
        TIME_ZONE_ID = timezone.getVTimeZone().getTimeZoneId();
    }

    private LenaCalendarGenerator() {}

    public static LenaCalendarGenerator instance() {
        return INSTANCE;
    }

    public void generate(final int year) throws IOException {
        writeFile(lenaCalendarIn(year), "lena_" + year + ".ics");
    }

    private Calendar lenaCalendarIn(final int year) {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
        calendar.getProperties().add(VERSION_2_0);
        calendar.getProperties().add(GREGORIAN);
        eventsIn(year).forEach(event -> calendar.getComponents().add(event));
        return calendar;
    }

    private List<VEvent> eventsIn(final int year) {
        List<VEvent> events = new ArrayList<>();
        LocalDate startOfYear = LocalDate.of(year, JANUARY, 1);
        ZonedDateTime startDatetime = ZonedDateTime.of(startOfYear.atStartOfDay(), systemDefault());
        LocalDate endDate = startOfYear;
        Parent previousParent = null;
        for (DaySchedule daySchedule : daySchedulesIn(year)) {
            if (daySchedule.isTransitionDay()) {
                ZonedDateTime endDateTime = ZonedDateTime.of(endDate.atTime(daySchedule.getTransitionTime()), systemDefault());
                previousParent = daySchedule.parentAtStartOfDay();
                events.add(event(startDatetime, previousParent, endDateTime));
                startDatetime = endDateTime;
            }
            endDate = endDate.plusDays(1);
        }
        ZonedDateTime endDateTime = ZonedDateTime.of(endDate.atStartOfDay(), systemDefault());
        events.add(event(startDatetime, previousParent.otherParent(), endDateTime));
        return events;
    }

    private List<DaySchedule> daySchedulesIn(final int year) {
        return LocalDate.of(2019, JANUARY, 1).datesUntil(LocalDate.of(year + 1, JANUARY, 1))
                        .map(date -> LenaCalendar.instance().scheduleOn(date))
                        .collect(toList());
    }

    private VEvent event(final ZonedDateTime startDatetime, final Parent parent,
                         final ZonedDateTime endDateTime) {
        VEvent event = new VEvent(icalDateTime(startDatetime), icalDateTime(endDateTime), "Lena (" + (DAD.equals(parent) ? "Wouter" : "Sofie") + ")");
        event.getProperties().add(TIME_ZONE_ID);
        event.getProperties().add(UID_GENERATOR.generateUid());
        return event;
    }

    private DateTime icalDateTime(final ZonedDateTime datetime) {
        return new DateTime(Date.from(datetime.toInstant()));
    }

    private void writeFile(final Calendar icsCalendar, final String fileName) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(fileName)) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(icsCalendar, fout);
        }
    }

}
