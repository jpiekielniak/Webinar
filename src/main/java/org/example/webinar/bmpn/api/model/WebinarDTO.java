package org.example.webinar.bmpn.api.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebinarDTO {
    private String title;
    private Long id;
}
