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
@Entity(name = "guidance")
public class Guidance extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "lecturer_id", nullable = false)
    private String lecturerId;

    @Column(name = "guidance_date")
    private LocalDate guidanceDate;

    @Column(name = "topic")
    private String topic;

    @Column(name = "notes")
    private String notes;

    @Column(name = "url_image_guidance")
    private String urlImageGuidance;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Lecturer lecturer;
}
