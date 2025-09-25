package efub.assignment.community.comment.domain;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.post.domain.Post;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    // 댓글 수정 테스트
    @Test
    void updateContent_shouldChangeContent() {

        // given
        Member commenter = Member.builder()
                .studentId("2217022")
                .university("이화여자대학교")
                .nickname("ming")
                .email("ming@example.com")
                .password("ming1234!")
                .build();

        Post post = Post.builder()
                .postId(2L)
                .author(commenter)
                .content("강아지 최고")
                .anonymous(true)
                .board(null)
                .build();

        Comment comment = Comment.builder()
                .post(post)
                .commenter(commenter)
                .content("송이 보고싶다")
                .build();

        // when
        comment.updateContent("송송이 보고싶다~~");

        // then
        assertThat(comment.getContent()).isEqualTo("송송이 보고싶다~~");
    }
}