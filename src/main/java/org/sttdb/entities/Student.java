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
@Entity(name = "student")
public class Student extends PanacheEntityBase {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true)
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

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "terminated_date")
    private LocalDate terminatedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student")
    private List<StudentMajor> studentMajors;

    @OneToMany(mappedBy = "student")
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "student")
    private List<Guidance> guidances;

    @OneToMany(mappedBy = "student")
    private List<Thesis> theses;

    public Long getAge(){
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
