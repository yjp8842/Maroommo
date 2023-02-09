package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import com.a406.mrm.model.entity.Question;
import com.a406.mrm.model.entity.User;
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
    public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository){
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AnswerInsertDto join(AnswerInsertDto insertDto)
            throws Exception{
        Question question = questionRepository.findById(insertDto.getQuestion_id());
        User user = userRepository.findById(insertDto.getUser_id()).get();
        AnswerInsertDto answerInsertDto = null;

        if(user != null && question != null){
            Answer answer = new Answer(insertDto,question,user);
            answerInsertDto = new AnswerInsertDto(answerRepository.save(answer));
        }

        return answerInsertDto;
    }

    @Override
    public boolean delete(int id, String user_id)
            throws Exception{
        if (answerRepository.findById(id).getUser().getId().equals(user_id)){
            answerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AnswerModifyDto update(AnswerModifyDto modifyDto)
            throws Exception{
        Answer answer = answerRepository.findById(modifyDto.getId());
        AnswerModifyDto answerModifyDto = null;

        if (answer != null && answer.getUser().getId().equals(modifyDto.getUser_id())){
            answer.setContent(modifyDto.getContent());
            answer = answerRepository.save(answer);
            answerModifyDto = new AnswerModifyDto(answer);
        }
        return answerModifyDto;
    }

    @Override
    public int goodPlus(AnswerGoodDto goodDto)
            throws Exception{
        Answer answer = answerRepository.findById(goodDto.getId());
        int goods = -1;

        if(answer != null){
            goods = answer.getGood();
            goods++;
            answer.setGood(goods);
            answerRepository.save(answer);
        }

        return goods;
    }


}