package com.mauriciocogo.tcc_backend.service;

import org.springframework.stereotype.Service;

import com.mauriciocogo.tcc_backend.repository.SectorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SectorService {
    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository){
        this.sectorRepository = sectorRepository;
    }

    // public 
}
