package efub.assignment.community.message.repository;

import efub.assignment.community.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMessageRoomMessageRoomId(long messageRoomId);
}
