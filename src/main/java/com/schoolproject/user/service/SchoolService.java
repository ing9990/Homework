package com.schoolproject.user.service;

/**
 * @author Taewoo
 */


import com.schoolproject.user.domain.School;
import com.schoolproject.user.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public School save(School school) {
        if (school.getSchoolId() == null) {
            school.setCreated(LocalDateTime.now());
        }
        school.setUpdated(LocalDateTime.now());
        return schoolRepository.save(school);
    }


    public List<String> cities() {
        return schoolRepository.getCities();
    }

}
















