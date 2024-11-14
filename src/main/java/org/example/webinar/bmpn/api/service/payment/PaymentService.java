package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.request.CreditCard;

import java.util.Optional;

public interface PaymentService {
    Optional<Boolean> checkIsCreditCardValid(CreditCard creditCard);
}
