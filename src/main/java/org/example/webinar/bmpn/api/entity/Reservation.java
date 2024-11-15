package org.example.webinar.bmpn.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private ReservationStatus status = ReservationStatus.PRERESERVED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "webinar_id")
    private Webinar webinar;
}
