package org.web.mywebsite.dtos.year;

import lombok.Data;
import org.web.mywebsite.dtos.semester.SemesterDto;
import org.web.mywebsite.entities.YearEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class YearDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private List<SemesterDto> semesters;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public YearDto(YearEntity year) {
        System.out.println(year.getSemesters());
        this.id = year.getId();
        this.title = year.getTitle();
        this.startDate = year.getStartDate();
        this.endDate = year.getEndDate();
        this.isActive = year.getIsActive();
        this.semesters = year.getSemesters().stream()
                .map(SemesterDto::new)
                .collect(Collectors.toList());
        this.createdAt = year.getCreatedAt();
        this.updatedAt = year.getUpdatedAt();
        this.createdBy = year.getCreatedBy();
        this.updatedBy = year.getUpdatedBy();
    }
}
