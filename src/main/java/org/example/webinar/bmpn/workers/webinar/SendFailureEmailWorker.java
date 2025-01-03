package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.email.EmailService;
import org.example.webinar.bmpn.api.service.emitter.EmitterService;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class SendFailureEmailWorker {

    private EmailService emailService;
    private ReservationService reservationService;
    private EmitterService emitterService;

    @JobWorker(type = "sendFailureEmail")
    public Map<String, Object> sendFailureEmail(final JobClient client, final ActivatedJob job) throws JSONException {
        var jobResultVariables = job.getVariablesAsMap();

        final var email = jobResultVariables.get("email").toString();
        emailService.sendFailureReservationEmail(email);

        emmitMessage(String.valueOf(job.getProcessInstanceKey()));
        return jobResultVariables;
    }

    private void emmitMessage(String instanceKey) throws JSONException {
        JSONObject message = new JSONObject();
        message.put("success", false);
        emitterService.sendMessageToListener(instanceKey, message.toString());
        emitterService.removeListener(instanceKey);
    }
}
