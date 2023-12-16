package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.WalletBootcoin;
import pe.com.project4.ms.infrastructure.rest.request.CreateAccountRequest;
import reactor.core.publisher.Mono;

public interface SaveWalletBootcoinUseCase {
    Mono<WalletBootcoin> createAccount(CreateAccountRequest createAccountRequest);
}
