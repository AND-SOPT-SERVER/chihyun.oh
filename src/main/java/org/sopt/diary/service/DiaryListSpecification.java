package org.sopt.diary.service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.sopt.diary.repository.DiaryEntity;
import org.springframework.data.jpa.domain.Specification;

public class DiaryListSpecification {
    public static Specification<DiaryEntity> searchDiaryList(Map<String, Object> searchKey) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String key : searchKey.keySet()) {
                predicates.add(criteriaBuilder.equal(root.get(key), searchKey.get(key)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
