package efub.assignment.community.comment.repository;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.domain.CommentHeart;
import efub.assignment.community.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    Optional<CommentHeart> findByMemberAndComment(Member member, Comment comment);
}
