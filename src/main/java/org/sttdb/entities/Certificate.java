package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "certificate")
public class Certificate extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "graduate_date")
    private LocalDate graduateDate;

    @Column(name = "gpa")
    private Float gpa;

    @Column(name = "degree")
    private String degree;

    @Column(name = "level")
    private String level;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Student student;
}
