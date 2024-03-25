package org.example.cirestechnologies.dto;

import lombok.Data;

@Data
public class UploadDtoRes {
    private int totalRecords;
    private int importedRecords;
    private int failedRecords;
}
