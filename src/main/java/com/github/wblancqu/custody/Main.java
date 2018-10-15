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
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String... args) throws IOException {
        LenaCalendarGenerator.instance().generate(requestYearFromUser());
    }

    private static int requestYearFromUser() {
        System.out.println("Year: ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}
