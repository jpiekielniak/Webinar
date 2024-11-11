package org.example.webinar.bmpn.api.service.email;

public interface IEmailService {
    void sendConfirmReservationEmail(String to);
    void sendFailureReservationEmail(String to);
    void sendConfirmPaymentEmail(String to);
    void sendRejectedPaymentEmail(String to);
}
