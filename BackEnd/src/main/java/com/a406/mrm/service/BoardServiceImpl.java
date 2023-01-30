package com.a406.mrm.service;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.repository.BoardRepository;
import com.a406.mrm.repository.CategorySubRepository;
import com.a406.mrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(int id) {
        boardRepository.deleteById(id);
    }

    @Override
    public String update(int id, String title, String content) {
        return null;
    }

}