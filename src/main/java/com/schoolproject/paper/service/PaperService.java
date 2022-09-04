package com.schoolproject.paper.service;

/**
 * @author Taewoo
 */


import com.schoolproject.paper.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

}
