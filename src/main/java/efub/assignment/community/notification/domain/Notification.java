package efub.assignment.community.notification.domain;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    // 게시판 이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = true)
    private Board board;

    // 댓글 내용
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;

    // 쪽지방 내용
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = true)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Notification(Board board, Comment comment, Message message, Member member) {
        this.board = board;
        this.comment = comment;
        this.message = message;
        this.member = member;
    }
}
