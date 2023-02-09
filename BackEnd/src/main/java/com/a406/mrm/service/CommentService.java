package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentInsertDto join(CommentInsertDto commentInsertDto) throws Exception;
    boolean delete(int id, String user_id) throws Exception;
    CommentModifyDto update(CommentModifyDto commentModifyDto) throws Exception;
}