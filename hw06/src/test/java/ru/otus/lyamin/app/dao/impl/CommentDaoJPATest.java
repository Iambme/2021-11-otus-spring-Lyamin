package ru.otus.lyamin.app.dao.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.lyamin.app.dao.QueryCounter;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.util.ObjectUtils.isEmpty;
import static ru.otus.lyamin.app.dao.QueryCounter.getExpectedQueriesCount;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;
import static ru.otus.lyamin.app.prototype.CommentPrototype.*;
import static ru.otus.lyamin.app.prototype.CommentPrototype.getDeletableComment;

@DisplayName("Класс CommentDaoJPA должен")
@DataJpaTest
@Import(CommentDaoJPA.class)
class CommentDaoJPATest {

    @Autowired
    private CommentDaoJPA commentDaoJPA;

    @Autowired
    private TestEntityManager em;
    private QueryCounter queryCounter;

    @BeforeEach
    void setUp() {
        queryCounter = new QueryCounter(em);
    }

    @DisplayName("возвращать комментарий по id ")
    @Test
    void shouldReturnCommentById() {
        Optional<Comment> actualComment = commentDaoJPA.getCommentById(getComment().getId());
        Comment expectedComment = em.find(Comment.class, getComment().getId());
        Assertions.assertThat(actualComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("возвращать комментарий по id книги ")
    @Test
    void shouldReturnCommentByBookId() {
        int expectedCommentCount = 2;
        List<Comment> actualComments = commentDaoJPA.getCommentsByBookId(getBook().getId());
        Assertions.assertThat(actualComments).isNotNull().hasSize(expectedCommentCount)
                .allMatch(c -> !isEmpty(c.getText()))
                .anyMatch(c -> c.getText().equals("testCommentText1"))
                .anyMatch(c -> c.getText().equals("testCommentText2"))
                .allMatch(c -> !isEmpty(c.getBook()));
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());

    }

    @DisplayName("возвращать все комментарии ")
    @Test
    void shouldReturnAllComments() {
        List<Comment> actualCommentList = commentDaoJPA.getComments();
        Assertions.assertThat(actualCommentList).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(getComments());
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

    @DisplayName("корректно добавлять комментарий ")
    @Test
    void shouldCorrectlyAddComment() {
        Comment Comment = new Comment("testCommentText", getBook().getId());
        Comment actualComment = commentDaoJPA.addComment(Comment);
        assertThat(actualComment).isNotNull()
                .isInstanceOf(Comment.class)
                .hasFieldOrPropertyWithValue("text", "testCommentText");

    }

    @DisplayName("корректно обновлять комментарий ")
    @Test
    void shouldCorrectlyUpdateComment() {
        Comment expectedComment = getComment();
        expectedComment.setText("testNewText");
        commentDaoJPA.updateCommentTextById(getComment().getId(), "testNewText");
        Optional<Comment> actualComment = commentDaoJPA.getCommentById(getComment().getId());
        Assertions.assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("корректно удалять комментарий ")
    @Test
    void shouldDeleteCommentById() {
        Comment comment = em.find(Comment.class, getDeletableComment().getId());
        Assertions.assertThat(comment).isNotNull();
        em.detach(comment);
        commentDaoJPA.deleteCommentById(getDeletableComment().getId());
        comment = em.find(Comment.class, getDeletableComment().getId());
        Assertions.assertThat(comment).isNull();
    }

}