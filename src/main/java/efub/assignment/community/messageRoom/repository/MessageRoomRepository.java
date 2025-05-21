package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    List<MessageRoom> findAllByCreaterMemberId(Long memberId);

    Optional<MessageRoom> findByCreaterAndReceiverAndPost(Member creater, Member receiver, Post post);

    List<MessageRoom> post(Post post);
}
