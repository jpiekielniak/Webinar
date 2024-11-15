package org.example.webinar.bmpn.api.repository;

import org.example.webinar.bmpn.api.entity.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebinarRepository extends JpaRepository<Webinar, Long> {
}