package ru.otus.lyamin.app.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lyamin.app.dao.CommentRepository;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.exception.LibraryException;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new LibraryException("Comment not fount with id" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(Long bookId) {
        return commentRepository.findCommentByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public Comment saveComment(String text, Long bookId) {
        Book book = bookService.getBookById(bookId);
        return commentRepository.save(validateComment(new Comment(text, book)));
    }

    @Override
    @Transactional
    public int updateCommentTextById(Long id, String text) {
        return commentRepository.updateCommentTextById(id, text);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
         commentRepository.deleteById(id);
    }

    private Comment validateComment(Comment comment) {
        if (comment.getText().isEmpty()) {
            throw new LibraryException("Comment text must be not empty");
        }
        return comment;
    }
}
