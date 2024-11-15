package org.example.webinar.bmpn.api.service.email;

public interface EmailService {
    void sendConfirmReservationEmail(String to);
    void sendFailureReservationEmail(String to);
    void sendConfirmPaymentEmail(String to);
    void sendRejectedPaymentEmail(String to);
    void sendThankEmail(String to);
}
