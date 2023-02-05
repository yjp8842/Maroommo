package com.a406.mrm.service;

import com.a406.mrm.model.dto.AnswerInsertDto;
import com.a406.mrm.model.dto.AnswerModifyDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import com.a406.mrm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    @Autowired
    public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AnswerInsertDto join(AnswerInsertDto insertDto) {
        Answer answer = new Answer(insertDto,questionRepository.findById(insertDto.getQuestion_id()), userRepository.findById(insertDto.getUser_id()).get());
        return new AnswerInsertDto(answerRepository.save(answer));
    }

    @Override
    public String delete(int id, String user_id) {
        if (answerRepository.findById(id).getUser().getId().equals(user_id)){
            answerRepository.deleteById(id);
            return "OK";
        }
        return "Fail";
    }

    @Override
    public AnswerModifyDto update(AnswerModifyDto modifyDto) {
        if (answerRepository.findById(modifyDto.getId()).getUser().getId().equals(modifyDto.getUser_id())){
            Answer answer = answerRepository.findById(modifyDto.getId());
            answer.setContent(modifyDto.getContent());
            return new AnswerModifyDto(answerRepository.save(answer));
        }else{
            return null;
        }
    }



}