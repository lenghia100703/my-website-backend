package org.web.mywebsite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<DocumentEntity> documents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private SemesterEntity semester;

    private String image;

    private Boolean isPublic;
}
