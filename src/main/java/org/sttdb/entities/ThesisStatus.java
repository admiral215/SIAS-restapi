package org.sttdb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "thesis_status")
public class ThesisStatus {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "thesis_id", nullable = false)
    private String thesisId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "thesis_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Thesis thesis;
}
