package efub.assignment.community.messageRoom.dto.request;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import jakarta.validation.constraints.NotNull;

public record existMessageRoomRequestDto(@NotNull long receiverId,
                                         @NotNull long postId) {
}
