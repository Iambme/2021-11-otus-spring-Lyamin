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
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new LibraryException("Comment not fount with id" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBookId(String bookId) {
        return commentRepository.findCommentByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public Comment save(String text, String bookId) {
        Book book = bookService.findById(bookId);
        return commentRepository.save(validateComment(new Comment(text, book)));
    }

    @Override
    @Transactional
    public void updateTextById(String id, String text) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentRepository.save(comment);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    private Comment validateComment(Comment comment) {
        if (comment.getText().isEmpty()) {
            throw new LibraryException("Comment text must be not empty");
        }
        return comment;
    }
}
