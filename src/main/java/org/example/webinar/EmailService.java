package org.example.webinar;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendConfirmReservationEmail(String to) {

    }

    @Override
    public void sendFailureReservationEmail(String to) {

    }

    @Override
    public void sendConfirmPaymentEmail(String to) {

    }

    @Override
    public void sendRejectedPaymentEmail(String to) {

    }

    public void sendRegistrationEmail(String to, String username) {
        Context context = new Context();
        context.setVariable("username", username);

        String emailContent = templateEngine.process("registration-email.html", context);

        sendHtmlEmail(to, "Registration Confirmation", emailContent);
    }

    private void sendHtmlEmail( String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("webinar.iwipb@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            // Handle email sending errors
        }
    }


}
