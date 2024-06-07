package org.sttdb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "lecturer_major")
public class LecturerMajor {
    @EmbeddedId
    private LecturerMajorId id;

    @Column(name = "set_date", nullable = false)
    private LocalDate setDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "major_id")
    private Major major;
}
