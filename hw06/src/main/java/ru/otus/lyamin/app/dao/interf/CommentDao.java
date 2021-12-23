package ru.otus.lyamin.app.dao.interf;

import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    Optional<Comment> getCommentById(Long id);
    List<Comment> getCommentsByBookId(Long bookId);
    List<Comment> getComments();
    Comment addComment(Comment comment);
    int updateCommentTextById(Long id, String text);
    int deleteCommentById(Long id);
}
