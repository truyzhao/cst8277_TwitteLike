package com.truby.cst8277.service;


import com.truby.cst8277.dto.request.AccessTokenDto;
import com.truby.cst8277.dto.request.AddUserRequestDto;
import com.truby.cst8277.entities.GithubClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UserManagementService {

    private Map<String, LocalDateTime> tokens = new HashMap<>();
    private ModelMapper modelMapper;
    private UserService userService;
    public AccessTokenDto accessTokenDto;
    private GithubOAuth2Service githubOAuth2Service;
    private final WebClient webClient;

    @Value("<github-client-id>")
    private String clientId;
    @Value("<github-client-secret>")
    private String clientSecret;
    private static final String CALLBACK_URL="http://localhost:8080/login/oauth2/code/github";

    public UserManagementService(@Qualifier("githubWebClient") WebClient webClient) {

        this.webClient = webClient;
    }

    //ask for authorization from GitHub with a UUID token
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public Mono<String> githubAuth(ServerWebExchange exchange) {

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(15);
        tokens.put(token, expirationTime); // Store the token and its expiration time for the given user ID
        exchange.getSession().doOnNext(session -> session.getAttributes().put("state", token)).subscribe();

        String url = "https://github.com/login/oauth/authorize?scope=user" +
                "&client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(CALLBACK_URL) +
                "&state=" + token;

        return Mono.just("redirect:/auth-success");
    }

    // callback from GitHub with code
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public Mono<ServerResponse> githubCallback(ServerWebExchange exchange) throws Exception {
        String code = exchange.getRequest().getQueryParams().getFirst("code");
        String state = exchange.getRequest().getQueryParams().getFirst("state");
        String token = (String) exchange.getSession().block().getAttributes().get("state");

        if (null != token && !token.equals(state) && isTokenValid(token)) {
            return ServerResponse.seeOther(URI.create("/login")).build();
        }

        String accessTokenJson = String.valueOf(githubOAuth2Service.getAccessToken(accessTokenDto));
        GithubClient jsonObject = githubOAuth2Service.getUserInfo(accessTokenJson);
        return ServerResponse.seeOther(URI.create("/success")).build();
    }

    public boolean isTokenValid(String token) {
        LocalDateTime expirationTime = tokens.get(token);
        return expirationTime != null
                && expirationTime.now().isBefore(expirationTime);
    }

    @PostMapping("/register")
    public String AddUserResponseDto(AddUserRequestDto newUser) {
        userService.add(newUser);
        return "register_success";
    }
}
