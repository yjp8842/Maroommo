package com.a406.mrm.repository;


import com.a406.mrm.model.dto.BoardResponseDto;
import com.a406.mrm.model.dto.QuestionResponseDto;
import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question save(Question question);

    void deleteById (int id);

    Question findById(int id);

    Page<QuestionResponseDto> findBycategorySub_Id (int categorysub_Id, Pageable pageable);

    List<Question> findByid (int question_id);


}