package efub.assignment.community.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

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
    @Column (nullable = false, length = 8)
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

    // 생성일
    @Column (nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 수정일
    @Column
    private LocalDateTime updatedAt;

    // 생성 시 시간 저장
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 수정 시 시간 저장
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Member(String studentId, String university, String nickname, String email, String password) {
        this.studentId = studentId;
        this.university = university;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.status = MemberStatus.ACTIVE; // 빌더 패턴은 수정되는 값들로부터 보호하기 위해 사용한다고 세미나에서 말씀해주셨는데, 처음 설정되는 값을 모두 포함시키는 것보다 수정되는 nickname과 status를 포함하지 않는 게 더 나을까?
    }

    public void updateMember(String nickname) {
        this.nickname = nickname;
    }

    public void changeStatus(MemberStatus status) {
        this.status = status;
    }
}
