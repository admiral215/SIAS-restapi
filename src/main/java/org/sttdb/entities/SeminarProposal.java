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
@Entity(name = "seminar_proposal")
public class SeminarProposal extends PanacheEntity {
    @Column(name = "seminar_date", nullable = false)
    private LocalDate seminarDate;

    @Column(name = "seminar_time", nullable = false)
    private String seminarTime;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "notes")
    private String notes;

    @Column(name = "proposal_file", columnDefinition = "bytea")
    private byte[] proposalFile;

    @ManyToOne
    @JoinColumn(name = "thesis_id", nullable = false)
    private Thesis thesis;

    @ManyToOne
    @JoinColumn(name = "first_lecturer_id", nullable = false)
    private Lecturer firstLecturer;

    @ManyToOne
    @JoinColumn(name = "second_lecturer_id", nullable = false)
    private Lecturer secondLecturer;
}
