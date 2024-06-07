package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "lecturer")
public class Lecturer extends PanacheEntityBase {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_city")
    private String birthCity;

    @Column(name = "address")
    private String address;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    @Column(name = "terminated_date")
    private LocalDate terminatedDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "lecturer")
    private List<LecturerMajor> lecturerMajors;

    @OneToMany(mappedBy = "lecturer")
    private List<Guidance> guidances;

    @OneToMany(mappedBy = "firstLecturer")
    private List<Thesis> firstLecturerTheses;

    @OneToMany(mappedBy = "secondLecturer")
    private List<Thesis> secondLecturerTheses;
}
