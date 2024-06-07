package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "major")
public class Major extends PanacheEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "type")
    private String type;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "description")
    private String description;

    @Column(name = "total_credits", nullable = false)
    private Integer totalCredits;

    @Column(name = "non_active_date")
    private LocalDate nonActiveDate;

    @OneToMany(mappedBy = "major")
    private List<StudentMajor> studentMajors;

    @OneToMany(mappedBy = "major")
    private List<LecturerMajor> lecturerMajors;
}
