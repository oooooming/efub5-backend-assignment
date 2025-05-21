package efub.assignment.community.message.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageRoom_id", nullable = false)
    private MessageRoom messageRoom;

    // 보낸사람
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member sender;

    // 쪽지 내용
    @Column(nullable = false)
    private String content;

    public Message(MessageRoom messageRoom, Member sender, String content) {
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.content = content;
    }

}
