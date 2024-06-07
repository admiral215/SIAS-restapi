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
@Entity(name = "defence")
public class Defence extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "thesis_id", nullable = false)
    private String thesisId;

    @Column(name = "first_lecturer_id", nullable = false)
    private String firstLecturerId;

    @Column(name = "second_lecturer_id")
    private String secondLecturerId;

    @Column(name = "defence_date", nullable = false)
    private LocalDate defenceDate;

    @Column(name = "defence_time", nullable = false)
    private String defenceTime;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "notes")
    private String notes;

    @Column(name = "defence_file")
    private String defenceFile;

    @ManyToOne
    @JoinColumn(name = "thesis_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Thesis thesis;

    @ManyToOne
    @JoinColumn(name = "first_lecturer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Lecturer firstLecturer;

    @ManyToOne
    @JoinColumn(name = "second_lecturer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Lecturer secondLecturer;
}