package efub.assignment.community.message.dto.resquest;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotNull;

public record MessageCreateRequestDto(@NotNull long messageRoomId,
                                      @NotNull long senderId,
                                      @NotNull String content) {
    public Message toEntity(MessageRoom messageRoom, Member sender) {
        return Message.builder()
                .messageRoom(messageRoom)
                .sender(sender)
                .content(content)
                .build();
    }
}
