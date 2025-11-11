package com.mauriciocogo.tcc_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mauriciocogo.tcc_backend.entity.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
  @Query("""
          SELECT i
          FROM Information i
          WHERE i.deleted IS FALSE
            AND (
              LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(i.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(i.sector.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(i.sector.acronym) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
      """)
  List<Information> searchByAcronymOrName(@Param("keyword") String keyword);

  @Query("SELECT i FROM Information i WHERE i.deleted IS FALSE")
  List<Information> findAllActive();

  @Query("SELECT i FROM Information i WHERE i.deleted IS FALSE AND id = :id")
  Optional<Information> findByIdActive(@Param("id") Long id);

}
