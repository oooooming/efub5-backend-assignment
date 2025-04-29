package efub.assignment.community.post.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateRequestDto(@NotNull Long boardId,
                                  @NotNull boolean anonymous,
                                  @NotNull Long authorId,
                                  @NotBlank String content) {
    public Post toEntity(Board board, Member author) {
        return Post.builder()
                .board(board)
                .anonymous(anonymous)
                .author(author)
                .content(content)
                .build();
    }
}
