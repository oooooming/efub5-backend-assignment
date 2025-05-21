package efub.assignment.community.notification.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.dto.response.NotificationListResponseDto;
import efub.assignment.community.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    // 알림 목록 조회
    public NotificationListResponseDto getNotifications(long memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()->new IllegalArgumentException("member not exist"));
        List<Notification> notifications = notificationRepository.findAllByMemberMemberId(memberId);
        return NotificationListResponseDto.from(member, notifications);
    }
}
