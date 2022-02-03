package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByBookId(Long id);

}
