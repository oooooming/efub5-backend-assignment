package efub.assignment.community.post.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.dto.PostCreateRequestDto;
import efub.assignment.community.post.dto.PostListResponseDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.dto.UpdateContentDto;
import efub.assignment.community.post.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 생성
    public PostResponseDto createPost(@Valid PostCreateRequestDto postCreateRequestDto) {
        Board board = boardRepository.findByBoardId(postCreateRequestDto.boardId())
                .orElseThrow(() -> new RuntimeException("Board not found"));
        Member author = memberRepository.findByMemberId(postCreateRequestDto.authorId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Post post = postCreateRequestDto.toEntity(board, author);
        Post savedPost = postRepository.save(post);
        return PostResponseDto.from(savedPost);
    }

    // 게시글 수정
    public PostResponseDto updateContent(@PathVariable Long postId, @Valid UpdateContentDto updateContentDto) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        String newContent = updateContentDto.getContent();
        post.updateContent(newContent);
        Post savedPost = postRepository.save(post);
        return PostResponseDto.from(savedPost);
    }

    // 게시글 상세 내용 조회
    public PostResponseDto getPost(@PathVariable Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return PostResponseDto.from(post);
    }

    // 게시글 목록 조회
    public PostListResponseDto getPostList(@PathVariable Long boardId) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        List<Post> postList = postRepository.findAllByBoard(board);
        return PostListResponseDto.from(boardId, postList);
    }

    // 게시글 삭제
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteByPostId(postId);
    }
}
