package efub.assignment.community.member.dto;

import efub.assignment.community.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private String studentId;
    private String university;
    private String nickname;
    private String email;

    public static MemberResponseDto from(Member member) { // db에서 member를 받아서 dto 꼴로 바꿔줌
        return MemberResponseDto.builder()
                .studentId(member.getStudentId())
                .university(member.getUniversity())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
    }
}
