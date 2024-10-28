package com.enterprisemanager.backend.infrastructure.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    public PaymentController(@Value("${stripe.api.secret.key}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> request) throws StripeException {
        int amount = (int) request.get("amount"); // Asegúrate de recibir el monto en centavos.
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put("automatic_payment_methods", Map.of("enabled", true));

        System.out.println("Received request to create payment intent with amount: " + amount);
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println("Payment Intent created with clientSecret: " + paymentIntent.getClientSecret());

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());

        return response;
    }
}
