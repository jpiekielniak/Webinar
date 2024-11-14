package org.example.webinar.bmpn.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Webinar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "webinar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
    private int maxSlots;
    private Double price;
    private boolean paid;
    private boolean completed = false;
}
