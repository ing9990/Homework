package com.schoolproject.user.service.helper;

/**
 * @author Taewoo
 */


import com.schoolproject.user.domain.School;
import com.schoolproject.user.service.SchoolService;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
public class SchoolTestHelper {

    private final SchoolService schoolService;

    public static School makeSchool(String name, String city) {
        return School.builder().name(name).city(city).build();
    }

    public School createSchool(String name, String city) {
        return schoolService.save(makeSchool(name, city));
    }

    public static void assertSchool(School school, String name, String city) {
        assertNotNull(school.getSchoolId());
        assertNotNull(school.getCreated());
        assertNotNull(school.getUpdated());

        assertEquals(name, school.getName());
        assertEquals(city, school.getCity());
    }
}













