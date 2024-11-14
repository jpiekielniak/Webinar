package org.example.webinar.bmpn.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrereservationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long webinarId;
}
