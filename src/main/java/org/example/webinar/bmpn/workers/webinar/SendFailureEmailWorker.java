package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.email.EmailService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class SendFailureEmailWorker {
    private EmailService emailService;

    @JobWorker(type = "sendFailureEmail")
    public Map<String, Object> sendFailureEmail(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        var email = jobResultVariables.get("email").toString();
        emailService.sendFailureReservationEmail(email);

        return jobResultVariables;
    }
}
