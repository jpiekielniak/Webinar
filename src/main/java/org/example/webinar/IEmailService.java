package org.example.webinar;

public interface IEmailService {
    void sendConfirmReservationEmail(String to, String username, String reservationId);
    void sendFailureReservationEmail(String to, String username, String reservationId);
    void sendConfirmPaymentEmail(String to, String username, String reservationId);
    void sendRejectedPaymentEmail(String to, String username, String reservationId);
}
