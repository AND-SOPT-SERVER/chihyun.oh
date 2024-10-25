package org.sopt.diary.dto.request;

import java.util.HashMap;
import java.util.Map;

public record DiaryListConditionRequest(
        String category
) {
    public Map<String, Object> getConditions() {
        Map<String, Object> conditions = new HashMap<>();

        if (category != null) {
            conditions.put("category", category);
        }

        return conditions;
    }
}