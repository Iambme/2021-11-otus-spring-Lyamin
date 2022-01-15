package ru.otus.lyamin.app.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.ObjectUtils.isEmpty;


@DisplayName("Класс CommentRepository должен")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("возвращать комментарий по id книги ")
    @Test
    void shouldReturnCommentByBookId() {
        String EXISTING_BOOK_ID = bookRepository.findAll().stream().findFirst().orElseThrow().getId();
        int EXPECTED_COMMENT_COUNT = 2;
        List<Comment> actualComments = commentRepository.findCommentByBookId(EXISTING_BOOK_ID);
        assertThat(actualComments).isNotNull().hasSize(EXPECTED_COMMENT_COUNT)
                .allMatch(c -> !isEmpty(c.getText()))
                .anyMatch(c -> c.getText().equals("Comment1"))
                .anyMatch(c -> c.getText().equals("Comment3"))
                .allMatch(c -> !isEmpty(c.getBook()));
    }

}