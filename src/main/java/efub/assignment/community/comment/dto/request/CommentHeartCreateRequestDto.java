package efub.assignment.community.comment.dto.request;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.domain.CommentHeart;
import efub.assignment.community.member.domain.Member;
import jakarta.validation.constraints.NotNull;

public record CommentHeartCreateRequestDto(@NotNull Long memberId){

    public CommentHeart toEntity(Member member, Comment comment) {
        return CommentHeart.builder()
                .member(member)
                .comment(comment)
                .build();
    }
}
