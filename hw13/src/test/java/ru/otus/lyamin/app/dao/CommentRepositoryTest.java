package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.ObjectUtils.isEmpty;
import static ru.otus.lyamin.app.dao.QueryCounter.getExpectedQueriesCount;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;

@DisplayName("Класс CommentRepository должен")
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private TestEntityManager em;
    private QueryCounter queryCounter;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        queryCounter = new QueryCounter(em);
    }

    @DisplayName("возвращать комментарий по id книги ")
    @Test
    void shouldReturnCommentByBookId() {
        int expectedCommentCount = 2;
        List<Comment> actualComments = commentRepository.findCommentByBookId(getBook().getId());
        assertThat(actualComments).isNotNull().hasSize(expectedCommentCount)
                .allMatch(c -> !isEmpty(c.getText()))
                .anyMatch(c -> c.getText().equals("testCommentText1"))
                .anyMatch(c -> c.getText().equals("testCommentText2"))
                .allMatch(c -> !isEmpty(c.getBook()));
        assertThat(queryCounter.getQueriesCount()).isEqualTo(getExpectedQueriesCount());
    }

}