package org.sttdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_city", nullable = false)
    private String birthCity;

    @Column(name = "address", nullable = false)
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

    public Long getAge(){
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
