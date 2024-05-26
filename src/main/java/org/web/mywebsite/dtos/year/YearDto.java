package org.web.mywebsite.dtos.year;

import lombok.Data;
import org.web.mywebsite.entities.YearEntity;

import java.time.LocalDate;
import java.util.Date;

@Data
public class YearDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public YearDto(YearEntity year) {
        this.id = year.getId();
        this.title = year.getTitle();
        this.startDate = year.getStartDate();
        this.endDate = year.getEndDate();
        this.isActive = year.getIsActive();
        this.createdAt = year.getCreatedAt();
        this.updatedAt = year.getUpdatedAt();
        this.createdBy = year.getCreatedBy();
        this.updatedBy = year.getUpdatedBy();
    }
}
