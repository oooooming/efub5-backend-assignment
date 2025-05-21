package efub.assignment.community.messageRoom.dto.request;

import jakarta.validation.constraints.NotNull;

public record existMessageRoomRequestDto(@NotNull long receiverId,
                                         @NotNull long postId) {
}
