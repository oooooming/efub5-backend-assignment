package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.dto.CommentCreateRequestDto;
import efub.assignment.community.comment.dto.CommentResponseDto;
import efub.assignment.community.comment.dto.PostCommentResponseDto;
import efub.assignment.community.comment.dto.UpdateCommentDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class PostCommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId,
                                                            @Valid @RequestBody CommentCreateRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.createComment(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateContent(@PathVariable("postId") Long commentId,
                                                            @Valid @RequestBody UpdateCommentDto requestDto) {
        CommentResponseDto responseDto = commentService.updateContent(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 게시글별 댓글 조회
    @GetMapping
    public ResponseEntity<PostCommentResponseDto> getPostComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getPostCommentList(postId));
    }
}
