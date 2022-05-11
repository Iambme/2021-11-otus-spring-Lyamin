package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);

    List<Comment> findByBookId(Long bookId);

    List<Comment> findAll();

    Comment save(Comment comment);

    void deleteById(Long id);
}
