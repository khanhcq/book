package com.karlchu.book.core.service.impl;

import com.karlchu.book.core.repository.SequenceRepository;
import com.karlchu.book.core.service.SequenceService;
import com.karlchu.book.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Khanh Chu on 12/27/2018.
 */

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceRepository repository;


    @Override
    public Long getNextSequence(String id) {
        Sequence seq = repository.findById(id);
        Long next = 1l;
        if(seq == null) {
            seq = new Sequence(id, next);
        } else {
            next = seq.getSeq() + 1;
            seq.setSeq(next);
        }
        repository.save(seq);
        return next;
    }
}
