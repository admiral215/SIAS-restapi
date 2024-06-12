package org.sttdb.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sttdb.dto.lecturer.LecturerItemResponseDto;
import org.sttdb.dto.lecturer.LecturerRegistrationRequestDto;
import org.sttdb.dto.lecturer.LecturerUpdateRequestDto;
import org.sttdb.entities.Gender;
import org.sttdb.entities.Lecturer;
import org.sttdb.entities.User;
import org.sttdb.repositories.LecturerRepository;
import org.sttdb.repositories.UserRepository;
import org.sttdb.services.LecturerService;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class LecturerServiceImpl implements LecturerService {
    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    UserRepository userRepository;


    @Override
    public List<LecturerItemResponseDto> getAllActiveLecturers(Integer pageNumber, Integer pageSize, String name) {
        if (pageNumber == null ) {
            pageNumber = 1;
        }
        if (pageSize == null ){
            pageSize = 2;
        }
        var lecturers = lecturerRepository.findActiveLecturers(pageNumber,pageSize, name);
        return lecturers.stream().map(lecturer -> LecturerItemResponseDto.builder()
                .userId(lecturer.getUserId())
                .firstName(lecturer.getFirstName())
                .lastName(lecturer.getLastName())
                .email(lecturer.getEmail())
                .phoneNumber(lecturer.getPhoneNumber())
                .birthDate(lecturer.getBirthDate())
                .age(lecturer.getAge())
                .gender(lecturer.getGender().getLabel())
                .address(lecturer.getAddress())
                .birthCity(lecturer.getBirthCity())
                .registerDate(lecturer.getRegisterDate())
                .build()).toList();
    }

    @Override
    public LecturerItemResponseDto getLecturerById(String lecturerId) {
        var lecturer = lecturerRepository.findById(lecturerId);
        if (lecturer == null) {
            throw new EntityNotFoundException("Lecturer " + lecturerId + " not found");
        }

        return LecturerItemResponseDto.builder()
                .userId(lecturer.getUserId())
                .firstName(lecturer.getFirstName())
                .lastName(lecturer.getLastName())
                .email(lecturer.getEmail())
                .phoneNumber(lecturer.getPhoneNumber())
                .birthDate(lecturer.getBirthDate())
                .age(lecturer.getAge())
                .gender(lecturer.getGender().getLabel())
                .address(lecturer.getAddress())
                .birthCity(lecturer.getBirthCity())
                .registerDate(lecturer.getRegisterDate())
                .build();
    }

    @Override
    @Transactional
    public void addLecturer(LecturerRegistrationRequestDto dto) {
        if (lecturerRepository.findById(dto.userId()) != null){
            throw new EntityExistsException("User " + dto.userId() + " already exists");
        }

        if (!dto.password().equals(dto.confirmPassword())){
            throw new IllegalArgumentException("Confirm password does not match");
        }

        var newUser = User.builder()
                .username(dto.userId())
                .password(dto.password())
                .role("lecturer")
                .build();
        userRepository.persist(newUser);

        var newLecturer = Lecturer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .birthDate(dto.birthDate())
                .birthCity(dto.birthCity())
                .gender(Gender.valueOf(dto.gender().toUpperCase()))
                .address(dto.address())
                .registerDate(LocalDate.now())
                .user(newUser)
                .build();
        lecturerRepository.persist(newLecturer);
    }

    @Override
    @Transactional
    public void updateLecturer(LecturerUpdateRequestDto dto) {
        var lecturer = lecturerRepository.findById(dto.userId());

        if (lecturer == null) {
            throw new EntityNotFoundException("Lecturer " + dto.userId() + " not found");
        }

        lecturer.setFirstName(dto.firstName());
        lecturer.setLastName(dto.lastName());
        lecturer.setEmail(dto.email());
        lecturer.setPhoneNumber(dto.phoneNumber());
        lecturer.setBirthDate(dto.birthDate());
        lecturer.setBirthCity(dto.birthCity());
        lecturer.setGender(Gender.valueOf(dto.gender().toUpperCase()));
        lecturer.setAddress(dto.address());
        lecturerRepository.persist(lecturer);
    }

    @Override
    @Transactional
    public void deleteLecturer(String lecturerId) {
        var lecturer = lecturerRepository.findById(lecturerId);

        if (lecturer == null) {
            throw new EntityNotFoundException("Lecturer " + lecturerId + " not found");
        }

        lecturerRepository.delete(lecturer);
    }

    @Override
    @Transactional
    public void inactiveLecturer(String lecturerId) {
        var lecturer = lecturerRepository.findById(lecturerId);

        if (lecturer == null) {
            throw new EntityNotFoundException("Lecturer " + lecturerId + " not found");
        }

        lecturer.setTerminatedDate(LocalDate.now());
        lecturerRepository.persist(lecturer);
    }
}
