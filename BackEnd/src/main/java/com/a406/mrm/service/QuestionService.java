package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {

    QuestionResponseAnswerDto join(String title, String content, String user_id, int categorySub_id, MultipartFile picture) throws Exception;
    boolean delete(int id, String user_id) throws Exception;
    QuestionResponseAnswerDto update(int id, String content, MultipartFile picture, int status, String title, String user_id) throws Exception;
    QuestionResponseStatusDto status(QuestionResponseStatusDto questionResponseStatusDto) throws Exception;
    Page<QuestionResponseDto> listQuestion_Pageable(int categorysub_id, Pageable pageable);
    QuestionResponseAnswerDto QuestionDetail(int question_id) throws Exception;

}