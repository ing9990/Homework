package com.schoolproject.user.repository;

import com.schoolproject.user.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taewoo
 */

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("SELECT DISTINCT(city) FROM School")
    List<String> getCities();

    List<School> findAllByCity(String city);


}
