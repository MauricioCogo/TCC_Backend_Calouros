package com.mauriciocogo.tcc_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mauriciocogo.tcc_backend.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{
}
