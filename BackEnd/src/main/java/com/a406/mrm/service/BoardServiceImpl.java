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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
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

//    @Override
//    public BoardInsertDto join(BoardInsertDto insertDto) {
//        Board board = new Board(insertDto,categorySubRepository.findById(insertDto.getCategorysub_id()), userRepository.findById(insertDto.getUser_id()).get());
//        return new BoardInsertDto(boardRepository.save(board));
//    }
    @Override
    public BoardInsertDto join(String title, String content, String user_id, int categorySub_id, MultipartFile image) {
        String uuid =  null;
        if(image != null){
            uuid = UUID.randomUUID().toString()+"."+image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
            String absPath = "/img_dir/"+uuid;
            System.out.println(absPath);
            try {
                image.transferTo(new File(absPath));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        Board board = new Board(title, content, uuid, categorySubRepository.findById(categorySub_id), userRepository.findById(user_id).get());
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
    public BoardModifyDto update(BoardModifyDto modifyDto) {
        if (boardRepository.findById(modifyDto.getId()).getUser().getId().equals(modifyDto.getUser_id())){
            Board board = boardRepository.findById(modifyDto.getId());
            board.setTitle(modifyDto.getTitle());
            board.setContent(modifyDto.getContent());
            board.setPicture(modifyDto.getPicture());
            return new BoardModifyDto(boardRepository.save(board));
        }else{
            return null;
        }
    }

    @Override
    public Page<BoardResponseDto> listBoard_Pageable(int categorysub_id, Pageable pageable) {
        return boardRepository.findBycategorySub_Id(categorysub_id, pageable);
    }

    @Override
    public List<BoardResponseCommentDto> BoardDetail(int board_id) {
        Board board = boardRepository.findById(board_id);
        int views = board.getViews();
        views++;
        board.setViews(views);
        boardRepository.save(board);

        List<BoardResponseCommentDto> result = boardRepository.findByid(board_id)
                .stream()
                .map(x -> new BoardResponseCommentDto(x)).collect(Collectors.toList());
        return result;
    }


}