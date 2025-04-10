package efub.assignment.community.post.dto;

import efub.assignment.community.post.domain.Post;

import java.util.List;

public record PostListResponseDto(Long boardId,
                                  int count,
                                  List<PostResponseDto> posts) {

    public static PostListResponseDto from(Long boardId, List<Post> posts) {
        List<PostResponseDto> postDtos = posts.stream()
                .map(PostResponseDto::from)
                .toList();

        return new PostListResponseDto(boardId, postDtos.size(), postDtos);
    }
}
