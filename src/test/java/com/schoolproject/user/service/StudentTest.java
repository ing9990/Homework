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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentTest extends WithUserTest {

    User teacher;
    User student;

    @BeforeEach
    void before() {
        prepareUserServices();
        this.teacher = this.userTestHelper.createTeacher(school, "신민화");
        this.student = this.userTestHelper.createStudent(school, teacher, "김태우", "2");
    }

    @DisplayName("1. 학습자 등록")
    @Test
    void 학습자_등록() {
        var studentList = userService.findStudentList();
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "김태우", "2");
    }

    @DisplayName("2. 선생님으로 등록하면 선생님의 학습자 조회")
    @Test
    void 선생님의_학생_조회() {
        var studentList = userService.findTeacherStudentList(teacher.getUserId());
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "김태우", "2");
    }

    @DisplayName("3. 학교로 학습자 조회")
    @Test
    void 학교로_학습자_조회() {
        var studentList = userService.findBySchoolStudentList(school.getSchoolId());
        assertEquals(1, studentList.size());
        UserTestHelper.assertStudent(school, teacher, studentList.get(0), "김태우", "2");
    }


}












