package efub.assignment.community.message.dto.response;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponseDto {
    private String content;
    private LocalDateTime createdAt;
    private boolean isSender;

    public static MessageResponseDto from(Member member, Message message) {
        boolean isSender = message.getSender().getMemberId().equals(member.getMemberId());
        return MessageResponseDto.builder()
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .isSender(isSender)
                .build();
    }
}
