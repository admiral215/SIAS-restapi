package org.sttdb.services;

import org.sttdb.dto.thesis.ThesisStatusRequestDto;
import org.sttdb.dto.thesis.ThesisChooseSupervisorRequestDto;
import org.sttdb.dto.thesis.ThesisItemResponseDto;
import org.sttdb.dto.thesis.ThesisUpsertRequestDto;

import java.util.List;

public interface ThesisService {
    void insertThesis(ThesisUpsertRequestDto dto);

    void updateThesis(ThesisUpsertRequestDto dto);

    void chooseSupervisor(ThesisChooseSupervisorRequestDto dto);

    List<ThesisItemResponseDto> getTheses(Integer pageNumber, Integer pageSize ,String status);

    void updateStatusThesis(ThesisStatusRequestDto dto);
}
