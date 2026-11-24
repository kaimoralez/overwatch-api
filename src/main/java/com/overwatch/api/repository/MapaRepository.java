package com.overwatch.api.repository;

import com.overwatch.api.entity.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {
}
