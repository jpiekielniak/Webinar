package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.example.webinar.bmpn.api.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PayPreBookingWorker {

    @Autowired
    private PaymentService paymentService;

    @JobWorker(type = "payPreBooking")
    public HashMap<String, Object> payPreBooking(final ZeebeClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();
        //Logika biznesowa - płatność rezerwacji (zmiana statusu płatności w bazie danych na paid)
        // Zwrócić ewentualnie dla dalszego procesu adres e-mail
        paymentService.makeReservationPayment((Long) job.getVariablesAsMap().get("reservationId"));
        return jobResultVariables;
    }
}
