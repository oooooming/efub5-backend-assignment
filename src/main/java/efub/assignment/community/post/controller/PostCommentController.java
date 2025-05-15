package efub.assignment.community.post.controller;

import efub.assignment.community.comment.dto.request.CommentCreateRequestDto;
import efub.assignment.community.comment.dto.response.CommentResponseDto;
import efub.assignment.community.comment.dto.response.PostCommentResponseDto;
import efub.assignment.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId,
                                                            @Valid @RequestBody CommentCreateRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.createComment(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 게시글별 댓글 조회
    @GetMapping
    public ResponseEntity<PostCommentResponseDto> getPostComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getPostCommentList(postId));
    }
}
