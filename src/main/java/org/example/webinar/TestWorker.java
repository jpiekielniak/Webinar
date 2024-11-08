package org.example.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestWorker {

    @JobWorker(type = "checkIfSeatIsFree")
    public Map<String, Object> checkSpot(final JobClient client, final ActivatedJob job) {
        Map<String, Object> jobResultVariables = job.getVariablesAsMap();

       // jobResultVariables.put("isFreeSpot", false);
        return jobResultVariables;

    }

    @JobWorker(type = "sendFailureEmail")
    public HashMap<String, Object> sendFailureEmail(final JobClient client, final ActivatedJob job) {
        HashMap<String, Object> jobResultVariables = new HashMap<>();

        //jobResultVariables.put("emailSent", true);
        return jobResultVariables;
    }
}

