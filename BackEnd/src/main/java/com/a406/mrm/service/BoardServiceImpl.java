package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.BoardRepository;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CategorySubRepository categorySubRepository;
    private final UserRepository userRepository;

    @Override
    public BoardInsertDto join(BoardInsertDto insertDto) throws Exception {
        CategorySub categorySub = categorySubRepository.findById(insertDto.getCategorysub_id());
        User user = userRepository.findById(insertDto.getUser_id()).get();
        BoardInsertDto boardInsertDto = null;

        if(categorySub != null && user != null){
            Board board = new Board(insertDto,categorySub,user);
            board = boardRepository.save(board);
            boardInsertDto = new BoardInsertDto(board);
        }

        return boardInsertDto;
    }

    @Override
    public boolean delete(int id, String user_id) throws Exception {
        Board board = boardRepository.findById(id);

        if (board != null && board.getUser().getId().equals(user_id)){
            boardRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public BoardResponseCommentDto update(BoardModifyDto modifyDto) throws Exception {
        Board board = boardRepository.findById(modifyDto.getId());
        BoardResponseCommentDto boardModifyDto = null;

        if (board != null && board.getUser().getId().equals(modifyDto.getUser_id())){
            board.setTitle(modifyDto.getTitle());
            board.setContent(modifyDto.getContent());
            board.setPicture(modifyDto.getPicture());
            board = boardRepository.save(board);
            boardModifyDto = new BoardResponseCommentDto(board);
        }

        return boardModifyDto;
    }

    @Override
    public Page<BoardResponseDto> listBoard_Pageable(int categorysub_id, Pageable pageable){
        return boardRepository.findBycategorySub_Id(categorysub_id, pageable);
    }

    @Override
    public BoardResponseCommentDto BoardDetail(int board_id) throws Exception {
        Board board = boardRepository.findById(board_id);
        BoardResponseCommentDto result = null;

        if(board != null){
            int views = board.getViews();
            views++;
            board.setViews(views);
            board = boardRepository.save(board);
            result = new BoardResponseCommentDto(board);
        }

        return result;
    }


}