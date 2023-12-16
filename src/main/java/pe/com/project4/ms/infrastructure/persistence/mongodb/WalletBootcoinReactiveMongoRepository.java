package pe.com.project4.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.domain.WalletBootcoin;
import pe.com.project4.ms.infrastructure.persistence.model.WalletBootcoinDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WalletBootcoinReactiveMongoRepository implements WalletYankiRepository {

    private final IWalletBootcoinReactiveMongoRepository walletYankiReactiveMongoRepository;

    @Override
    public Mono<WalletBootcoin> save(WalletBootcoin walletBootcoin) {
        log.info("==> Dentro del repository {}", walletBootcoin);
        return walletYankiReactiveMongoRepository.save(new WalletBootcoinDao(walletBootcoin))
                .map(WalletBootcoinDao::toWalletBootcoin)
                .doOnNext(result -> log.info("==> Despues del map {}", result));
    }

    @Override
    public Mono<WalletBootcoin> findByPhoneNumber(String phone) {
        log.info("==> llega a buscar {}", phone);
        return walletYankiReactiveMongoRepository
                .findByPhoneNumber(phone)
                .map(WalletBootcoinDao::toWalletBootcoin)
                .doOnNext(result -> log.info("==> llega a encontrar {}", result));
    }

    @Override
    public Flux<WalletBootcoin> saveAll(Flux<WalletBootcoin> walletAccounts) {
        return walletYankiReactiveMongoRepository.saveAll(walletAccounts.map(WalletBootcoinDao::new))
                .map(WalletBootcoinDao::toWalletBootcoin)
                .doOnNext(result -> log.info("==> Despues del map {}", result));
    }


}
