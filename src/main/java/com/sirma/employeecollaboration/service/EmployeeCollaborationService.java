package com.sirma.employeecollaboration.service;

import com.sirma.employeecollaboration.model.EmployeeCollaborationDto;
import com.sirma.employeecollaboration.model.EmployeeProjectRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeCollaborationService {

    private final List<DateTimeFormatter> supportedFormats = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    );

    public List<EmployeeCollaborationDto> processCsvAndFindLongestCollaboration(MultipartFile file) throws Exception {
        List<EmployeeProjectRecord> records = parseCsv(file);

        Map<Long, List<EmployeeProjectRecord>> groupedByProject = records.stream()
                .collect(Collectors.groupingBy(EmployeeProjectRecord::projectId));

        List<EmployeeCollaborationDto> result = new ArrayList<>();
        long maxTotalDuration = 0;

        for (Map.Entry<Long, List<EmployeeProjectRecord>> entry : groupedByProject.entrySet()) {
            Long projectId = entry.getKey();
            List<EmployeeProjectRecord> projectRecords = entry.getValue();

            for (int i = 0; i < projectRecords.size(); i++) {
                for (int j = i + 1; j < projectRecords.size(); j++) {
                    EmployeeProjectRecord e1 = projectRecords.get(i);
                    EmployeeProjectRecord e2 = projectRecords.get(j);

                    long overlap = calculateOverlap(e1, e2);
                    if (overlap > 0) {
                        EmployeeCollaborationDto employeeCollaborationDto = new EmployeeCollaborationDto(
                                e1.empId(), e2.empId(), projectId, overlap
                        );
                        if (overlap > maxTotalDuration) {
                            result.clear();
                            result.add(employeeCollaborationDto);
                            maxTotalDuration = overlap;
                        } else if (overlap == maxTotalDuration) {
                            result.add(employeeCollaborationDto);
                        }
                    }
                }
            }
        }

        return result;
    }

    private List<EmployeeProjectRecord> parseCsv(MultipartFile file) throws Exception {
        List<EmployeeProjectRecord> records = new ArrayList<>();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser parser = new CSVParser(reader, format)
        ) {
            for (CSVRecord record : parser) {
                Long empId = Long.parseLong(record.get("EmpID"));
                Long projectId = Long.parseLong(record.get("ProjectID"));
                LocalDate dateFrom = parseDate(record.get("DateFrom"));
                LocalDate dateTo = parseDate(record.get("DateTo"));
                records.add(new EmployeeProjectRecord(empId, projectId, dateFrom, dateTo));
            }
        }

        return records;
    }

    private LocalDate parseDate(String input) {
        if (input == null || input.trim().equalsIgnoreCase("NULL")) {
            return LocalDate.now();
        }

        for (DateTimeFormatter formatter : supportedFormats) {
            try {
                return LocalDate.parse(input.trim(), formatter);
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new IllegalArgumentException("Unrecognized date format: " + input);
    }

    private long calculateOverlap(EmployeeProjectRecord e1, EmployeeProjectRecord e2) {
        LocalDate start = Collections.max(List.of(e1.dateFrom(), e2.dateFrom()));
        LocalDate end = Collections.min(List.of(e1.dateTo(), e2.dateTo()));
        if (!start.isAfter(end)) {
            return java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;
        }
        return 0;
    }
}
