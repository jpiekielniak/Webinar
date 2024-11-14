package org.example.webinar.bmpn;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class WebinarProcessController {

    private static final String BPMN_PROCESS_ID = "reservation-process";

    @Qualifier("zeebeClientLifecycle")
    private ZeebeClient client;

    @PostMapping("/start")
    public Map<String, Object> startProcessInstance(@RequestBody Map<String, Object> variables) {

        var event = client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPMN_PROCESS_ID)
                .latestVersion()
                .variables(variables)
                .send();

        variables.put("processInstanceKey", event.join().getProcessInstanceKey());
        return variables;
    }
}