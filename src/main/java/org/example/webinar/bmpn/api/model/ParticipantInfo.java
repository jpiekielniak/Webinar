package org.example.webinar.bmpn.api.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantInfo {
    private String fullName;
    private String email;
}
