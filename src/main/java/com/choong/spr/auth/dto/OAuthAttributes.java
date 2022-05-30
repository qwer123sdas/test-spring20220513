package com.choong.spr.auth.dto;

import java.util.Map;

import org.springframework.context.annotation.Role;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Controller
@RequestMapping("oauth2/authorization")
public class OAuthAttributes {
	private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String phone;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String phone) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    // OAuth2User에서 반환하는 사용자 정보는 Map 이기때문에 값을 하나씩 변환
    /* public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
    	 // google은 userNameAttributeName가 기본 제공되지만 네이버는 아니라서 직접 지정
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }*/
    
    
    @RequestMapping("naver")
    @ResponseBody
    private static String ofNaver(String code, String state) {
        // RestTemplate 인스턴스 생성
        RestTemplate rt = new RestTemplate();

        HttpHeaders accessTokenHeaders = new HttpHeaders();
        accessTokenHeaders.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add("grant_type", "authorization_code");
        accessTokenParams.add("client_id", "{client_id");
        accessTokenParams.add("client_secret", "{client_secret}");
        accessTokenParams.add("code" , code);	// 응답으로 받은 코드
        accessTokenParams.add("state" , state); // 응답으로 받은 상태

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(accessTokenParams, accessTokenHeaders);

        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );

        return "accessToken: " + accessTokenResponse.getBody();
    }

}
