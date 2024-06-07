package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "thesis")
public class Thesis extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "first_lecturer_id", nullable = false)
    private String firstLecturerId;

    @Column(name = "second_lecturer_id")
    private String secondLecturerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "abstract", nullable = false)
    private String abstractField;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "first_lecturer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Lecturer firstLecturer;

    @ManyToOne
    @JoinColumn(name = "second_lecturer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Lecturer secondLecturer;

    @OneToMany(mappedBy = "thesis")
    private List<ThesisStatus> thesisStatuses;

    @OneToMany(mappedBy = "thesis")
    private List<SeminarProposal> proposals;

    @OneToMany(mappedBy = "thesis")
    private List<Defence> defences;

}
