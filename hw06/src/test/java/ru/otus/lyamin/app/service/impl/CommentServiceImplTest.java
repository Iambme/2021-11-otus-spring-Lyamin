package ru.otus.lyamin.app.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lyamin.app.dao.interf.CommentDao;
import ru.otus.lyamin.app.entity.Comment;
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
    private CommentDao commentDao;
    @Autowired
    private CommentService commentService;

    @DisplayName("возвращать комментарий по id ")
    @Test
    void shouldReturnCommentById() {
        Comment expectedComment = getComment();
        when(commentDao.getCommentById(expectedComment.getId())).thenReturn(Optional.of(expectedComment));
        Comment actualComment = commentService.getCommentById(expectedComment.getId());
        assertThat(actualComment).isNotNull()
                .isInstanceOf(Comment.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
        verify(commentDao, times(1)).getCommentById(expectedComment.getId());
    }

    @DisplayName("возвращать комментарий по id книги ")
    @Test
    void shouldReturnCommentByName() {
        List<Comment> expectedComments = getComments();
        when(commentDao.getCommentsByBookId(getBook().getId())).thenReturn(expectedComments);
        List<Comment> actualComments = commentService.getCommentsByBookId(getBook().getId());
        assertThat(actualComments).isNotNull()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedComments);
        verify(commentDao, times(1)).getCommentsByBookId(getBook().getId());
    }

    @DisplayName("возвращать все комментарии ")
    @Test
    void shouldReturnAllComments() {
        List<Comment> expectedComments = getComments();
        when(commentDao.getComments()).thenReturn(expectedComments);
        List<Comment> actualComments = commentService.getComments();
        assertThat(actualComments).isNotEmpty()
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedComments);
        verify(commentDao, times(1)).getComments();
    }

    @DisplayName("корректно добавлять комментарий ")
    @Test
    void shouldCorrectlyAddComment() {
        when(commentDao.addComment(any(Comment.class))).thenReturn((getComment()));
        Comment actualComment = commentService.addComment(getComment().getText(),getBook().getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(getComment());
        verify(commentDao, times(1)).addComment(any(Comment.class));
    }

    @DisplayName("корректно обновлять комментарий ")
    @Test
    void shouldCorrectlyUpdateCommentTextById() {
        int expectedCount = 1;
        when(commentDao.updateCommentTextById(getComment().getId(), getComment().getText())).thenReturn(expectedCount);
        int actualCount = commentService.updateCommentTextById(getComment().getId(), getComment().getText());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(commentDao, times(1)).updateCommentTextById(getComment().getId(), getComment().getText());
    }

    @DisplayName("корректно удалять комментарий ")
    @Test
    void shouldDeleteCommentById() {
        int expectedCount = 1;
        when(commentDao.deleteCommentById(getComment().getId())).thenReturn(expectedCount);
        int actualCount = commentService.deleteCommentById(getComment().getId());
        assertThat(actualCount).isEqualTo(expectedCount);
        verify(commentDao, times(1)).deleteCommentById(getComment().getId());
    }
}