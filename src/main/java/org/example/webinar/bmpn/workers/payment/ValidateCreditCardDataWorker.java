package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.example.webinar.bmpn.api.model.CreditCard;
import org.example.webinar.bmpn.api.service.payment.PaymentService;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ValidateCreditCardDataWorker {

    private PaymentService paymentService;
    private ReservationService reservationService;

    @JobWorker(type = "validateCreditCardData")
    public Map<String, Object> validateCreditCardData(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var creditCard = CreditCard.builder()
                .owner(jobResultVariables.get("owner").toString())
                .cardNumber(jobResultVariables.get("cardNumber").toString())
                .code(jobResultVariables.get("code").toString())
                .expirationDate(jobResultVariables.get("expiryDate").toString())
                .build();

        var isCreditCardValid = paymentService.checkIsCreditCardValid(creditCard);

        if(!isCreditCardValid.get()) {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode("CREDIT_CARD_NOT_VALID")
                    .send()
                    .join();
        }

        final var reservationId = Long.parseLong(jobResultVariables.get("reservationId").toString());
        reservationService.changeReservationStatus(reservationId, ReservationStatus.PAYED);

        jobResultVariables.put("isCreditCardDataValid", isCreditCardValid.get());

        return jobResultVariables;
    }
}
