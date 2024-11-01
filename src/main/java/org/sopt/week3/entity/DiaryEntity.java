package org.sopt.week3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.sopt.week3.constant.Category;
import org.sopt.week3.dto.diary.DiaryDTO;
import org.sopt.week3.exception.diary.DiaryErrorCode;
import org.sopt.week3.exception.diary.DiaryException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "diary")
public class DiaryEntity {

    private static final int MAX_TITLE_LENGTH = 10;
    private static final int MAX_CONTENT_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(nullable = false, length = MAX_CONTENT_LENGTH)
    private String content;

    @Column(nullable = false)
    private int contentLength;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Boolean 타입은 JPA 자동 명명 규칙에 따라 tinyint(1)로 저장된다.
    // 반면, boolean 원시 타입은 bit(1)로 저장되어 1,0 의 값을 갖지만, 조회 시 TRUE/FALSE로 동작하지 않는다.
    @Column(nullable = false)
    private Boolean isShare;

    @CreatedDate
    private LocalDateTime createdAt;

    @PrePersist
    @PreUpdate
    private void updateContentLength() {
        contentLength = content.length();
    }

    protected DiaryEntity() {
    }

    private DiaryEntity(final String title, final String content, final Category category, final UserEntity user,
                        final boolean isShare) {
        validateTitleLength(title);
        validateContentLength(content);

        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
        this.isShare = isShare;
    }

    public static DiaryEntity toDiaryEntity(final DiaryDTO diaryDTO, final UserEntity user) {
        return new DiaryEntity(
                diaryDTO.title(),
                diaryDTO.content(),
                diaryDTO.category(),
                user,
                diaryDTO.isShare()
        );
    }

    private void validateTitleLength(final String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new DiaryException(DiaryErrorCode.OVER_LENGTH_LIMIT);
        }
    }

    private void validateContentLength(final String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new DiaryException(DiaryErrorCode.OVER_LENGTH_LIMIT);
        }
    }

    public void setTitle(String title) {
        validateTitleLength(title);
        this.title = title;
    }

    public void setContent(String content) {
        validateContentLength(content);
        this.content = content;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setShare(Boolean share) {
        isShare = share;
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

    public Category getCategory() {
        return category;
    }

    public UserEntity getUser() {
        return user;
    }

    public boolean isShare() {
        return isShare;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
