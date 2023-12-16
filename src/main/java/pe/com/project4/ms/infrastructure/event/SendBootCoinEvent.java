package pe.com.project4.ms.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project4.ms.infrastructure.rest.request.SendMoneyRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendBootCoinEvent {
    private String walletAccountSenderId;
    private String walletAccountReceiverId;
    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occurredAt;

    public SendBootCoinEvent(SendMoneyRequest sendMoneyRequest) {
        walletAccountSenderId = sendMoneyRequest.getWalletAccountSenderId();
        walletAccountReceiverId = sendMoneyRequest.getWalletAccountReceiverId();
        amount = sendMoneyRequest.getAmount();
        occurredAt = LocalDateTime.now();
    }
}
