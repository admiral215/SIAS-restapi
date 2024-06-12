package org.sttdb.services;

import org.sttdb.dto.lecturer.LecturerItemResponseDto;
import org.sttdb.dto.lecturer.LecturerRegistrationRequestDto;
import org.sttdb.dto.lecturer.LecturerUpdateRequestDto;

import java.util.List;

public interface LecturerService {
    List<LecturerItemResponseDto> getAllActiveLecturers(Integer pageNumber, Integer pageSize, String name);

    LecturerItemResponseDto getLecturerById(String lecturerId);

    void addLecturer(LecturerRegistrationRequestDto dto);

    void updateLecturer(LecturerUpdateRequestDto dto);
    
    void deleteLecturer(String lecturerId);

    void inactiveLecturer(String id);
}
