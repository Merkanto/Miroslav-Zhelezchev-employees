package com.sirma.employeecollaboration.model;

import java.time.LocalDate;

public record EmployeeProjectRecord(
        Long empId,
        Long projectId,
        LocalDate dateFrom,
        LocalDate dateTo) {
}
