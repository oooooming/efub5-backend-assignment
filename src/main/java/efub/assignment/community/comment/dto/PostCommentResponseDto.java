package efub.assignment.community.comment.dto;

import efub.assignment.community.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCommentResponseDto {

    private final Long postId;
    private final List<CommentResponseDto> commentList;
    private final Long count;

    public static PostCommentResponseDto from(Long postId, List<Comment> commentList) {
        return PostCommentResponseDto.builder()
                .postId(postId)
                .commentList(commentList.stream().map(CommentResponseDto::from).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();
    }
}
