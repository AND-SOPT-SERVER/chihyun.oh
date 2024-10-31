package org.sopt.week3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.sopt.week3.constant.Category;

/**
 *
 */

@Entity
@Table(name = "diary")
public class DiaryEntity {

    private static final int MAX_TITLE_LENGTH = 10;
    private static final int MAX_CONTENT_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(name = "content", nullable = false, length = MAX_CONTENT_LENGTH)
    private String content;

    @Column(name = "category", nullable = false)
    private Category category;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "is_share")
    private boolean isShare;

    protected DiaryEntity() {
    }

    public DiaryEntity(final String title, final String content, final Category category, final UserEntity user,
                       final boolean isShare) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
        this.isShare = isShare;
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
}
