package com.project.parking.data.defaults;

public enum StatusValue {

    ENABLED(1L),
    DISABLED(2L),

    PENDING(3L),

    CONSUMED(4L)

    ;

    private final Long value;

    StatusValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }

}
