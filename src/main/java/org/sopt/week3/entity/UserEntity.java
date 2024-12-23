package org.sopt.week3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import org.sopt.week3.dto.user.UserDTO;

/**
 * 일기 공유 서비스를 이용하는 사용자 엔티티 클래스입니다. 아이디는 nickname, 비밀번호는 password 입니다.
 */

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<DiaryEntity> diaries;

    protected UserEntity() {
    }

    private UserEntity(final String username, final String password, final String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static UserEntity toUserEntity(final UserDTO userDTO) {
        return new UserEntity(
                userDTO.username(),
                userDTO.password(),
                userDTO.nickname()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordCorrect(final String password) {
        return this.password.equals(password);
    }

    public String getNickname() {
        return nickname;
    }
}
