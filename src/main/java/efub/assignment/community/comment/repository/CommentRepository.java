package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCommentId(Long commentId);
    List<Comment> findAllByPostPostIdOrderByCreatedAt(Long postId);
    List<Comment> findAllByCommenterMemberIdOrderByCreatedAtDesc(Long memberId);
    void deleteByCommentId(Long commentId);
}
