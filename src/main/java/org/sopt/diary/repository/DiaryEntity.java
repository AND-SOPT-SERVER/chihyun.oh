package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.sopt.diary.constant.ErrorMessage;
import org.sopt.diary.dto.Diary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class DiaryEntity {
    private static final int MAX_CONTENT_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @CreatedDate
    protected LocalDateTime createdAt;

    protected DiaryEntity() {
    }

    private DiaryEntity(String title, String content) {
        validateContentLength(content);

        this.title = title;
        this.content = content;
    }

    public static DiaryEntity toCreateDiaryEntity(Diary diary) {
        return new DiaryEntity(
                diary.getTitle(),
                diary.getContent()
        );
    }

    private void validateContentLength(String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.CONTENT_LENGTH_OVER.getMessage());
        }
    }

    public Long getId() {
        return id;
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

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        validateContentLength(content);

        this.content = content;
    }
}
