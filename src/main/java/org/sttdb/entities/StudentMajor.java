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
@Entity(name = "student_major")
public class StudentMajor extends PanacheEntityBase {
    @EmbeddedId
    private StudentMajorId id;

    @Column(name = "set_date")
    private LocalDate setDate;

    @Column(name = "complete_date")
    private LocalDate completeDate;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "major_id")
    private Major major;
}
