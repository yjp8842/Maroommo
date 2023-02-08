package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;

public interface AnswerService {

    AnswerInsertDto join(AnswerInsertDto answerInsertDto);

    String delete(int id, String user_id);

    AnswerModifyDto update(AnswerModifyDto answerModifyDto);
    AnswerGoodDto goodPlus(AnswerGoodDto goodDto);


}