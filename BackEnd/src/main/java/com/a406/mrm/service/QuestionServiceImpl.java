package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.model.entity.Question;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.QuestionRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final CategorySubRepository categorySubRepository;
    private final UserRepository userRepository;

    @Override
    public QuestionResponseAnswerDto join(String title, String content, String user_id, int categorySub_id, MultipartFile picture) throws Exception{
        String uuid =  null;
        if(picture != null){
            uuid = UUID.randomUUID().toString()+"."+picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf(".")+1);
            String absPath = "/img_dir/"+uuid;
//            String absPath = "/Users/dhwnsgh/Desktop/S08P12A406/BackEnd/src/main/resources/img"+uuid;
            try {
                picture.transferTo(new File(absPath));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        CategorySub categorySub = categorySubRepository.findById(categorySub_id);
        User user = userRepository.findById(user_id).get();
        QuestionResponseAnswerDto questionResponseAnswerDto = null;

        if(categorySub != null && user != null){
            Question question = new Question(title, content, uuid, categorySub, user);
            question = questionRepository.save(question);
            questionResponseAnswerDto = new QuestionResponseAnswerDto(question);
        }

        return questionResponseAnswerDto;
    }

    @Override
    public boolean delete(int id, String user_id) throws Exception{
        Question question = questionRepository.findById(id);

        if (question != null && question.getUser().getId().equals(user_id)) {
            questionRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public QuestionModifyDto update(QuestionModifyDto modifyDto) throws Exception{
        Question question = questionRepository.findById(modifyDto.getId());
        QuestionModifyDto questionModifyDto = null;

        if (question != null && question.getUser().getId().equals(modifyDto.getUser_id())){
            question.setTitle(modifyDto.getTitle());
            question.setContent(modifyDto.getContent());
            question.setPicture(modifyDto.getPicture());
            question.setStatus(modifyDto.getStatus());
            question = questionRepository.save(question);

            questionModifyDto = new QuestionModifyDto(question);
        }

        return questionModifyDto;
    }

    @Override
    public QuestionResponseStatusDto status(QuestionResponseStatusDto responseStatusDto) throws Exception{
        Question question = questionRepository.findById(responseStatusDto.getId());
        QuestionResponseStatusDto questionResponseStatusDto = null;

        if(question != null && question.getUser().getId().equals(responseStatusDto.getUser_id())){
            question.setStatus(responseStatusDto.getStatus());
            question = questionRepository.save(question);
            questionResponseStatusDto = new QuestionResponseStatusDto(question);
        }

        return  questionResponseStatusDto;
    }

    @Override
    public Page<QuestionResponseDto> listQuestion_Pageable(int categorysub_id, Pageable pageable) {
        return questionRepository.findBycategorySub_Id(categorysub_id, pageable);
    }

    @Override
    public QuestionResponseAnswerDto QuestionDetail(int question_id) throws Exception{
        Question question = questionRepository.findById(question_id);
        QuestionResponseAnswerDto result = null;

        if(question != null){
            int views = question.getViews();
            views++;
            question.setViews(views);
            question = questionRepository.save(question);

            result = new QuestionResponseAnswerDto(question);
        }

        return result;
    }


}