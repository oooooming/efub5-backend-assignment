package efub.assignment.community.comment.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.comment.domain.Comment;
import efub.assignment.community.comment.domain.CommentHeart;
import efub.assignment.community.comment.dto.request.CommentHeartCreateRequestDto;
import efub.assignment.community.comment.dto.response.CommentHeartResponseDto;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.comment.repository.CommentRepository;
import efub.assignment.community.comment.repository.CommentHeartRepository;
import efub.assignment.community.notification.repository.NotificationRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentHeartRepository commentHeartRepository;
    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private CommentService commentService;

    private Member member;
    private Board board;
    private Post post;
    private Comment comment;

    @BeforeEach
    void setUp() throws Exception {
        member = Member.builder()
                .studentId("2217021")
                .university("이화여자대학교")
                .nickname("ming")
                .email("ming@example.com")
                .password("ming1234!")
                .build();

        board = Board.builder()
                .owner(member)
                .description("게시판이다냥")
                .notice("매너부탁이다냥")
                .name("냥게시판")
                .build();

        post = Post.builder()
                .postId(1L)
                .board(board)
                .anonymous(false)
                .author(member)
                .content("고양이귀엽다냥")
                .build();

        comment = Comment.builder()
                .post(post)
                .commenter(member)
                .content("인정이다냥")
                .build();

        // commentId 강제 세팅 (테스트용)
        Field field = Comment.class.getDeclaredField("commentId");
        field.setAccessible(true);
        field.set(comment, 1L);
    }

    // 댓글 생성 테스트
//    @Test
//    void createComment_shouldSaveCommentAndNotification() {
//
//        // given
//        CommentCreateRequestDto requestDto = new CommentCreateRequestDto(member.getMemberId(), "인정이다냥");
//
//        when(memberRepository.findByMemberId(member.getMemberId())).thenReturn(Optional.of(member));
//        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
//        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
//
//        // when
//        CommentResponseDto response =
//                commentService.createComment(post.getPostId(), requestDto);
//
//        // then
//        assertThat(response.getContent()).isEqualTo("인정이다냥");
//        assertThat(response.getCommenterId()).isEqualTo(member.getMemberId());
//
//        verify(commentRepository, times(1)).save(any(Comment.class));
//        verify(notificationRepository, times(1)).save(any(Notification.class));
//    }

    // 댓글 좋아요 생성 테스트

    // 댓글 좋아요 성공 테스트
    @Test
    void 댓글_좋아요_성공() {
        // given
        Long commentId = 1L;
        CommentHeartCreateRequestDto requestDto = new CommentHeartCreateRequestDto(member.getMemberId());

        when(commentRepository.findByCommentId(commentId)).thenReturn(Optional.of(comment));
        when(memberRepository.findByMemberId(member.getMemberId())).thenReturn(Optional.of(member));
        when(commentHeartRepository.findByMemberAndComment(member, comment)).thenReturn(Optional.empty());

        CommentHeart savedHeart = new CommentHeart(comment, member);
        when(commentHeartRepository.save(any(CommentHeart.class))).thenReturn(savedHeart);

        // when
        CommentHeartResponseDto responseDto = commentService.createCommentHeart(commentId, requestDto);

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getCommentId()).isEqualTo(commentId);
        assertThat(responseDto.getMemberId()).isEqualTo(member.getMemberId());
    }

    // 존재하지 않는 댓글 예외
    @Test
    void 존재하지_않는_댓글일_때_예외() {
        // given
        Long commentId = 999L;
        CommentHeartCreateRequestDto requestDto = new CommentHeartCreateRequestDto(member.getMemberId());

        when(commentRepository.findByCommentId(commentId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> commentService.createCommentHeart(commentId, requestDto));
    }

    // 이미 좋아요한 경우 예외
    @Test
    void 이미_좋아요한_경우_예외() {
        // given
        Long commentId = 1L;
        CommentHeartCreateRequestDto requestDto = new CommentHeartCreateRequestDto(member.getMemberId());

        when(commentRepository.findByCommentId(commentId)).thenReturn(Optional.of(comment));
        when(memberRepository.findByMemberId(member.getMemberId())).thenReturn(Optional.of(member));
        when(commentHeartRepository.findByMemberAndComment(member, comment))
                .thenReturn(Optional.of(new CommentHeart(comment, member)));

        // when & then
        assertThrows(IllegalStateException.class,
                () -> commentService.createCommentHeart(commentId, requestDto));
    }
}