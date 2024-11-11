package org.example.webinar;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendConfirmReservationEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process("confirm-reservation-email.html", context);

        sendHtmlEmail(to, "Reservation Confirmed", emailContent);
    }

    @Override
    public void sendFailureReservationEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process("failure-reservation-email.html", context);

        sendHtmlEmail(to, "Reservation Failed", emailContent);
    }

    @Override
    public void sendConfirmPaymentEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process("confirm-payment-email.html", context);

        sendHtmlEmail(to, "Payment Confirmed", emailContent);
    }

    @Override
    public void sendRejectedPaymentEmail(String to) {
        var context = new Context();
        var emailContent = templateEngine.process("rejected-payment-email.html", context);

        sendHtmlEmail(to, "Payment Rejected", emailContent);
    }

    private void sendHtmlEmail(String to, String subject, String content) {
        try {
            var message = javaMailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("webinar.iwipb@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
