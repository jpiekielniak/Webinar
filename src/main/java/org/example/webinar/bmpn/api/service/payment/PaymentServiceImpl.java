package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static boolean isNotNullOrEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public Optional<Boolean> checkIsCreditCardValid(CreditCard creditCard) {
        return Optional.of(isNotNullOrEmpty(creditCard.getOwner()) &&
                isNotNullOrEmpty(creditCard.getCardNumber()) &&
                isNotNullOrEmpty(creditCard.getCode()) &&
                isNotNullOrEmpty(creditCard.getExpirationDate()));
    }
}
