package efub.assignment.community.notification.controller;

import efub.assignment.community.notification.dto.response.NotificationListResponseDto;
import efub.assignment.community.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // 알림 목록 조회
    @GetMapping("{memberId}")
    public ResponseEntity<NotificationListResponseDto> getNotifications(@PathVariable("memberId") long memberId) {
        NotificationListResponseDto responseDto = notificationService.getNotifications(memberId);
        return ResponseEntity.ok(responseDto);
    }
}
