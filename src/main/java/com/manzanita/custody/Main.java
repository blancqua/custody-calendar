package com.manzanita.custody;

import java.io.IOException;
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
