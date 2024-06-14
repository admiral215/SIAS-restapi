package org.sttdb.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sttdb.dto.thesis.ThesisStatusRequestDto;
import org.sttdb.dto.thesis.ThesisChooseSupervisorRequestDto;
import org.sttdb.dto.thesis.ThesisItemResponseDto;
import org.sttdb.dto.thesis.ThesisUpsertRequestDto;
import org.sttdb.entities.*;
import org.sttdb.repositories.LecturerRepository;
import org.sttdb.repositories.StudentRepository;
import org.sttdb.repositories.ThesisRepository;
import org.sttdb.repositories.ThesisStatusRepository;
import org.sttdb.services.ThesisService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ThesisServiceImpl implements ThesisService {
    @Inject
    ThesisRepository thesisRepository;

    @Inject
    StudentRepository studentRepository;

    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    ThesisStatusRepository thesisStatusRepository;


    @Override
    @Transactional
    public void insertThesis(ThesisUpsertRequestDto dto) {
        Student student = studentRepository.findById(dto.studentId());
        if (student == null) {
            throw new EntityNotFoundException("Student " + dto.studentId() + " not found");
        }
        if (dto.id() != null){
            throw new IllegalArgumentException("id must be null to insert thesis");
        }


        Thesis newThesis = Thesis.builder()
                .title(dto.title())
                .abstractField(dto.abstractField())
                .student(student)
                .build();
        thesisRepository.persist(newThesis);

        ThesisStatus thesisStatus = ThesisStatus.builder()
                .date(LocalDate.now())
                .description("first submit")
                .status(ThesisStatusEnum.SUBMITTED)
                .thesis(newThesis)
                .build();
        thesisStatusRepository.persist(thesisStatus);
    }

    @Override
    @Transactional
    public void updateThesis(ThesisUpsertRequestDto dto) {
        Student student = studentRepository.findById(dto.studentId());
        if (student == null) {
            throw new EntityNotFoundException("Student " + dto.studentId() + " not found");
        }

        Thesis currentThesis = thesisRepository.findById(dto.id());
        if (currentThesis == null) {
            throw new EntityNotFoundException("Thesis " + dto.id() + " not found");
        }

        currentThesis.setTitle(dto.title());
        currentThesis.setAbstractField(dto.abstractField());
        thesisRepository.persist(currentThesis);
    }

    @Override
    @Transactional
    public void chooseSupervisor(ThesisChooseSupervisorRequestDto dto) {
        Thesis thesis = thesisRepository.findById(dto.thesisId());
        if (thesis == null) {
            throw new EntityNotFoundException("Thesis " + dto.thesisId() + " not found");
        }

        Lecturer supervisor1 = lecturerRepository.findById(dto.firstSupervisorId());
        if (supervisor1 == null) {
            throw new EntityNotFoundException("First supervisor " + dto.firstSupervisorId() + " not found");
        }

        Lecturer supervisor2 = lecturerRepository.findById(dto.secondSupervisorId());
        if (supervisor2 == null) {
            throw new EntityNotFoundException("Second supervisor " + dto.secondSupervisorId() + " not found");
        }

        thesis.setFirstLecturer(supervisor1);
        thesis.setSecondLecturer(supervisor2);
        thesisRepository.persist(thesis);
    }

    @Override
    public List<ThesisItemResponseDto> getTheses(Integer pageNumber, Integer pageSize, String searchStatus) {
        if (pageNumber == null ) {
            pageNumber = 1;
        }
        if (pageSize == null ){
            pageSize = 10;
        }


        var theses = thesisRepository.findAllByStatus(searchStatus, pageNumber, pageSize);
        return theses.stream().map(thesis -> {
            Optional<ThesisStatus> latestStatus = thesis.getThesisStatuses().stream()
                    .max(Comparator.comparing(ThesisStatus::getDate).thenComparing(status -> status.id));

            var dto = ThesisItemResponseDto.builder()
                    .studentName(thesis.getStudent().getFullName())
                    .title(thesis.getTitle())
                    .abstractField(thesis.getAbstractField())
                    .id(thesis.id)
                    .supervisorName1(thesis.getFirstLecturer() == null ? "You haven't chosen a supervisor 1 yet" : thesis.getFirstLecturer().getFullName())
                    .supervisorName2(thesis.getSecondLecturer() == null ? "You haven't chosen a supervisor 2 yet" : thesis.getFirstLecturer().getFullName());

            latestStatus.ifPresent(status -> dto.status(status.getStatus().toString()));

            return dto.build();
    }).toList();
    }

    @Override
    @Transactional
    public void updateStatusThesis(ThesisStatusRequestDto dto) {
        var thesis = thesisRepository.findById(dto.thesisId());
        if (thesis == null) {
            throw new EntityNotFoundException("Thesis " + dto.thesisId() + " not found");
        }

        var thesisStatus = ThesisStatus.builder()
                .date(LocalDate.now())
                .status(ThesisStatusEnum.valueOf(dto.status().toUpperCase()))
                .description(dto.description())
                .thesis(thesis)
                .build();
        thesisStatusRepository.persist(thesisStatus);
    }


}
