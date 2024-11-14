package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.request.CreditCard;
import org.springframework.stereotype.Service;

public interface PaymentService {
    void makePayment(Long preReservationId);
    boolean checkIsCreditCardValid(CreditCard creditCard);
}
