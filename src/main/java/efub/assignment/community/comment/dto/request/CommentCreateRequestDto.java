package efub.assignment.community.comment.dto.request;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.validation.constraints.NotNull;

public record CommentCreateRequestDto (@NotNull Long commenterId,
                                       @NotNull String content){

    public Comment toEntity(Post post, Member member) {
        return Comment.builder()
                .post(post)
                .commenter(member)
                .content(content)
                .build();
    }
}
