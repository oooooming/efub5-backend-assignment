package efub.assignment.community.notification.dto.response;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.notification.domain.Notification;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class NotificationListResponseDto {
    private List<NotificationResponseDto> notifications;
    private long memberId;
    private long count;

    public static NotificationListResponseDto from(Member member,List<Notification> notifications) {
        return NotificationListResponseDto.builder()
                .notifications(notifications.stream().map(NotificationResponseDto::from).collect(Collectors.toList()))
                .memberId(member.getMemberId())
                .count(notifications.size())
                .build();
    }
}
