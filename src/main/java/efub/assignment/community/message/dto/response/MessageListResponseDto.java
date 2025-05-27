package efub.assignment.community.message.dto.response;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MessageListResponseDto {
    private List<MessageResponseDto> messages;
    private long count;
    private long memberId;

    public static MessageListResponseDto from(List<Message> messages, Member member) {
        return MessageListResponseDto.builder()
                .memberId(member.getMemberId())
                .count(messages.size())
                .messages(messages.stream()
                        .map(message -> MessageResponseDto.from(member, message))
                        .collect(Collectors.toList()))
                .build();
    }
}
