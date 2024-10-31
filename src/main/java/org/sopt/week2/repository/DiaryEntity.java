package org.sopt.week2.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import org.sopt.week2.dto.Diary;
import org.sopt.week2.exception.CustomException;
import org.sopt.week2.exception.ErrorCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class DiaryEntity {
    private static final int MAX_CONTENT_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private String title;

    @Column(length = MAX_CONTENT_LENGTH)
    private String content;

    @Column
    private Integer contentLength;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected DiaryEntity() {
    }

    private DiaryEntity(final String category, final String name, final String title, final String content) {
        validateContentLength(content);

        this.category = category;
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public static DiaryEntity toCreateDiaryEntity(Diary diary) {
        return new DiaryEntity(
                diary.category(),
                diary.name(),
                diary.title(),
                diary.content()
        );
    }

    @PrePersist
    @PreUpdate
    void saveContentLength() {
        contentLength = content.length();
    }

    private void validateContentLength(String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new CustomException(ErrorCode.CONTENT_LENGTH_OVER);
        }
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        validateContentLength(content);

        this.content = content;
    }
}
