package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.BookDao;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;


@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Book> bookRowMapper = new BookMapper();

    @Override
    public int countOfBooks() {
        return jdbc.queryForObject("select count(*) from book", emptyMap(), Integer.class);
    }

    @Override
    public Book getBookById(Long id) {
        String sql = "select b.id as book_id, b.title as book_title, " +
                "a.id as author_id, a.first_name as author_first_name, a.last_name as author_last_name, " +
                "g.id as genre_id, g.name as genre_name from book b " +
                "left join  author a  on a.id = b.author_id " +
                "left join  genre g  on g.id = b.genre_id " +
                "where b.id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id), bookRowMapper);
    }

    @Override
    public List<Book> getBooks() {
        String sql = "select b.id as book_id, b.title as book_title, " +
                "a.id as author_id, a.first_name as author_first_name, a.last_name as author_last_name, " +
                "g.id as genre_id, g.name as genre_name from book b " +
                "left join  author a  on a.id = b.author_id " +
                "left join  genre g  on g.id = b.genre_id";
        return jdbc.query(sql, bookRowMapper);
    }

    @Override
    public Long addBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource(Map.of(
                "title", book.getTitle(), "authorId", book.getAuthor().getId(), "genreId", book.getGenre().getId()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into book (`title`, author_id, genre_id) values (:title, :authorId, :genreId)",
                params, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateBook(Book book) {
        return jdbc.update("update book set `title` = :title, author_id = :authorId, genre_id = :genreId where id = :id",
                Map.of("id", book.getId(), "title", book.getTitle(),
                        "authorId", book.getAuthor().getId(), "genreId", book.getGenre().getId()));
    }

    @Override
    public int deleteBookById(Long id) {
        return jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author(resultSet.getLong("author_id"),
                    resultSet.getString("author_first_name"),
                    resultSet.getString("author_last_name"));

            Genre genre = new Genre(resultSet.getLong("genre_id"),
                    resultSet.getString("genre_name"));

            return new Book(resultSet.getLong("book_id"),
                    resultSet.getString("book_title"),
                    author,
                    genre);
        }
    }
}
