package org.example.webinar.bmpn.workers.attendance;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class SetCompletedWebinar {

    private WebinarService webinarService;

    @JobWorker(type = "setCompletedWebinar")
    public Map<String, Object> setCompletedWebinar(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var webinarId = Long.parseLong(String.valueOf(jobResultVariables.get("webinarId")));
        webinarService.changeWebinarStatus(webinarId, true);

        return jobResultVariables;
    }
}
