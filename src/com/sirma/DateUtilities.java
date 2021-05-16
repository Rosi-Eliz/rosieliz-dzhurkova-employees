package com.sirma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtilities {
    public static Date parseDateFrom(String dateString) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        knownPatterns.add(new SimpleDateFormat("dd-MM-yyyyy"));
        knownPatterns.add(new SimpleDateFormat("dd-MMM-yy"));
        knownPatterns.add(new SimpleDateFormat("dd-MMM-yyyy"));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yy"));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("dd.MM.yy"));
        knownPatterns.add(new SimpleDateFormat("dd.MM.yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyddd"));
        knownPatterns.add(new SimpleDateFormat("yyyyddd"));
        knownPatterns.add(new SimpleDateFormat("yy/MM/dd"));
        knownPatterns.add(new SimpleDateFormat("yyyy/MM/dd"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        knownPatterns.add(new SimpleDateFormat("d/M/yy"));
        knownPatterns.add(new SimpleDateFormat("d-M-yyyy"));
        knownPatterns.add(new SimpleDateFormat("d-M-yy"));

        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                return new Date(pattern.parse(dateString).getTime());
            } catch (ParseException pe) {
                // No action should take place
            }
        }
        System.err.println("No known Date format found: " + dateString);
        return null;
    }
}
