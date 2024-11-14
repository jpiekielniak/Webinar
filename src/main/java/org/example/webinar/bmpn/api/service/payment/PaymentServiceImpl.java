package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Optional<Boolean> checkIsCreditCardValid(CreditCard creditCard) {
        return Optional.of(true);
    }
}
