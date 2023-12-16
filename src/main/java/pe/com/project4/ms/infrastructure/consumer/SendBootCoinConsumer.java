package pe.com.project4.ms.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.application.impl.SendMoneyWalletBootcoinService;
import pe.com.project4.ms.infrastructure.event.SendBootCoinRequestEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendBootCoinConsumer {
    private final SendMoneyWalletBootcoinService sendMoneyWalletBootcoinService;

    @KafkaListener(topics = "SEND-BOOT-COIN-EVENT", groupId = "BOOTCOIN")
    public void consume(SendBootCoinRequestEvent sendBootCoinRequestEvent) {
        log.info("Consuming Message {}", sendBootCoinRequestEvent);
        sendMoneyWalletBootcoinService.sendMoney(sendBootCoinRequestEvent)
                .subscribe(result -> log.debug(result.toString()));
    }
}
