package efub.assignment.community.board.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards")
public class Board extends BaseEntity {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    // 게시판 소유자
    @ManyToOne
    @JoinColumn (name = "member_id", nullable = false)
    private Member owner;

    // 게시판 이름
    @Column (nullable = false)
    private String name;

    // 게시판 설명
    @Column (nullable = true)
    private String description;

    // 게시판 공지
    @Column (nullable = true)
    private String notice;

    @Builder
    public Board(Member owner, String description, String notice, String name) {
        this.owner = owner;
        this.description = description;
        this.notice = notice;
        this.name = name;
    }

    public void updateOwner(Member newOwner) {
        this.owner = newOwner;
    }
}
