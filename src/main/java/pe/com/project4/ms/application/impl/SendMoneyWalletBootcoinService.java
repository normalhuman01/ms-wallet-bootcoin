package pe.com.project4.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project4.ms.application.repository.WalletYankiRepository;
import pe.com.project4.ms.application.SendMoneyWalletBootcoinUseCase;
import pe.com.project4.ms.domain.WalletBootcoin;
import pe.com.project4.ms.infrastructure.event.SendBootCoinRequestEvent;
import pe.com.project4.ms.infrastructure.producer.WalletSendMoneyProducer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMoneyWalletBootcoinService implements SendMoneyWalletBootcoinUseCase {

    private final WalletYankiRepository walletYankiRepository;
    private final WalletSendMoneyProducer walletSendMoneyProducer;

    @Override
    public Mono<WalletBootcoin> sendMoney(SendBootCoinRequestEvent sendMoneyRequest) {
        log.info("<---- Inicia la transaccion con ID {}", sendMoneyRequest.getTransactionNumber());
        log.info("<---- Imprime sendBootCoinRequest: {}", sendMoneyRequest.toString());
        Mono<WalletBootcoin> walletYankiSenderMono = walletYankiRepository.findByPhoneNumber(sendMoneyRequest.getWalletAccountSenderId())
                .switchIfEmpty(Mono.error(new RuntimeException("Esta cuenta no existe!")))
                .filter(walletYanki -> sendMoneyRequest.getAmount().compareTo(walletYanki.getBalance()) <= 0)
                .doOnNext(w -> log.info("se imprime luego de verificar el saldo {}", w))
                .switchIfEmpty(Mono.error(new RuntimeException("Saldo Insuficiente!!!!!")));

        Mono<WalletBootcoin> walletYankiReceiverMono = walletYankiRepository.findByPhoneNumber(sendMoneyRequest.getWalletAccountReceiverId())
                .switchIfEmpty(Mono.error(new RuntimeException("Esta cuenta no existe!")));

        return Mono.zip(walletYankiSenderMono, walletYankiReceiverMono, (walletYankiSender, walletYankiReceiver) -> {
            BigDecimal money = sendMoneyRequest.getAmount();
            walletYankiSender.debitMoney(money);
            walletYankiReceiver.creditMoney(money);
            return Stream.of(walletYankiSender, walletYankiReceiver);
        }).flatMapMany(walletYankis -> {
            //walletSendMoneyProducer.sendMoneyEvent(new SendBootCoinEvent(sendMoneyRequest));
            return walletYankiRepository.saveAll(Flux.fromStream(walletYankis));
        }).filter(walletYankis -> walletYankis.getPhoneNumber().equals(sendMoneyRequest.getWalletAccountSenderId()))
                .elementAt(0);
    }
}
