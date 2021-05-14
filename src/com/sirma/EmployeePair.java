package com.sirma;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class EmployeePair implements Comparable<EmployeePair> {
    private Employee first;
    private Employee second;
    private Integer overlappingDays;

    public EmployeePair(Employee first, Employee second) {
        this.first = first;
        this.second = second;
        calculateOverlappingDays();
    }

    public Employee getFirst() {
        return first;
    }

    public Employee getSecond() {
        return second;
    }

    public Integer getOverlappingDays() {
        return overlappingDays;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair pair = (EmployeePair) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second) ||
                Objects.equals(first, pair.second) && Objects.equals(second, pair.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    private void calculateOverlappingDays(){
        LocalDate firstLocalStartDate = convertToLocalDateViaInstant(first.getStartDate());
        LocalDate firstLocalEndDate = convertToLocalDateViaInstant(first.getEndDate());
        LocalDate secondLocalStartDate = convertToLocalDateViaInstant(second.getStartDate());
        LocalDate secondLocalEndDate = convertToLocalDateViaInstant(second.getEndDate());

        if (firstLocalEndDate.isBefore(firstLocalStartDate) || secondLocalEndDate.isBefore(secondLocalStartDate)) {
            // invalid input
            overlappingDays = -1;
        } else {
            long numberOfOverlappingDates = -1;
            if (firstLocalEndDate.isBefore(secondLocalStartDate) || secondLocalEndDate.isBefore(firstLocalStartDate)) {
                // no overlap
                overlappingDays = -1;
            } else {
                LocalDate laterStart = Collections.max(Arrays.asList(firstLocalStartDate, secondLocalStartDate));
                LocalDate earlierEnd = Collections.min(Arrays.asList(firstLocalEndDate, secondLocalEndDate));
                numberOfOverlappingDates = ChronoUnit.DAYS.between(laterStart, earlierEnd);
            }
            overlappingDays = (int)numberOfOverlappingDates;
        }
    }

    // Sort in descending order by overlapping days
    @Override
    public int compareTo(EmployeePair pair) {
        return pair.getOverlappingDays() - this.overlappingDays;
    }
}
