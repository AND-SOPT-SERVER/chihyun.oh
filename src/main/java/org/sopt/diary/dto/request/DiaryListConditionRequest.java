package org.sopt.diary.dto.request;

import java.util.HashMap;
import java.util.Map;

public class DiaryListConditionRequest {
    private final String category;

    public DiaryListConditionRequest(String category) {
        this.category = category;
    }

    public Map<String, Object> getConditions() {
        Map<String, Object> conditions = new HashMap<>();

        if (category != null) {
            conditions.put("category", category);
        }

        return conditions;
    }
}
