package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

@Log4j2
@Component
@ConditionalOnProperty(name = "test.webclient", havingValue = "true")
public class WebClientConnectivityTest implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner WebClient connectivity test");

        try {
            WebClient webClient = webClient();
            Mono<String> response = webClient.post()
                    .uri("https://api-dev.cisco.com/asdc/services/dcpcoreint/content/contents")
                    .header("Authorization", "Bearer NVQTsPw2IRx3ZMUSdZo7W9r09Kaa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<>() {
                    });
            log.info("RESPONSE 1");
            response.publishOn(Schedulers.elastic()).subscribe(body -> log.info(body));
        } catch (Exception e) {
            log.info("ERROR WebClient: ----------");
            log.info(e.getMessage());
        }
    }

    private WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.HTTP)
                                .host("proxy-usw2.nprd-network.us.csco.cloud")
                                .port(3128)));

        ReactorClientHttpConnector conn = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .clientConnector(conn)
                .build();
    }
}
