package efub.assignment.community.comment.controller;

import efub.assignment.community.comment.dto.CommentCreateRequestDto;
import efub.assignment.community.comment.dto.CommentResponseDto;
import efub.assignment.community.comment.dto.MemberCommentResponseDto;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/comments")
public class MemberCommentController {

    private final CommentService commentService;

    // 작성자별 댓글 조회
    @GetMapping
    public ResponseEntity<MemberCommentResponseDto> getMemberComments(@PathVariable Long memberId) {
        return ResponseEntity.ok(commentService.getMemberCommentList(memberId));
    }
}
