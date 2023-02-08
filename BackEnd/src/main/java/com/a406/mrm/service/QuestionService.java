package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionInsertDto join(QuestionInsertDto questionInsertDto);

    String delete(int id, String user_id);

    QuestionModifyDto update(QuestionModifyDto questionModifyDto);

    QuestionResponseStatusDto status(QuestionResponseStatusDto questionResponseStatusDto);

    Page<QuestionResponseDto> listQuestion_Pageable(int categorysub_id, Pageable pageable);


    List<QuestionResponseAnswerDto> QuestionDetail(int question_id);

}