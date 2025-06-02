package efub.assignment.community.message.dto.response;

import efub.assignment.community.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageCreateResponseDto {
    private long messageId;
    private long messageRoomId;
    private long senderId;
    private String content;
    private LocalDateTime createdAt;

    public static MessageCreateResponseDto from(Message message) {
        return MessageCreateResponseDto.builder()
                .messageId(message.getMessageId())
                .messageRoomId(message.getMessageRoom().getMessageRoomId())
                .senderId(message.getSender().getMemberId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
