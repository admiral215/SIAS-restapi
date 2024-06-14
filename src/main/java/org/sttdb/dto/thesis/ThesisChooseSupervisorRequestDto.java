package org.sttdb.dto.thesis;

public record ThesisChooseSupervisorRequestDto(
        Long thesisId,
        String firstSupervisorId,
        String secondSupervisorId
) {
}
