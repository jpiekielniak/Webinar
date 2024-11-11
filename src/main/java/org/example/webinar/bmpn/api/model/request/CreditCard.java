package org.example.webinar.bmpn.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {
    private String name;
    private String surname;
    private String code;
    private String expirationDate;
}
