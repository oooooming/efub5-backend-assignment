package efub.assignment.community.comment.dto.response;

import efub.assignment.community.comment.domain.CommentHeart;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentHeartResponseDto {
    private Long commentHeartId;
    private Long memberId;
    private Long commentId;
    private LocalDateTime createdAt;

    public static CommentHeartResponseDto from(CommentHeart commentHeart) {
        return CommentHeartResponseDto.builder()
                .commentHeartId(commentHeart.getCommentHeartId())
                .memberId(commentHeart.getMember().getMemberId())
                .commentId(commentHeart.getComment().getCommentId())
                .createdAt(commentHeart.getCreatedAt())
                .build();
    }
}
