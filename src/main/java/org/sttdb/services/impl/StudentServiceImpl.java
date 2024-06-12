package org.sttdb.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sttdb.dto.student.StudentItemResponseDto;
import org.sttdb.dto.student.StudentRegistrationRequestDto;
import org.sttdb.dto.student.StudentUpdateRequestDto;
import org.sttdb.entities.Gender;
import org.sttdb.entities.Student;
import org.sttdb.entities.User;
import org.sttdb.repositories.StudentRepository;
import org.sttdb.repositories.UserRepository;
import org.sttdb.services.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {
    @Inject
    StudentRepository studentRepository;

    @Inject
    UserRepository userRepository;

    @Override
    public List<StudentItemResponseDto> getAllActiveStudents(Integer pageIndex, Integer pageSize, String name) {
        if (pageIndex == null ) {
            pageIndex = 1;
        }
        if (pageSize == null ){
            pageSize = 10;
        }

        var students = studentRepository.findActiveStudents(pageIndex,pageSize, name);
        return students.stream().map(student -> StudentItemResponseDto.builder()
                .userId(student.getUserId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getBirthDate())
                .age(student.getAge())
                .email(student.getEmail())
                .address(student.getAddress())
                .gender(student.getGender().getLabel())
                .birthCity(student.getBirthCity())
                .phoneNumber(student.getPhoneNumber())
                .totalCredits(student.getTotalCredits())
                .build()).toList();
    }

    @Override
    public StudentItemResponseDto getStudentById(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student " + id + " does not exist");
        }
        return StudentItemResponseDto.builder()
                .userId(student.getUserId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getBirthDate())
                .age(student.getAge())
                .email(student.getEmail())
                .address(student.getAddress())
                .gender(student.getGender().getLabel())
                .birthCity(student.getBirthCity())
                .phoneNumber(student.getPhoneNumber())
                .totalCredits(student.getTotalCredits())
                .build();
    }

    @Override
    @Transactional
    public void addStudent(StudentRegistrationRequestDto dto) {
        if (studentRepository.findById(dto.userId()) != null){
            throw new EntityExistsException("User " + dto.userId() + " already exists");
        }

        if (!dto.password().equals(dto.confirmPassword())){
            throw new IllegalArgumentException("Confirm password does not match");
        }

        var newUser = User.builder()
                .username(dto.userId())
                .password(dto.password())
                .role("student")
                .build();
        userRepository.persist(newUser);

        var newStudent = Student.builder()
                .userId(dto.userId())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .gender(Gender.valueOf(dto.gender().toUpperCase()))
                .birthCity(dto.birthCity())
                .phoneNumber(dto.phoneNumber())
                .totalCredits(dto.totalCredits())
                .registerDate(LocalDate.now())
                .email(dto.email())
                .user(newUser)
                .build();
        studentRepository.persist(newStudent);
    }

    @Override
    @Transactional
    public void updateStudent(StudentUpdateRequestDto dto, String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student " + id + " does not exist");
        }
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setBirthDate(dto.birthDate());
        student.setGender(Gender.valueOf(dto.gender().toUpperCase()));
        student.setPhoneNumber(dto.phoneNumber());
        student.setTotalCredits(dto.totalCredits());
        student.setEmail(dto.email());
        student.setAddress(dto.address());
        student.setBirthCity(dto.birthCity());
        studentRepository.persist(student);

    }

    @Override
    @Transactional
    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student " + id + " does not exist");
        }
        studentRepository.delete(student);
    }

    @Override
    @Transactional
    public void inactiveStudent(String id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student " + id + " does not exist");
        }
        student.setTerminatedDate(LocalDate.now());
        studentRepository.persist(student);
    }

}
