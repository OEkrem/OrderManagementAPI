package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(

        @Schema(description = "Kart numarası (16 haneli)", example = "4546711234567890")
        @Pattern(regexp = "\\d{16}", message = "Kart numarası 16 haneli ve sadece rakamlardan oluşmalıdır")
        String cardNumber,

        @Schema(description = "CCV güvenlik kodu (3 haneli)", example = "123")
        @Pattern(regexp = "\\d{3}", message = "CCV kodu 3 haneli olmalıdır")
        String ccv,

        @Schema(description = "Kart sahibinin adı", example = "Ahmet Yılmaz")
        @NotBlank(message = "Kart sahibi adı boş olamaz")
        String holderName,

        @Schema(description = "Son kullanma tarihi (MM/YY formatında)", example = "12/26")
        @Pattern(regexp = "(0[1-9]|1[0-2])/(\\d{2})", message = "Geçersiz son kullanma tarihi formatı, MM/YY olmalı")
        String expireDate,

        @Schema(description = "Ödeme miktarı (pozitif sayı)", example = "250.75")
        @Positive(message = "Ödeme miktarı pozitif olmalıdır")
        double amount

) {
}
