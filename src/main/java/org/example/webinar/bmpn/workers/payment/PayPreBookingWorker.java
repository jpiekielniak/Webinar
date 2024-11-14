package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.payment.PaymentService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PayPreBookingWorker {

    private PaymentService paymentService;

    @JobWorker(type = "payPreBooking")
    public Map<String, Object> payPreBooking(final ZeebeClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var preReservationId = Long.parseLong(job.getVariablesAsMap().get("preReservationId").toString());
        paymentService.makePreReservationPayment(preReservationId);
        return jobResultVariables;
    }
}
