package ru.otus.lyamin.app.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.CommentRepository;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.service.impl.CommentServiceImpl;
import ru.otus.lyamin.app.service.interf.BookService;
import ru.otus.lyamin.app.service.interf.CommentService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.CommentPrototype.getComment;
import static ru.otus.lyamin.app.prototype.CommentPrototype.getComments;

@DisplayName("Класс CommentServiceImpl должен")
@SpringBootTest(classes = CommentServiceImpl.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookService bookService;
    @Autowired
    private CommentService commentService;

    @DisplayName("возвращать комментарий по id ")
    @Test
    void shouldReturnCommentById() {
        Comment expectedComment = getComment();
        when(commentRepository.findById(expectedComment.getId())).thenReturn(Optional.of(expectedComment));
        Comment actualComment = commentService.findById(expectedComment.getId());
        assertThat(actualComment).isNotNull()
                .isInstanceOf(Comment.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
        verify(commentRepository, times(1)).findById(expectedComment.getId());
    }

    @DisplayName("возвращать комментарий по id книги ")
    @Test
    void shouldReturnCommentByName() {
        List<Comment> expectedComments = getComments();
        when(commentRepository.findCommentByBookId(getBook().getId())).thenReturn(expectedComments);
        List<Comment> actualComments = commentService.findByBookId(getBook().getId());
        assertThat(actualComments).isNotNull()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedComments);
        verify(commentRepository, times(1)).findCommentByBookId(getBook().getId());
    }

    @DisplayName("возвращать все комментарии ")
    @Test
    void shouldReturnAllComments() {
        List<Comment> expectedComments = getComments();
        when(commentRepository.findAll()).thenReturn(expectedComments);
        List<Comment> actualComments = commentService.findAll();
        assertThat(actualComments).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedComments);
        verify(commentRepository, times(1)).findAll();
    }

    @DisplayName("корректно добавлять комментарий ")
    @Test
    void shouldCorrectlyAddComment() {
        when(commentRepository.save(any(Comment.class))).thenReturn((getComment()));
        Comment actualComment = commentService.save(getComment());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(getComment());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @DisplayName("корректно обновлять комментарий ")
    @Test
    void shouldCorrectlyUpdateCommentTextById() {
        Comment expectedComment = getComment();
        expectedComment.setText("newText");
        when(commentRepository.save(expectedComment)).thenReturn(expectedComment);
        Comment actualComment = commentService.save(expectedComment);
        assertThat(actualComment).isEqualTo(expectedComment);
        verify(commentRepository, times(1)).save(expectedComment);
    }

    @DisplayName("корректно удалять комментарий ")
    @Test
    void shouldDeleteCommentById() {
        commentService.deleteById(getComment().getId());
        verify(commentRepository, times(1)).deleteById(getComment().getId());
    }
}