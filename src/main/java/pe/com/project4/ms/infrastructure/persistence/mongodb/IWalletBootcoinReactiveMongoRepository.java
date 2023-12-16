package pe.com.project4.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project4.ms.infrastructure.persistence.model.WalletBootcoinDao;
import reactor.core.publisher.Mono;

public interface IWalletBootcoinReactiveMongoRepository extends ReactiveMongoRepository<WalletBootcoinDao, String> {
    Mono<WalletBootcoinDao> findByPhoneNumber(String phoneNumber);
}
