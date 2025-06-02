package efub.assignment.community.messageRoom.dto.response;

import efub.assignment.community.messageRoom.domain.MessageRoom;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MessageRoomListResponseDto {
    private List<MessageRoomResponseDto> messageRooms;
    private Long count;
    private Long memberId;

    public static MessageRoomListResponseDto from(Long memberId, List<MessageRoom> messageRooms) {
        return MessageRoomListResponseDto.builder()
                .memberId(memberId)
                .count((long) messageRooms.size())
                .messageRooms(messageRooms.stream().map(MessageRoomResponseDto::from).collect(Collectors.toList()))
                .build();
    }
}
