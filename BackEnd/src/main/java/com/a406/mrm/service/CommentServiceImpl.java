package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Comment;
import com.a406.mrm.repository.BoardRepository;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.CommentRepository;
import com.a406.mrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(BoardRepository boardRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentInsertDto join(CommentInsertDto insertDto) {
        Comment comment = new Comment(insertDto,boardRepository.findById(insertDto.getBoard_id()), userRepository.findById(insertDto.getUser_id()).get());
        return new CommentInsertDto(commentRepository.save(comment));
    }

    @Override
    public String delete(int id, String user_id) {
        if (commentRepository.findById(id).getUser().getId().equals(user_id)){
            commentRepository.deleteById(id);
            return "OK";
        }
        return "Fail";
    }

    @Override
    public CommentModifyDto update(CommentModifyDto modifyDto) {
        if (commentRepository.findById(modifyDto.getId()).getUser().getId().equals(modifyDto.getUser_id())){
            Comment comment = commentRepository.findById(modifyDto.getId());
            comment.setContent(modifyDto.getContent());
            return new CommentModifyDto(commentRepository.save(comment));
        }else{
            return null;
        }
    }



}