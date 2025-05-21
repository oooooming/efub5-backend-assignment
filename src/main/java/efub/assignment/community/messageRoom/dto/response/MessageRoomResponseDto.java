package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
@Builder
public class MessageRoomResponseDto {
    private Long messageRoomId;
    private String latestMessage;
    private LocalDateTime latestMessageTime;

    public static MessageRoomResponseDto from(MessageRoom messageRoom) {
        // 최신 메시지 가져오기
        Message latestMessage = messageRoom.getMessages().stream()
                .max(Comparator.comparing(Message::getCreatedAt))
                .orElse(null);

        return MessageRoomResponseDto.builder()
                .messageRoomId(messageRoom.getMessageRoomId())
                .latestMessage(latestMessage != null ? latestMessage.getContent() : "(대화 없음)")
                .latestMessageTime(latestMessage != null ? latestMessage.getCreatedAt() : null)
                .build();
    }
}
