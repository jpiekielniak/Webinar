package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.request.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String makeReservationPayment(Long reservationId) {
        return  null;
    }

    @Override
    public boolean checkIsCreditCardValid(CreditCard creditCard) {
        return false;
    }
}
