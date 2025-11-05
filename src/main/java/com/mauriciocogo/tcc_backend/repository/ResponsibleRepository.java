package com.mauriciocogo.tcc_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mauriciocogo.tcc_backend.entity.Responsible;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByEmail(String email);
}
