package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.services.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent() {
        String clientSecret = paymentService.createPaymentIntent();
        if (clientSecret != null) {
            return ResponseEntity.ok().body(Map.of("clientSecret", clientSecret));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/process-payment")
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> paymentData) {
        // Tutaj dodaj kod do przetwarzania płatności na podstawie otrzymanych danych
        // Pamiętaj, żeby dostosować tę metodę do Twoich potrzeb i integracji z Stripe
        // Zwróć odpowiedź w zależności od wyniku przetwarzania płatności
        return ResponseEntity.ok().body(Map.of("status", "success", "message", "Payment processed successfully"));
    }

}