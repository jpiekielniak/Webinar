package org.example.webinar.bmpn.api.service.payment;

import org.example.webinar.bmpn.api.model.request.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String makeReservationPayment(Long preReservationId) {
        //Zmiana wartości isPaid w PreReservationRequest na true
        return  null;
    }

    @Override
    public boolean checkIsCreditCardValid(CreditCard creditCard) {
        return false;
    }
}
