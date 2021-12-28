package ru.otus.lyamin.app.service.interf;

import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Long id);

    List<Comment> getCommentsByBookId(Long bookId);

    List<Comment> getComments();

    Comment saveComment(String text, Long bookId);

    int updateCommentTextById(Long id, String text);

    int deleteCommentById(Long id);
}
