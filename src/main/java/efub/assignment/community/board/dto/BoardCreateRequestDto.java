package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public record BoardCreateRequestDto(@NotBlank String name,
                                    @Nullable String description,
                                    @Nullable String notice,
                                    @NotNull Long ownerId) { // record -> 불변성 보장, DTO에 적합

    public Board toEntity(Member member) {
        return Board.builder()
                .name(name)
                .description(description)
                .notice(notice)
                .owner(member)
                .build();

    }
}
