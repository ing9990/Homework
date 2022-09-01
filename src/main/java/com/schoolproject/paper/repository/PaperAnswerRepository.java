package com.schoolproject.paper.repository;

import com.schoolproject.paper.domain.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Taewoo
 */

public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, PaperAnswer.PaperAnswerId> {
}
