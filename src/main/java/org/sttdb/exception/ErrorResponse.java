package org.sttdb.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record ErrorResponse(int status, String message) {
}
