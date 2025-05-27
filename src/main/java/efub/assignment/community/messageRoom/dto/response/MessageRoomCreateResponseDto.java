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

    private Long messageRoomId;
    private Long createrId;
    private Long receiverId;
    private String content;
    private Long postId;
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
