package com.oekrem.SpringMVCBackEnd.services.bank;

import com.oekrem.SpringMVCBackEnd.dto.Request.PaymentRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IsBankasiPaymentService implements BankPaymentService {

    private static final String API_HOST = "https://api.isbank.com.tr";
    private static final String PAYMENT_ENDPOINT = "/api/isbank/v1/credit-cards/payments";
    private static final String CLIENT_ID = "your_client_id";
    private static final String CLIENT_SECRET = "your_client_secret";
    private static final String ACCESS_TOKEN = "your_access_token";

    private final WebClient webClient;

    public IsBankasiPaymentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_HOST).build();
    }


    @Override
    public boolean processPayment(PaymentRequest request) {
        System.out.println("İş Bankası POS kullanılıyor...");

        try {
            String response = webClient.post()
                    .uri(PAYMENT_ENDPOINT)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("X-IBM-client-id", CLIENT_ID)
                    .header("X-IBM-client-secret", CLIENT_SECRET)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response != null;
        } catch (Exception e) {
            System.err.println("Ödeme başarısız: " + e.getMessage());
            return false;
        }

    }
}
