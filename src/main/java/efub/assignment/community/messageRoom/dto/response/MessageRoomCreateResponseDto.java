package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MessageRoomCreateResponseDto {
    @NotNull
    private Long messageRoomId;
    @NotNull
    private Long createrId;
    @NotNull
    private Long receiverId;
    @NotBlank
    private String content;
    @NotNull
    private Long postId;
    @NotNull
    private LocalDateTime createdAt;

    public static MessageRoomCreateResponseDto from(MessageRoom messageRoom) {
        return MessageRoomCreateResponseDto.builder()
                .messageRoomId(messageRoom.getMessageRoomId())
                .createrId(messageRoom.getCreater().getMemberId())
                .receiverId(messageRoom.getReceiver().getMemberId())
                .content(messageRoom.getContent())
                .postId(messageRoom.getPost().getPostId())
                .createdAt(messageRoom.getCreatedAt())
                .build();
    }
}
