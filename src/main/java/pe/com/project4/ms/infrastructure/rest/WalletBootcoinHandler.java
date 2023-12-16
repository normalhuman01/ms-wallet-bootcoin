package pe.com.project4.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import pe.com.project4.ms.application.SaveWalletBootcoinUseCase;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletBootcoin;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletBootcoinHandler {

    private final SaveWalletBootcoinUseCase saveWalletYankiService;
    private final WalletYankiRepository walletYankiRepository;

    public Mono<ServerResponse> postWalletBootCoin(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateAccountRequest.class)
                .map(saveWalletYankiService::createAccount)
                .flatMap(respuesta -> this.toServerResponse(respuesta, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getYankiPhone(ServerRequest request) {
        Mono<WalletBootcoin> yanki = request.queryParam("phoneNumber")
                .map(walletYankiRepository::findByPhoneNumber)
                .orElseGet(Mono::empty);
        return this.toServerResponse(yanki, HttpStatus.OK);
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<WalletBootcoin> walletBootcoin, HttpStatus status) {
        log.info("==> Antes de responder {} " + walletBootcoin.toString());
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(walletBootcoin, WalletBootcoin.class);
    }

}
