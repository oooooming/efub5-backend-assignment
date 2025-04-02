package efub.assignment.community.member.controller;

import efub.assignment.community.member.dto.MemberRequestDto;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 생성 POST /members
    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.createMember(memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    }

    // 회원 조회 GET /members/{memberId}
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("memberId") Long memberId) {
        MemberResponseDto memberResponseDto = memberService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    // 닉네임 수정 PATCH /members/profile/{memberId}
    @PatchMapping("/profile/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody @Valid MemberRequestDto memberRequestDto,
                                                            @PathVariable("memberId") Long memberId) {
        MemberResponseDto memberResponseDto = memberService.updateMember(memberId, memberRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    // 회원 탈퇴 (상태 변경) PATCH /members/{memberId}
    @PatchMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
