package org.sttdb.entities;

import lombok.Getter;

@Getter
public enum ThesisStatusEnum {
    SUBMITTED("Submitted"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted");

    private final String label;

    ThesisStatusEnum(String label) {
        this.label = label;
    }
}
