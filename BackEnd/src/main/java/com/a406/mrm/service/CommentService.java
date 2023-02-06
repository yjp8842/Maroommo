package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentInsertDto join(CommentInsertDto commentInsertDto);

    String delete(int id, String user_id);

    CommentModifyDto update(CommentModifyDto commentModifyDto);


}