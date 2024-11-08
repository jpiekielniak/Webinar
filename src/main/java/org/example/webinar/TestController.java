package org.example.webinar;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController {


    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient client;

    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String, Object> variables) {

        client
                .newCreateInstanceCommand()
                .bpmnProcessId("reservation-process")
                .latestVersion()
                .variables(variables)
                .send();
    }
}