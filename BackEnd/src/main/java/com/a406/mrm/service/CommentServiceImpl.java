package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Comment;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.BoardRepository;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.CommentRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentInsertDto join(CommentInsertDto insertDto) {
        Board board = boardRepository.findById(insertDto.getBoard_id());
        User user = userRepository.findById(insertDto.getUser_id()).get();
        CommentInsertDto commentInsertDto = null;

        if(user != null && board != null){
            Comment comment = new Comment(insertDto,board,user);
            comment = commentRepository.save(comment);
            commentInsertDto = new CommentInsertDto(comment);
        }

        return commentInsertDto;
    }

    @Override
    public boolean delete(int id, String user_id) {
        Comment comment = commentRepository.findById(id);

        if (comment != null && comment.getUser().getId().equals(user_id)){
            commentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public CommentModifyDto update(CommentModifyDto modifyDto) {
        Comment comment = commentRepository.findById(modifyDto.getId());
        CommentModifyDto commentModifyDto = null;

        if (comment != null && comment.getUser().getId().equals(modifyDto.getUser_id())){
            comment.setContent(modifyDto.getContent());
            comment = commentRepository.save(comment);
            commentModifyDto = new CommentModifyDto(comment);
        }

        return commentModifyDto;
    }



}