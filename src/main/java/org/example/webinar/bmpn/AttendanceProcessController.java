package org.example.webinar.bmpn;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AttendanceProcessController {

    @Qualifier("zeebeClientLifecycle")
    private ZeebeClient client;
    private static final String BMPN_PROCESS_ID = "AttendanceAtTheWebinarProcess";

    @PostMapping("/attendance")
    public Map<String, Object> startPayment(@RequestBody Map<String, Object> variables) {

        client
                .newCreateInstanceCommand()
                .bpmnProcessId(BMPN_PROCESS_ID)
                .latestVersion()
                .variables(variables)
                .send();

        return variables;
    }
}