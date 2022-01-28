package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment findById(String id);

    List<Comment> getByBookId(String bookId);

    List<Comment> findAll();

    Comment save(String text, String bookId);

    void updateTextById(String id, String text);

    void deleteById(String id);
}
