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
@Entity(name = "guidance")
public class Guidance extends PanacheEntity {

    @Column(name = "guidance_date", nullable = false)
    private LocalDate guidanceDate;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "notes")
    private String notes;

    @Column(name = "url_image_guidance")
    private String urlImageGuidance;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;
}
