package com.a406.mrm.repository;


import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board save(Board board);

    void deleteById (int id);

    Board findById(int id);

//    Page<Board> findAll(Pageable pageable);

    List<Board> findBycategorySub_Id (@Param("categorysub_Id")int categorysub_Id);

}