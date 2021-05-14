package com.sirma;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static EmployeePair createPairs(HashMap<Integer, List<Employee>> employees){
        SortedSet<EmployeePair> result = new TreeSet<>();
        if(employees.size() < 2) {
            return null;
        }

        for(Integer project : employees.keySet()) {
            //for every project create all pairs of employees which have overlapping periods of work
            List<Employee> currentEmployees = employees.get(project);
            for(int i = 0; i < currentEmployees.size(); i++ ){
                for(int j = i+1; j < currentEmployees.size(); j++) {
                    EmployeePair pair = new EmployeePair(currentEmployees.get(i), currentEmployees.get(j));
                    if(pair.getOverlappingDays() > 0) {
                        result.add(pair);
                    }
                }
            }
        }
        return result.first();
    }

    public static HashMap<Integer, List<Employee>> groupByProjects(List<Employee> employees) {
        HashMap<Integer, List<Employee>> result = new HashMap<>();
        for(Employee employee : employees)
        {
            result.computeIfAbsent(employee.getProjectId(), key -> new LinkedList<Employee>());
            result.get(employee.getProjectId()).add(employee);
        }
        return result;
    }

    public static List<Employee> readEmployeesFromFile(){
        List<Employee> employees = new LinkedList<>();
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                File inputFile = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(inputFile);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    line = line.replaceAll("\\s+", "");
                    String[] parts = line.split(",");
                    assert (parts.length == 4);
                    Integer id = Integer.parseInt(parts[0]);
                    Integer projectId = Integer.parseInt(parts[1]);
                    String startDate = parts[2].equals("NULL") ? null : parts[2];
                    String endDate = parts[3].equals("NULL") ? null : parts[3];
                    Employee currentEmployee = new Employee(id, projectId, startDate, endDate);
                    employees.add(currentEmployee);
                }
                scanner.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return employees;
    }
    public static void main(String[] args) {
        List<Employee> employees = readEmployeesFromFile();
        HashMap<Integer, List<Employee>> groupedEmployees = groupByProjects(employees);
        EmployeePair pair = createPairs(groupedEmployees);
        System.out.println("Employee with id: " + pair.getFirst().getId() +
                " and employee with id: " + pair.getSecond().getId() +
                " have worked on project with id: " + pair.getFirst().getProjectId() +
                " for " +pair.getOverlappingDays() + " days.");
    }
}
