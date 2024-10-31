package org.sopt.week3.constant;

import java.util.Arrays;

public enum DiarySortColumn {
    CREATED_AT("createdAt"),
    CONTENT_LENGTH("content_length");

    private final String name;

    DiarySortColumn(final String name) {
        this.name = name;
    }

    public static DiarySortColumn getSortColumnByCriteria(final String criteria) {
        return Arrays.stream(values())
                .filter(column -> column.name.equals(criteria))
                .findAny()
                .orElse(CREATED_AT);
    }

    public String getName() {
        return name;
    }
}
