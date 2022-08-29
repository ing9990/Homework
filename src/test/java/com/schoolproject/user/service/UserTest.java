package com.schoolproject.user.service;

/**
 * @author Taewoo
 */


import com.schoolproject.user.domain.User;
import com.schoolproject.user.service.helper.UserTestHelper;
import com.schoolproject.user.service.helper.WithUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest extends WithUserTest {

    @BeforeEach
    protected void before() {
        super.prepareUserServices();
    }

    @DisplayName("1. 사용자 생성")
    @Test
    void 사용자_생성() {
        userTestHelper.createUser(school, "user1");
        var list = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, list.size());
        UserTestHelper.assertUser(school, list.get(0), "user1");
    }
}

















