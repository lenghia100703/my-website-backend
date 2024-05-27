package org.web.mywebsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "year")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YearEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL)
    private List<SemesterEntity> semesters;

    private Boolean isActive;
}
