package org.example.webinar.bmpn;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
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
public class PaymentProcessController {

    @Qualifier("zeebeClientLifecycle")
    private ZeebeClient client;
    private static final String MESSAGE = "paymentRequestMessage";

    @PostMapping("/payment")
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