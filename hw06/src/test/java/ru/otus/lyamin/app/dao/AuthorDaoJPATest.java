//package ru.otus.lyamin.app.dao;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import ru.otus.lyamin.app.dao.impl.AuthorDaoJPA;
//import ru.otus.lyamin.app.entity.Author;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThatCode;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static ru.otus.lyamin.app.prototype.AuthorPrototype.*;
//
//@DisplayName("Класс AuthorDaoJPATest должен ")
//@JdbcTest
//@Import(AuthorDaoJPA.class)
//class AuthorDaoJPATest {
//    @Autowired
//    private AuthorDaoJPA authorDaoJPA;
//
//    @DisplayName("корректно возвращать количество авторов ")
//    @Test
//    void shouldReturnCorrectCountOfAuthors() {
//        int expectedCount = getAuthors().size();
//        int actualCount = authorDaoJPA.countOfAuthors();
//        assertThat(actualCount).isEqualTo(expectedCount);
//    }
//
//    @DisplayName("возвращать автора по id ")
//    @Test
//    void shouldReturnAuthorById() {
//        Author author = authorDaoJPA.getAuthorById(getAuthor().getId());
//        Assertions.assertThat(author).isEqualTo(getAuthor());
//    }
//
//    @DisplayName("возвращать всех авторов ")
//    @Test
//    void shouldReturnAllAuthors() {
//        List<Author> actualAuthorList = authorDaoJPA.getAuthors();
//        Assertions.assertThat(actualAuthorList).usingRecursiveFieldByFieldElementComparator()
//                .containsExactlyElementsOf(getAuthors());
//    }
//
//    @DisplayName("корректно добавлять автора ")
//    @Test
//    void shouldCorrectlyAddAuthor() {
//        Author author = new Author(null, "testAuthorName", "testAuthorLastName");
//        long id = authorDaoJPA.save(author);
//        Author actualAuthor = authorDaoJPA.getAuthorById(id);
//        assertThat(actualAuthor).isNotNull()
//                .isInstanceOf(Author.class)
//                .extracting("firstName", "lastName")
//                .doesNotContainNull()
//                .containsExactly("testAuthorName", "testAuthorLastName");
//
//    }
//
//    @DisplayName("корректно обновлять автора ")
//    @Test
//    void shouldCorrectlyUpdateAuthor() {
//        Author expectedAuthor = getAuthor();
//        expectedAuthor.setFirstName("testNewName");
//        authorDaoJPA.updateAuthor(expectedAuthor);
//        Author actualAuthor = authorDaoJPA.getAuthorById(getAuthor().getId());
//        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
//    }
//
//    @DisplayName("корректно удалять автора ")
//    @Test
//    void shouldDeleteAuthorById() {
//        assertThatCode(() -> authorDaoJPA.getAuthorById(getDeletableAuthor().getId())).doesNotThrowAnyException();
//        int countBeforeDelete = authorDaoJPA.getAuthors().size();
//        authorDaoJPA.deleteAuthorById(getDeletableAuthor().getId());
//        int countAfterDelete = authorDaoJPA.countOfAuthors();
//        assertThat(countBeforeDelete).isGreaterThan(countAfterDelete);
//        assertThatThrownBy(() -> authorDaoJPA.getAuthorById(getDeletableAuthor().getId()));
//    }
//}