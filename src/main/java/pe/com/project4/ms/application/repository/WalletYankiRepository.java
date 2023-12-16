package pe.com.project4.ms.application.repository;

import pe.com.project4.ms.domain.WalletBootcoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletYankiRepository {
    Mono<WalletBootcoin> save(WalletBootcoin walletBootcoin);

    Mono<WalletBootcoin> findByPhoneNumber(String phone);

    Flux<WalletBootcoin> saveAll(Flux<WalletBootcoin> walletAccounts);
}
