package org.example.webinar.bmpn;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class WebinarProcessController {

    private static final String BPMN_PROCESS_ID = "reservation-process";

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient client;

    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String, Object> variables) {

        client
                .newCreateInstanceCommand()
                .bpmnProcessId(BPMN_PROCESS_ID)
                .latestVersion()
                .variables(variables)
                .send();
    }
}