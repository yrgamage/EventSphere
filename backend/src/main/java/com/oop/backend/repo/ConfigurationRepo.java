package com.oop.backend.repo;

import com.oop.backend.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration,Integer> {
    @Query(value = "SELECT * FROM configuration ORDER BY configID DESC LIMIT 1", nativeQuery = true)
    Configuration findLastRow();


}
