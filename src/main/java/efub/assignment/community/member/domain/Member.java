package efub.assignment.community.member.domain;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    // 학번
    @Column (nullable = false, length = 20, unique = true)
    private String studentId;

    // 대학
    @Column (nullable = false, length = 100)
    private String university;

    // 닉네임
    @Column (nullable = false)
    private String nickname;

    // 이메일
    @Column (nullable = false, length = 100, unique = true)
    private String email;

    // 비밀번호
    @Column (nullable = false, length = 255)
    private String password;

    // 회원 상태
    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.ACTIVE;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String studentId, String university, String nickname, String email, String password) {
        this.studentId = studentId;
        this.university = university;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.status = MemberStatus.ACTIVE;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }
}
