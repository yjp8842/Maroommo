package com.a406.mrm.service;

import com.a406.mrm.model.dto.AnswerInsertDto;
import com.a406.mrm.model.dto.AnswerModifyDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;

public interface AnswerService {

    AnswerInsertDto join(AnswerInsertDto answerInsertDto);

    String delete(int id, String user_id);

    AnswerModifyDto update(AnswerModifyDto answerModifyDto);


}