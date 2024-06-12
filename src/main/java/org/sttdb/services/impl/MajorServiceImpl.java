package org.sttdb.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sttdb.dto.major.MajorItemResponseDto;
import org.sttdb.dto.major.MajorUpsertRequestDto;
import org.sttdb.entities.Major;
import org.sttdb.repositories.MajorRepository;
import org.sttdb.services.MajorService;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class MajorServiceImpl implements MajorService {
    @Inject
    MajorRepository majorRepository;

    @Override
    public List<MajorItemResponseDto> getMajors(Integer pageNumber, Integer pageSize, String name) {
        if (pageNumber == null ) {
            pageNumber = 1;
        }
        if (pageSize == null ){
            pageSize = 10;
        }

        var majors = majorRepository.findAllBySearchName(pageNumber, pageSize, name);
        return majors.stream().map(major -> MajorItemResponseDto.builder()
                .id(major.id)
                .name(major.getName())
                .department(major.getDepartment())
                .level(major.getLevel())
                .type(major.getType())
                .faculty(major.getFaculty())
                .totalCredits(major.getTotalCredits())
                .description(major.getDescription())
                .build()).toList();
    }

    @Override
    public MajorItemResponseDto getMajor(Long majorId) {
        var major = majorRepository.findById(majorId);
        if (major == null){
            throw new EntityNotFoundException("Major with id =" + majorId + " not found");
        }
        return MajorItemResponseDto.builder()
                .id(major.id)
                .name(major.getName())
                .department(major.getDepartment())
                .level(major.getLevel())
                .type(major.getType())
                .faculty(major.getFaculty())
                .totalCredits(major.getTotalCredits())
                .description(major.getDescription())
                .build();
    }

    @Override
    @Transactional
    public void addMajor(MajorUpsertRequestDto dto) {
        if (majorRepository.findByName(dto.name()) != null){
            throw new EntityExistsException("Major " + dto.name() + " already exists");
        }

        if (dto.id() != null){
            throw new IllegalArgumentException("forbidden access because id is not null");
        }

        var major = Major.builder()
                .name(dto.name())
                .department(dto.department())
                .level(dto.level())
                .type(dto.type())
                .faculty(dto.faculty())
                .totalCredits(dto.totalCredits())
                .description(dto.description())
                .build();

        majorRepository.persist(major);
    }

    @Override
    @Transactional
    public void updateMajor(MajorUpsertRequestDto dto) {
        var major = majorRepository.findById(dto.id());
        if (major == null){
            throw new EntityNotFoundException("Major " + dto.id() + " not found");
        }
        major.setName(dto.name());
        major.setDepartment(dto.department());
        major.setLevel(dto.level());
        major.setType(dto.type());
        major.setFaculty(dto.faculty());
        major.setTotalCredits(dto.totalCredits());
        major.setDescription(dto.description());

        majorRepository.persist(major);
    }

    @Override
    @Transactional
    public void deleteMajor(Long majorId) {
        if (majorRepository.findById(majorId) == null) {
            throw new EntityNotFoundException("Major " + majorId + " not found");
        }
        majorRepository.deleteById(majorId);
    }

    @Override
    @Transactional
    public void inactiveMajor(Long id) {
        Major major = majorRepository.findById(id);
        if ( major == null) {
            throw new EntityNotFoundException("Major " + id + " not found");
        }
        major.setNonActiveDate(LocalDate.now());
        majorRepository.persist(major);
    }
}
