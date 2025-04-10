package efub.assignment.community.post.domain;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Post extends BaseEntity {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    // 게시판
    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    // 작성자
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member author;

    // 익명
    @Column(nullable = false)
    private boolean anonymous;

    // 내용
    @Column(nullable = false)
    private String content;

    @Builder
    private Post (Long postId, Board board, Member author, boolean anonymous, String content) {
        this.postId = postId;
        this.board = board;
        this.author = author;
        this.anonymous = anonymous;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
