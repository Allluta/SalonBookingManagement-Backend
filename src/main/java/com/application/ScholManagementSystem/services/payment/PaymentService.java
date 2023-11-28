package com.application.ScholManagementSystem.services.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    public PaymentService(@Value("${stripe.secret-key}") String stripeSecretKey) {
        this.stripeSecretKey = stripeSecretKey;
        Stripe.apiKey = stripeSecretKey;
    }

    public String createPaymentIntent() {
        try {
            PaymentIntent intent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setCurrency("pln")
                            .setAmount(1099L)
                            .build()
            );
            return intent.getClientSecret();
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
