package efub.assignment.community.comment.service;

import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.dto.*;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentCreateRequestDto requestDto) {
        Member commenter = memberRepository.findByMemberId(requestDto.commenterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Comment newComment = requestDto.toEntity(post, commenter);
        commentRepository.save(newComment);

        return CommentResponseDto.from(newComment);
    }

    // 댓글 수정
    public CommentResponseDto updateContent(Long commentId, UpdateCommentDto requestDto) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 댓글입니다."));
        String newContent = requestDto.getContent();
        comment.updateContent(newContent);

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.from(savedComment);
    }

    // 게시글별 댓글 조회
    public PostCommentResponseDto getPostCommentList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostPostIdOrderByCreatedAt(postId);
        return PostCommentResponseDto.from(postId, commentList);
    }

    // 작성자별 댓글 조회
    public MemberCommentResponseDto getMemberCommentList(Long memberId) {
        List<Comment> commentList = commentRepository.findAllByCommenterMemberIdOrderByCreatedAtDesc(memberId);
        return MemberCommentResponseDto.from(memberId, commentList);
    }

}
