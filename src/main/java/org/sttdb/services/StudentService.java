package org.sttdb.services;

import org.sttdb.dto.student.StudentItemResponseDto;
import org.sttdb.dto.student.StudentRegistrationRequestDto;
import org.sttdb.dto.student.StudentUpdateRequestDto;

import java.util.List;

public interface StudentService {

    List<StudentItemResponseDto> getAllActiveStudents(Integer pageIndex, Integer pageSize);

    StudentItemResponseDto getStudentById(String id);

    void addStudent(StudentRegistrationRequestDto dto);

    void updateStudent(StudentUpdateRequestDto dto, String id);

    void deleteStudent(String id);

    void inactiveStudent(String id);
}
