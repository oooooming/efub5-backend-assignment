package efub.assignment.community.post.repository;

import efub.assignment.community.post.domain.Post;

import java.util.List;

public interface CustomPostRepository {
    List<Post> search(String keyword, String writerNickname, String boardName);
}
