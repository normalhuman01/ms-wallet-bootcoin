package pe.com.project4.ms.infrastructure.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.project4.ms.infrastructure.event.SendBootCoinEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletSendMoneyProducer {
    private final KafkaTemplate<String, SendBootCoinEvent> kafkaTemplate;

    public void sendMoneyEvent(SendBootCoinEvent sendBootCoinEvent) {
        log.debug("==> Producing message {}", sendBootCoinEvent.toString());
        kafkaTemplate.send("WALLET-SEND-MONEY", sendBootCoinEvent);
    }

}
