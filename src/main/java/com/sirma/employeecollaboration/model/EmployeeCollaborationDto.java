package com.sirma.employeecollaboration.model;

public record EmployeeCollaborationDto(
        Long employee1Id,
        Long employee2Id,
        Long projectId,
        long daysWorkedTogether
) {}
