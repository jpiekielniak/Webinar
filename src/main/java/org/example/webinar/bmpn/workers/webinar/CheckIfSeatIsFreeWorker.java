package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CheckIfSeatIsFreeWorker {

    private WebinarService webinarService;

    @JobWorker(type = "checkIfSeatIsFree")
    public Map<String, Object> checkIfSeatIsFree(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        var isFreeSlot = webinarService.isFreeSlot(Long.parseLong(jobResultVariables.get("webinarId").toString()));
        jobResultVariables.put("isFreeSpot", isFreeSlot);

        return jobResultVariables;
    }
}
