package efub.assignment.community.messageRoom.repository;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long> {
    List<MessageRoom> findAllByCreaterMemberId(Long memberId);

    Optional<MessageRoom> findByCreaterAndReceiverAndPost(Member creater, Member receiver, Post post);
}
