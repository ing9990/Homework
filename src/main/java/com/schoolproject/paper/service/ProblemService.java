package com.schoolproject.paper.service;

/**
 * @author Taewoo
 */


import com.schoolproject.paper.domain.Problem;
import com.schoolproject.paper.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public Problem save(Problem problem) {
        if(problem.getProblemId() == null){
            problem.setCreated(LocalDateTime.now());
        }
        problem.setUpdated(LocalDateTime.now());
        return problemRepository.save(problem);
    }

    public Optional<Problem> findProblem(Long problemId){
        return problemRepository.findById(problemId);
    }

    public List<Problem> listProblems(long paperTemplateId){
        return problemRepository.findAllByPaperTemplateIdOrderByIndexNumAsc(paperTemplateId);
    }

    public void delete(Problem problem) {
        problemRepository.delete(problem);
    }

    public void updateProblem(long problemId, String content, String answer) {
        problemRepository.updateProblem(problemId, content, answer, LocalDateTime.now());
    }

}