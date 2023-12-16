package pe.com.project4.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project4.ms.domain.WalletBootcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("walletAccounts")
public class WalletBootcoinDao {
    @Id
    private String id;
    private BigDecimal balance;
    private String phoneNumber;
    private LocalDateTime createdAt;

    public WalletBootcoinDao(WalletBootcoin walletBootcoin) {
        id = walletBootcoin.getId();
        balance = walletBootcoin.getBalance();
        phoneNumber = walletBootcoin.getPhoneNumber();
        createdAt = walletBootcoin.getCreatedAt();
    }

    public WalletBootcoin toWalletBootcoin() {
        WalletBootcoin walletBootcoin = new WalletBootcoin();
        walletBootcoin.setId(id);
        walletBootcoin.setBalance(balance);
        walletBootcoin.setPhoneNumber(phoneNumber);
        walletBootcoin.setCreatedAt(createdAt);
        return walletBootcoin;
    }
}
