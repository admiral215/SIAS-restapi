package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "certificate")
public class Certificate extends PanacheEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "graduate_date", nullable = false)
    private LocalDate graduateDate;

    @Column(name = "gpa", nullable = false)
    private Float gpa;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "level", nullable = false)
    private String level;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Student student;
}
