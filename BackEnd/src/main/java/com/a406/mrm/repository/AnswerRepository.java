package com.a406.mrm.repository;


import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Answer save(Answer answer);

    void deleteById (int id);

    Answer findById(int id);


}