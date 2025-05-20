package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.dto.request.CommentHeartCreateRequestDto;
import efub.assignment.community.comment.dto.response.CommentHeartResponseDto;
import efub.assignment.community.comment.dto.response.CommentResponseDto;
import efub.assignment.community.comment.dto.request.UpdateCommentDto;
import efub.assignment.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/{commentId}")
public class CommentController {

    private final CommentService commentService;

    // 댓글 수정
    @PatchMapping
    public ResponseEntity<CommentResponseDto> updateContent(@PathVariable("commentId") Long commentId,
                                                            @Valid @RequestBody UpdateCommentDto requestDto) {
        CommentResponseDto responseDto = commentService.updateContent(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 댓글 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("성공적으로 댓글 삭제가 완료되었습니다.");
    }

    // 댓글 좋아요 생성
    @PostMapping("/commentHearts")
    public ResponseEntity<CommentHeartResponseDto> createCommentHeart(@PathVariable("commentId") Long commentId,
                                                                 @RequestBody @Valid CommentHeartCreateRequestDto requestDto) {
        CommentHeartResponseDto responseDto = commentService.createCommentHeart(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 댓글 좋아요 삭제
    @DeleteMapping("/commentHearts/{commentHeartId}")
    public ResponseEntity<String> deleteCommentHeart(@PathVariable("commentHeartId") Long commentHeartId) {
        commentService.deleteCommentHeart(commentHeartId);
        return ResponseEntity.ok("댓글 좋아요가 성공적으로 삭제되었습니다.");
    }
}
