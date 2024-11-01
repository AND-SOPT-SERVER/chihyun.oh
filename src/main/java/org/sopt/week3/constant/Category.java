package org.sopt.week3.constant;

import java.util.Arrays;
import org.sopt.week3.exception.diary.DiaryErrorCode;
import org.sopt.week3.exception.diary.DiaryException;

public enum Category {
    FOOD("음식"),
    SCHOOL("학교"),
    MOVIE("영화"),
    EXERCISE("운동");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public static Category getCategoryByName(final String name) {
        return Arrays.stream(values())
                .filter(category -> category.name.equals(name))
                .findFirst()
                .orElseThrow(
                        () -> new DiaryException(DiaryErrorCode.CATEGORY_MISMATCH)
                );
    }

    public String getName() {
        return name;
    }
}
