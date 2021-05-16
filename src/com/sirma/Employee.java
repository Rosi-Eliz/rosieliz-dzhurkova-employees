package com.sirma;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class Employee {
    private Integer id;
    private Integer projectId;
    private Date startDate;
    private Date endDate;

    public Employee(Integer id, Integer projectId, String startDate, String endDate) throws ParseException {
        this.id = id;
        this.projectId = projectId;
        if(startDate == null){
            this.startDate = new Date(System.currentTimeMillis());;
        } else {
            this.startDate = DateUtilities.parseDateFrom(startDate);
        }

        if(endDate == null){
            this.endDate = new Date(System.currentTimeMillis());;
        } else {
            this.endDate = DateUtilities.parseDateFrom(endDate);
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
