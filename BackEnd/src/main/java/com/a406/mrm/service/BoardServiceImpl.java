package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.repository.BoardRepository;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CategorySubRepository categorySubRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CategorySubRepository categorySubRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.categorySubRepository = categorySubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BoardInsertDto join(BoardInsertDto boardInsertDto, int categorysub_id, String user_id) {
        Board board = new Board(boardInsertDto,categorySubRepository.findById(categorysub_id), userRepository.findById(user_id).get());
        return new BoardInsertDto(boardRepository.save(board));
    }

    @Override
    public String delete(int id, String user_id) {
        if (boardRepository.findById(id).getUser().getId().equals(user_id)){
            boardRepository.deleteById(id);
            return "OK";
        }else{
            return "Fail";
        }
    }

    @Override
    public BoardModifyDto update(BoardModifyDto boardModifyDto, int board_id) {
        Board board = boardRepository.findById(board_id);
        board.setTitle(boardModifyDto.getTitle());
        board.setContent(boardModifyDto.getContent());
        board.setPicture(boardModifyDto.getPicture());
        return new BoardModifyDto(boardRepository.save(board));
    }

//    @Override
//    public List<BoardResponseDto> listBoard(int categorySub_id) {
//        List<BoardResponseDto> result = boardRepository.findBycategorySub_Id(categorySub_id)
//                .stream()
//                .map(x -> new BoardResponseDto(x)).collect(Collectors.toList());
//        return result;
//    }

    @Override
    public Page<BoardResponseDto> listBoard_Pageable(int categorysub_id, Pageable pageable) {
        return boardRepository.findBycategorySub_Id(categorysub_id, pageable);
    }

//    @Override
//    public BoardResponseDto detail(int board_id) {
//        BoardResponseDto boardResponseDto = new BoardResponseDto(boardRepository.findById(board_id));
//        return boardResponseDto;
//    }

    @Override
    public List<BoardResponseCommentDto> listBoard(int board_id) {
        List<BoardResponseCommentDto> result = boardRepository.findByid(board_id)
                .stream()
                .map(x -> new BoardResponseCommentDto(x)).collect(Collectors.toList());
        return result;
    }


}