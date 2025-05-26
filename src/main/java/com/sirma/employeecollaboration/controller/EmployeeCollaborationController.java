package com.sirma.employeecollaboration.controller;

import com.sirma.employeecollaboration.model.EmployeeCollaborationDto;
import com.sirma.employeecollaboration.service.EmployeeCollaborationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeCollaborationController {

    private final EmployeeCollaborationService employeeCollaborationService;

    @Autowired
    public EmployeeCollaborationController(EmployeeCollaborationService employeeCollaborationService) {
        this.employeeCollaborationService = employeeCollaborationService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<EmployeeCollaborationDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<EmployeeCollaborationDto> result = employeeCollaborationService.processCsvAndFindLongestCollaboration(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
