package com.sirma.employeecollaboration.model;

import java.util.Objects;
import java.util.Set;

public class EmployeePairProjectWork {
    private Set<Long> employeeIds;  // e.g., [143, 218]
    private Long projectId;
    private long daysWorked;

    public EmployeePairProjectWork(Set<Long> employeeIds, Long projectId, long daysWorked) {
        this.employeeIds = employeeIds;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public Set<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(Set<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeePairProjectWork that)) return false;
        return daysWorked == that.daysWorked &&
                Objects.equals(employeeIds, that.employeeIds) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeIds, projectId, daysWorked);
    }
}
