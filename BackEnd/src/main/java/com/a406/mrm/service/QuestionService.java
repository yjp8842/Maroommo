package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionResponseAnswerDto join(QuestionInsertDto questionInsertDto) throws Exception;
    boolean delete(int id, String user_id) throws Exception;
    QuestionModifyDto update(QuestionModifyDto questionModifyDto) throws Exception;
    QuestionResponseStatusDto status(QuestionResponseStatusDto questionResponseStatusDto) throws Exception;
    Page<QuestionResponseDto> listQuestion_Pageable(int categorysub_id, Pageable pageable);
    QuestionResponseAnswerDto QuestionDetail(int question_id) throws Exception;

}