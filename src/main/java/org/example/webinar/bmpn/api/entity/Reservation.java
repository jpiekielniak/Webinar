package org.example.webinar.bmpn.api.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "webinar_id")
    private Webinar webinar;
}
