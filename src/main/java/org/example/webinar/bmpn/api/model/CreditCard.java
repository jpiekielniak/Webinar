package org.example.webinar.bmpn.api.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {
    private String owner;
    private String cardNumber;
    private String code;
    private String expirationDate;
}
