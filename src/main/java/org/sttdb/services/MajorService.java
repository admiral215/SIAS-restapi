package org.sttdb.services;

import org.sttdb.dto.major.MajorItemResponseDto;
import org.sttdb.dto.major.MajorUpsertRequestDto;

import java.util.List;

public interface MajorService {
    List<MajorItemResponseDto> getMajors(Integer pageNumber, Integer pageSize, String name);

    MajorItemResponseDto getMajor(Long majorId);

    void addMajor(MajorUpsertRequestDto dto);

    void updateMajor(MajorUpsertRequestDto dto);

    void deleteMajor(Long majorId);

    void inactiveMajor(Long id);
}
