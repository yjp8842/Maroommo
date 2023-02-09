package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;

public interface AnswerService {

    AnswerInsertDto join(AnswerInsertDto answerInsertDto) throws Exception;
    boolean delete(int id, String user_id) throws Exception;
    AnswerModifyDto update(AnswerModifyDto answerModifyDto) throws Exception;
    int goodPlus(AnswerGoodDto goodDto) throws Exception;
}