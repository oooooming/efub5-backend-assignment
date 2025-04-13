package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostResponseDto {
    private Long postId;
    private Long boardId;
    private Long authorId;
    private boolean anonymous;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .boardId(post.getBoard().getBoardId())
                .authorId(post.getAuthor().getMemberId())
                .anonymous(post.isAnonymous())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
