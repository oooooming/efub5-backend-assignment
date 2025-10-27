package efub.assignment.community.member.controller;

import efub.assignment.community.global.utils.SecurityUtils;
import efub.assignment.community.member.dto.request.TokenRequestDto;
import efub.assignment.community.member.dto.response.TokenResponseDto;
import efub.assignment.community.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    //현재 인증된 사용자 이메일 조회
    @GetMapping("/me")
    public ResponseEntity<String> getEmail(){
        return ResponseEntity.status(HttpStatus.OK).body(SecurityUtils.getCurrentUserEmail());
    }

    /**
     * 액세스토큰 재발급 처리 메서드
     */
    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> reissuedAccessToken(@RequestBody TokenRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.reissueAccessToken(requestDto.getRefreshToken()));
    }

}