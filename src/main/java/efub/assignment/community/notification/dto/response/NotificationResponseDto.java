package efub.assignment.community.notification.dto.response;

import efub.assignment.community.notification.domain.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponseDto {
    private String name;
    private String type;
    private String commentContent;
    private String messageContent;
    private LocalDateTime createdAt;

    public static NotificationResponseDto from(Notification notification) {

        // null이어도 notifiaction 생성
        String commentContent = null;
        if (notification.getComment() != null) {
            commentContent = notification.getComment().getContent();
        }
        String messageContent = null;
        if (notification.getMessage() != null) {
            messageContent = notification.getMessage().getContent();
        }

        return NotificationResponseDto.builder()
                .name(notification.getComment() != null ? notification.getBoard().getName() : null)
                .commentContent(commentContent)
                .type(notification.getComment() != null ? "댓글" : "쪽지")
                .messageContent(messageContent)
                .createdAt(notification.getComment() != null
                        ? notification.getComment().getCreatedAt()
                        : notification.getMessage().getCreatedAt())
                .build();
    }
}
