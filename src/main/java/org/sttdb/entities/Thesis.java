package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "thesis")
public class Thesis extends PanacheEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "abstract", nullable = false)
    private String abstractField;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "first_lecturer_id", nullable = false)
    private Lecturer firstLecturer;

    @ManyToOne
    @JoinColumn(name = "second_lecturer_id", nullable = false)
    private Lecturer secondLecturer;

    @OneToMany(mappedBy = "thesis")
    private List<ThesisStatus> thesisStatuses;

    @OneToMany(mappedBy = "thesis")
    private List<SeminarProposal> proposals;

    @OneToMany(mappedBy = "thesis")
    private List<Defence> defences;

}
