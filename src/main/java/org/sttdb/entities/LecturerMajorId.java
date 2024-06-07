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
public class LecturerMajorId implements Serializable {
    private String lecturerId;
    private Long majorId;
}
