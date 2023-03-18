package com.truby.cst8277.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class GitHubClientConfig {
    private static final String GITHUB_OAUTH2_REGISTRATION_ID = "github";
    private static final String GITHUB_BASE_URL = "https://github.com";

    @Bean(name = "githubWebClient")
    public WebClient githubWebClient(ReactiveOAuth2AuthorizedClientManager reactiveOAuth2AuthorizedClientManager) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(reactiveOAuth2AuthorizedClientManager);
        oauth2.setDefaultClientRegistrationId(GITHUB_OAUTH2_REGISTRATION_ID);

        return WebClient.builder()
                .baseUrl(GITHUB_BASE_URL)
                .filter(oauth2)
                .filter(ExchangeFilterFunction.ofRequestProcessor(this::injectHeader))
                .build();
    }


    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ReactiveOAuth2AuthorizedClientService authorizedClientService) {

        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    public Mono<ClientRequest> injectHeader(final ClientRequest clientRequest) {
        return Mono.just(ClientRequest.from(clientRequest)
                .header(HttpHeaders.USER_AGENT, "java:com.truby.cst8277:v0.0.1-SNAPSHOT")
                .build());
    }
}
