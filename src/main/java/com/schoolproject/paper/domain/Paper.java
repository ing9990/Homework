package com.schoolproject.paper.domain;

/**
 * @author Taewoo
 */


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.schoolproject.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TABLE_PAPER")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paperId;

    private Long paperTemplateId;
    private String name;

    private Long studyUserId;

    @Transient
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paper")
    private List<PaperAnswer> paperAnswerList;

    private LocalDateTime created;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private PaperState state;

    public static enum PaperState {
        READY, START, END, CANCEL
    }

    private int total;
    private int answered;
    private int correct;

    @Transient
    public double getScore() {
        if (total < 1) return 0;
        return correct * 100.0 / total;
    }

    public void addAnswered() {
        answered++;
    }

    public void addCorrect() {
        correct++;
    }

    public Map<Integer, PaperAnswer> answerMap() {
        if (paperAnswerList == null) return new HashMap<>();
        return paperAnswerList.stream().collect(Collectors.toMap(PaperAnswer::num, Function.identity()));
    }

}







