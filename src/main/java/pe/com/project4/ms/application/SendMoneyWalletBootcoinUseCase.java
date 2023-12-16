package pe.com.project4.ms.application;

import pe.com.project4.ms.domain.WalletBootcoin;
import pe.com.project4.ms.infrastructure.event.SendBootCoinRequestEvent;
import reactor.core.publisher.Mono;

public interface SendMoneyWalletBootcoinUseCase {
    Mono<WalletBootcoin> sendMoney(SendBootCoinRequestEvent sendMoneyRequest);
}
