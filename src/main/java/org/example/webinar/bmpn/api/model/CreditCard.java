package org.example.webinar.bmpn.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {
    private String firstName;
    private String lastName;
    private String code;
    private String expirationDate;
}
