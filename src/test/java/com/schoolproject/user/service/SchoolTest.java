package com.schoolproject.user.service;

import com.schoolproject.user.domain.School;
import com.schoolproject.user.repository.SchoolRepository;
import com.schoolproject.user.service.helper.SchoolTestHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taewoo
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SchoolTest {

    @Autowired
    private SchoolRepository schoolRepository;

    private SchoolService schoolService;
    private SchoolTestHelper schoolTestHelper;

    School school;

    @BeforeEach
    void before() {
        this.schoolRepository.deleteAll();
        this.schoolService = new SchoolService(schoolRepository);
        this.schoolTestHelper = new SchoolTestHelper(this.schoolService);
        school = this.schoolTestHelper.createSchool("테스트 학교", "서울");
    }

    @DisplayName("1. 학교 생성하기")
    @Test
    void test_1() {
        schoolService.save(school);
        var list = schoolRepository.findAll();
        assertEquals(1, list.size());
        SchoolTestHelper.assertSchool(list.get(0), "테스트 학교", "서울");
    }

    @DisplayName("2. 학교 이름을 수정한다.")
    @Test
    void test_2() {
        schoolService.updateName(school.getSchoolId(), "테스트2 학교");
        assertEquals("테스트2 학교", schoolRepository.findAll().get(0).getName());
    }

    @DisplayName("3. 지역 목록을 가져온다.")
    @Test
    void test_3() {
        var list = schoolService.cities();
        assertEquals(1, list.size());
        assertEquals("서울", list.get(0));

        schoolTestHelper.createSchool("부산 학교", "부산");

        list = schoolService.cities();
        assertEquals(2, list.size());
        assertEquals("부산", list.get(1));
    }

    @DisplayName("4. 지역으로 학교 목록 가져오기")
    @Test
    void test_4() {
        var list = schoolService.findAllByCity("서울");

        assertEquals(1, list.size());
        assertEquals("서울", list.get(0).getCity());

        schoolTestHelper.createSchool("서울2 학교", "서울");

        list = schoolService.findAllByCity("서울");
        assertEquals(2, list.size());
        assertEquals("서울2 학교", list.get(1).getName());
    }
}











