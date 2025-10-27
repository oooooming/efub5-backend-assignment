package efub.assignment.community.member.service;

import efub.assignment.community.global.jwt.TokenProvider;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.response.TokenResponseDto;
import efub.assignment.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * AccessToken 재발급
     *
     * 클라이언트가 전달한 리프레시 토큰을 검증해 유효한 경우 새로운 액세스토큰을 발급한다.
     */
    public TokenResponseDto reissueAccessToken(String refreshToken){

        //전달받은 리프레시 토큰에서 이메일을 추출하여 사용자 정보 가져오기
        String email = tokenProvider.extractEmail(refreshToken);
        Member member = getUserByEmail(email);

        // Redis에서 해당 사용자 Id를 키로 하는 리프래시 토큰 가져오기
        String storedRefreshToken = redisTemplate.opsForValue().get(member.getMemberId().toString());

        //전달받은 리프레시 토큰과 Redis에 저장된 리프레시 토큰이 일하는지 확인
        if(storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)){
            throw new IllegalArgumentException("유효하지 않은 리 프레시 토큰입니다.");
        }

        //일치한다면 새로운 AccessToken 생성
        String accessToken = tokenProvider.createAccessToken(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    /**
     * Email로 사용자 객체 가져오기
     */
    @Transactional(readOnly = true)
    public Member getUserByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일로 사용자를 찾을 수 없습니다."));
    }

}
