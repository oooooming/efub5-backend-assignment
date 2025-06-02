package efub.assignment.community.comment.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.domain.CommentHeart;
import efub.assignment.community.comment.dto.request.CommentCreateRequestDto;
import efub.assignment.community.comment.dto.request.CommentHeartCreateRequestDto;
import efub.assignment.community.comment.dto.request.UpdateCommentDto;
import efub.assignment.community.comment.dto.response.CommentHeartResponseDto;
import efub.assignment.community.comment.dto.response.CommentResponseDto;
import efub.assignment.community.comment.dto.response.MemberCommentResponseDto;
import efub.assignment.community.comment.dto.response.PostCommentResponseDto;
import efub.assignment.community.comment.repository.CommentHeartRepository;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.notification.domain.Notification;
import efub.assignment.community.notification.repository.NotificationRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentHeartRepository commentHeartRepository;
    private final NotificationRepository notificationRepository;

    // 댓글 생성
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentCreateRequestDto requestDto) {
        Member commenter = memberRepository.findByMemberId(requestDto.commenterId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Comment newComment = requestDto.toEntity(post, commenter);
        commentRepository.save(newComment);

        // 게시글 작성자에게 알림 생성
        Board board = newComment.getPost().getBoard();
        Member boardOwner = board.getOwner();

        Notification notification = Notification.builder()
                .board(board)
                .comment(newComment)
                .member(boardOwner)
                .build();

        notificationRepository.save(notification);

        return CommentResponseDto.from(newComment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateContent(Long commentId, UpdateCommentDto requestDto) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 댓글입니다."));
        String newContent = requestDto.getContent();
        comment.updateContent(newContent);

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.from(savedComment);
    }

    // 게시글별 댓글 조회
    @Transactional
    public PostCommentResponseDto getPostCommentList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostPostIdOrderByCreatedAt(postId);
        return PostCommentResponseDto.from(postId, commentList);
    }

    // 작성자별 댓글 조회
    @Transactional(readOnly = true)
    public MemberCommentResponseDto getMemberCommentList(Long memberId) {
        List<Comment> commentList = commentRepository.findAllByCommenterMemberIdOrderByCreatedAtDesc(memberId);
        return MemberCommentResponseDto.from(memberId, commentList);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }

    // 댓글 좋아요 생성
    @Transactional
    public CommentHeartResponseDto createCommentHeart(Long commentId, CommentHeartCreateRequestDto requestDto) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        Member member = memberRepository.findByMemberId(requestDto.memberId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 중복 좋아요 방지
        commentHeartRepository.findByMemberAndComment(member, comment)
                .ifPresent(heart -> {
                    throw new IllegalStateException("이미 해당 댓글에 좋아요를 눌렀습니다.");
                });

        CommentHeart commentHeart = requestDto.toEntity(member, comment);
        CommentHeart savedCommentHeart = commentHeartRepository.save(commentHeart);

        return CommentHeartResponseDto.from(savedCommentHeart);
    }

    // 댓글 좋아요 삭제
    @Transactional
    public void deleteCommentHeart(Long commentHeartId) {
        commentHeartRepository.deleteById(commentHeartId);
    }
}
