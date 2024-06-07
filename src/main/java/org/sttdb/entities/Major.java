package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "major")
public class Major extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "type")
    private String type;

    @Column(name = "level")
    private String level;

    @Column(name = "description")
    private String description;

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "non_active_date")
    private LocalDate nonActiveDate;
}
