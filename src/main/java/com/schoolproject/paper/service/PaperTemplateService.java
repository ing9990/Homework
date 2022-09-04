package com.schoolproject.paper.service;

/**
 * @author Taewoo
 */


import com.schoolproject.paper.repository.PaperRepository;
import com.schoolproject.paper.repository.PaperTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
@RequiredArgsConstructor
public class PaperTemplateService {

    private final PaperTemplateRepository paperTemplateRepository;

}
