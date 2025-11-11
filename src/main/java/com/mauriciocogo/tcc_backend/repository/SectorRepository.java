package com.mauriciocogo.tcc_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mauriciocogo.tcc_backend.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Query("""
                SELECT s
                FROM Sector s
                WHERE s.deleted IS FALSE
                  AND (LOWER(s.acronym) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    List<Sector> searchByAcronymOrName(@Param("keyword") String keyword);
    
    @Query("SELECT s FROM Sector s WHERE s.deleted IS FALSE")
    List<Sector> findAllActive();
}
