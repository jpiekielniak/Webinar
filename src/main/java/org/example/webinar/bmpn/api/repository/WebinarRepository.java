package org.example.webinar.bmpn.api.repository;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebinarRepository extends JpaRepository<Webinar, Long> {
}