package efub.assignment.community.messageRoom.dto.request;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageRoomCreateRequestDto(@NotNull Long createrId,
                                          @NotNull Long receiverId,
                                          @NotBlank String content,
                                          @NotNull Long postId) {

    public MessageRoom toEntity(Member creater, Member receiver, Post post) {
        return MessageRoom.builder()
                .creater(creater)
                .receiver(receiver)
                .content(content)
                .post(post)
                .build();
    }
}
