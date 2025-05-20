package efub.assignment.community.board.dto.response;

import efub.assignment.community.board.domain.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardResponseDto {
    private Long boardId;
    private String name;
    private String description;
    private String notice;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .boardId(board.getBoardId())
                .name(board.getName())
                .description(board.getDescription())
                .notice(board.getNotice())
                .ownerId(board.getOwner().getMemberId())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();

    }
}
