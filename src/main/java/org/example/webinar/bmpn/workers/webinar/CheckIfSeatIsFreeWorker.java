package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CheckIfSeatIsFreeWorker {

    @JobWorker(type = "checkIfSeatIsFree")
    public Map<String, Object> checkIfSeatIsFree(final JobClient client, final ActivatedJob job) {
        Map<String, Object> jobResultVariables = job.getVariablesAsMap();

        //Logika biznesowa - sprawdzenie czy miejsce jest wolne za pomoca API(repo, service lub inna metoda)
        // Zwrócenie wartości true, false w zależności od wyniku w zmiennej "isFreeSpot"

        return jobResultVariables;
    }
}
