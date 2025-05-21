package efub.assignment.community.messageRoom.domain;

import efub.assignment.community.global.domain.BaseEntity;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messageRooms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long messageRoomId;

    // 처음 생성한 사람
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creater_id", nullable = false)
    private Member creater;

    // 처음 받은 사람
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    // 처음 쪽지 내용
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Builder
    public MessageRoom(Member creater, Member receiver, String content, Post post, List<Message> messages) {
        this.creater = creater;
        this.receiver = receiver;
        this.content = content;
        this.post = post;
        this.messages = messages != null ? messages : new ArrayList<>();
    }

}
