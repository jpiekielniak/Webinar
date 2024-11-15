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
    private static final String MESSAGE = "attendanceRequestMessage";

    @PostMapping("/attendance")
    public Map<String, Object> startPayment(@RequestBody Map<String, Object> variables) {

        final var processInstanceKey = variables.get("processInstanceKey").toString();

        client.newPublishMessageCommand()
                .messageName(MESSAGE)
                .correlationKey(processInstanceKey)
                .variables(variables)
                .send()
                .join();

        return variables;
    }
}