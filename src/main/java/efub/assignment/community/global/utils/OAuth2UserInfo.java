package efub.assignment.community.global.utils;

import java.util.Map;

public class OAuth2UserInfo {

    private Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    private Map<String, Object> getKakaoAccount() {
        return (Map<String, Object>) attributes.get("kakao_account");
    }

    public String getEmail() {
        return (String) getKakaoAccount().get("email");
    }

    public String getNickname() {
        return (String) ((Map<String, Object>) getKakaoAccount().get("profile")).get("nickname");
    }

    public String getImage() {
        return (String) ((Map<String, Object>) getKakaoAccount().get("profile")).get("image");
    }
}

