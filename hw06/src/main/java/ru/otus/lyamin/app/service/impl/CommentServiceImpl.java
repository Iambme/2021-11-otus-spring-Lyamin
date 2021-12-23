package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.interf.CommentDao;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id).orElseThrow(() -> new LibraryException("Comment not fount with id" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(Long bookId) {
        return commentDao.getCommentsByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getComments() {
        return commentDao.getComments();
    }

    @Override
    @Transactional
    public Comment addComment(String text, Long bookId) {
        return commentDao.addComment(new Comment(text, bookId));
    }

    @Override
    @Transactional
    public int updateCommentTextById(Long id, String text) {
        return commentDao.updateCommentTextById(id, text);
    }

    @Override
    @Transactional
    public int deleteCommentById(Long id) {
        return commentDao.deleteCommentById(id);
    }
}
