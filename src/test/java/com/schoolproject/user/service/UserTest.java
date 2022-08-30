package com.schoolproject.user.service;

/**
 * @author Taewoo
 */


import com.schoolproject.user.domain.Authority;
import com.schoolproject.user.domain.User;
import com.schoolproject.user.service.helper.UserTestHelper;
import com.schoolproject.user.service.helper.WithUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        var list = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());

        assertEquals(1, list.size());
        UserTestHelper.assertUser(school, list.get(0), "user1");
    }

    @DisplayName("2. 이름 수정")
    @Test
    void 이름_수정() {
        var user = userTestHelper.createUser(school, "user1");
        userService.updateUsername(user.getUserId(), "user2");
        var list = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals("user2", list.get(0).getName());
    }


    @DisplayName("3. 권한 부여")
    @Test
    void 권한_부여하기() {
        var user = userTestHelper.createUser(school, "user1", Authority.ROLE_STUDENT);
        userService.addAuthority(user.getUserId(), Authority.ROLE_TEACHER);
        var savedUser = userService.findUser(user.getUserId()).get();
        userTestHelper.assertUser(school, savedUser, "user1", Authority.ROLE_STUDENT, Authority.ROLE_TEACHER);
    }

    @DisplayName("4. 권한 취소")
    @Test
    void 권한_취소하기() {
        var user1 = userTestHelper.createUser(school, "admin", Authority.ROLE_STUDENT, Authority.ROLE_TEACHER);
        userService.removeAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        var savedUer = userService.findUser(user1.getUserId()).get();
        userTestHelper.assertUser(school, savedUer, "admin", Authority.ROLE_TEACHER);
    }

    @DisplayName("5. 이메일 검색")
    @Test
    void 이메일_검색기능() {
        var user1 = userTestHelper.createUser(school, "user1");
        var saved = (User) userSecurityService.loadUserByUsername("user1@test.com");
        userTestHelper.assertUser(school, saved, "user1");
    }

    @DisplayName("6. ROLE 중복 방지")
    @Test
    void Role_중복_방지() {
        var user1 = userTestHelper.createUser(school, "user1", Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_STUDENT);
        assertEquals(1, userService.findUser(user1.getUserId()).get().getAuthorities().size());
    }

    @DisplayName("7. 이메일 중복 방지")
    @Test
    void 이메일_중복_방지() {
        userTestHelper.createUser(school, "user1");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userTestHelper.createUser(school, "user1");
        });
    }



}

















