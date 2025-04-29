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
public class MemberCommentResponseDto {

    private final Long memberId;
    private final List<CommentResponseDto> commentList;
    private final Long count;

    public static MemberCommentResponseDto from(Long memberId, List<Comment> commentList) {
        return MemberCommentResponseDto.builder()
                .memberId(memberId)
                .commentList(commentList.stream().map(CommentResponseDto::from).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();
    }
}
