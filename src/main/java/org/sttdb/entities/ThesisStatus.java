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
@Entity(name = "thesis_status")
public class ThesisStatus extends PanacheEntity {

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ThesisStatusEnum status;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
