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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherTest extends WithUserTest {

    User teacher;

    @BeforeEach
    void before() {
        prepareUserServices();
        this.teacher = this.userTestHelper.createTeacher(school, "teacher1");
    }

    @DisplayName("1. 선생님 등록")
    @Test
    void 선생님_등록() {
        var teacherList = userService.findTeacherList();
        assertEquals(1, teacherList.size());
        UserTestHelper.assertTeacher(school, teacherList.get(0), "teacher1");
    }

    @DisplayName("2. 선생님으로 등록한 학생 리스트 조회")
    @Test
    void 선생님으로_등록한_학생_리스트_조회() {
        this.userTestHelper.createStudent(school, teacher, "study1", "1");
        this.userTestHelper.createStudent(school, teacher, "study2", "1");
        this.userTestHelper.createStudent(school, teacher, "study3", "1");
        assertEquals(3, userService.findTeacherStudentList(teacher.getUserId()).size());
    }


    @DisplayName("3. 선생님 리스트 조회")
    @Test
    void 선생님_리스트() {
        this.userTestHelper.createUser(school, "teacher2", Authority.ROLE_TEACHER);
        this.userTestHelper.createUser(school, "teacher3", Authority.ROLE_TEACHER);
        this.userTestHelper.createUser(school, "teacher4", Authority.ROLE_TEACHER);

        assertEquals(4, userService.findTeacherList().size());
    }

    @DisplayName("4. 선생님 조회 테스트")
    @Test
    void 선생님_조회() {
        var list = new ArrayList<>(
                List.of(this.userTestHelper.createTeacher(school, "신민화"),
                        this.userTestHelper.createTeacher(school, "허순희"),
                        this.userTestHelper.createTeacher(school, "장용미"),
                        this.userTestHelper.createTeacher(school, "박성우")));

        assertTrue(userService.findTeacherList().containsAll(list));
    }

    @DisplayName("5. 학교로 선생님 조회")
    @Test
    void 학교로_선생님_조회() {
        var teacherList = userService.findBySchoolTeacherList(school.getSchoolId());
        assertEquals(1, teacherList.size());
        UserTestHelper.assertTeacher(school, teacher, "teacher1");

        this.userTestHelper.createUser(school, "허순희", Authority.ROLE_TEACHER);
        this.userTestHelper.createUser(school, "장용미", Authority.ROLE_TEACHER);

        assertEquals(3, userService.findTeacherList().size());
    }

}


















