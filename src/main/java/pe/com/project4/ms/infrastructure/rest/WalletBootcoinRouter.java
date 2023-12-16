package pe.com.project4.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WalletBootcoinRouter {
    private static final String WALLET_BOOTCOINS = "/wallet-bootcoin";
    private static final String WALLET_HOLDER_ACCOUNT = WALLET_BOOTCOINS + "/account";

    @Bean
    public RouterFunction<ServerResponse> routes(WalletBootcoinHandler walletBootcoinHandler) {
        return route(POST(WALLET_HOLDER_ACCOUNT).and(accept(APPLICATION_JSON)), walletBootcoinHandler::postWalletBootCoin)
                .andRoute(GET(WALLET_BOOTCOINS).and(queryParam("phoneNumber", t -> true)), walletBootcoinHandler::getYankiPhone);
    }
}
