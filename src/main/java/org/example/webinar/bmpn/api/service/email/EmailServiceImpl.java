package org.example.webinar.bmpn.api.service.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;
    private static final String EMAIL = "webinar.iwipb@gmail.com";
    private static final String CONFIRM_RESERVATION_EMAIL = "confirm-reservation-email.html";
    private static final String FAILURE_RESERVATION_EMAIL = "failure-reservation-email.html";
    private static final String CONFIRM_PAYMENT_EMAIL = "confirm-payment-email.html";
    private static final String REJECTED_PAYMENT_EMAIL = "rejected-payment-email.html";

    @Override
    public void sendConfirmReservationEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process(CONFIRM_RESERVATION_EMAIL, context);

        sendHtmlEmail(to, "Potwierdzenie rezerwacji", emailContent);
    }

    @Override
    public void sendFailureReservationEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process(FAILURE_RESERVATION_EMAIL, context);

        sendHtmlEmail(to, "Rezerwacja odrzucona", emailContent);
    }

    @Override
    public void sendConfirmPaymentEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process(CONFIRM_PAYMENT_EMAIL, context);

        sendHtmlEmail(to, "Potwierdzenie płatności", emailContent);
    }

    @Override
    public void sendRejectedPaymentEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process(REJECTED_PAYMENT_EMAIL, context);

        sendHtmlEmail(to, "Płatność odrzucona", emailContent);
    }

    @Override
    public void sendThankEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process("thank-email.html", context);

        sendHtmlEmail(to, "Dziękujemy za udział w webinarze!", emailContent);
    }

    private void sendHtmlEmail(String to, String subject, String content) {
        try {
            var message = javaMailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(EMAIL);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
