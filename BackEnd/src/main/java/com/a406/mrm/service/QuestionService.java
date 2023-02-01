package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionInsertDto join(QuestionInsertDto questionInsertDto, int categorysub_id, String user_id);

    String delete(int id, String user_id);

    QuestionModifyDto update(QuestionModifyDto questionModifyDto, int question_id, String user_id);

    QuestionResponseStatusDto status(QuestionResponseStatusDto questionResponseStatusDto, int question_id, String user_id);

    Page<QuestionResponseDto> listQuestion_Pageable(int categorysub_id, Pageable pageable);


    List<QuestionResponseAnswerDto> listQuestion(int question_id);

}