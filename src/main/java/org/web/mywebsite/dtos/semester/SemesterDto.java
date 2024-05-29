package org.web.mywebsite.dtos.semester;

import lombok.Data;
import org.web.mywebsite.entities.SemesterEntity;
import org.web.mywebsite.entities.YearEntity;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SemesterDto {
    private Long id;
    private String name;
    private YearEntity year;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public SemesterDto(SemesterEntity semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.year = semester.getYear();
        this.description = semester.getDescription();
        this.startDate = semester.getStartDate();
        this.endDate = semester.getEndDate();
        this.isActive = semester.getIsActive();
        this.createdAt = semester.getCreatedAt();
        this.updatedAt = semester.getUpdatedAt();
        this.createdBy = semester.getCreatedBy();
        this.updatedBy = semester.getUpdatedBy();
    }
}
