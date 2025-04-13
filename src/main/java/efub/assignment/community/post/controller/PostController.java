package efub.assignment.community.post.controller;

import efub.assignment.community.post.dto.PostCreateRequestDto;
import efub.assignment.community.post.dto.PostListResponseDto;
import efub.assignment.community.post.dto.PostResponseDto;
import efub.assignment.community.post.dto.UpdateContentDto;
import efub.assignment.community.post.repository.PostRepository;
import efub.assignment.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostResponseDto postResponseDto = postService.createPost(postCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updateContent(@PathVariable Long postId, @Valid @RequestBody UpdateContentDto updateContentDto) {
        PostResponseDto postResponseDto = postService.updateContent(postId, updateContentDto);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // 게시글 내용 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // 게시글 목록 조회
    @GetMapping("/{boardId}/list")
    public ResponseEntity<PostListResponseDto> getPosts(@PathVariable Long boardId) {
        PostListResponseDto postListResponseDto = postService.getPostList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(postListResponseDto);
    }

    // 게시글 삭제
    @DeleteMapping("{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("성공적으로 게시글 삭제가 완료되었습니다.");
    }
}
