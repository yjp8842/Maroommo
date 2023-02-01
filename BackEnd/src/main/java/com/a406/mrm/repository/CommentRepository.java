package com.a406.mrm.repository;


import com.a406.mrm.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment save(Comment comment);

    void deleteById (int id);

    Comment findById(int id);


}