package org.sttdb.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class StudentMajorId implements Serializable {
    private String studentId;
    private String majorId;
}
