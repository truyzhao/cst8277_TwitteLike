package com.truby.cst8277.service;

import com.truby.cst8277.dto.request.AccessTokenDto;
import com.truby.cst8277.entities.GithubClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GithubOAuth2Service {
    private final ReactiveOAuth2AuthorizedClientService authorizedClientService;
    private final WebClient webClient = WebClient.builder().build();

    public GithubOAuth2Service(ReactiveOAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    //get Access Token from GitHub
    public Mono<String> getAccessToken(AccessTokenDto accessTokenDto) {
        return webClient.post()
                .uri("/login/oauth/access_token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(accessTokenDto))
                .retrieve()
                .bodyToMono(String.class);
    }

    public GithubClient getUserInfo(String accessToken) {
        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .baseUrl("https://api.github.com/user")
                .build();
        return client.get()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GithubClient.class)
                .block();
    }


    private Mono<String> getUserName(String accessToken) {
        return webClient.get()
                .uri("https://api.github.com/user?access_token=" + accessToken)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .map(responseBody -> "u/" + responseBody.get("name"));
    }
    // Exchange code for access token
  /*  public String getAccessToken(String clientId, String clientSecret, String code) {
        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .baseUrl("https://github.com/login/oauth/access_token")
                .build();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("code", code);
        return client.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> {
                    String accessToken = "";
                    try {
                        Map<String, String> map = Arrays.stream(s.split("&"))
                                .map(str -> str.split("="))
                                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
                        accessToken = map.get("access_token");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return accessToken;
                }).block();
    }*/

    // Use access token to get user information

}

