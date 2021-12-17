package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.AuthorDao;
import ru.otus.lyamin.app.entity.Author;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Author> authorRowMapper = new DataClassRowMapper<>(Author.class);

    @Override
    public int countOfAuthors() {
        return jdbc.queryForObject("select count(*) from author", emptyMap(), Integer.class);
    }

    @Override
    public Author getAuthorById(long id) {
        return jdbc.queryForObject("select * from author where id = :id", Map.of("id", id), authorRowMapper);
    }

    @Override
    public List<Author> getAuthors() {
        return jdbc.query("select id, first_name, last_name from author", authorRowMapper);
    }

    @Override
    public long addAuthor(Author author) {
        SqlParameterSource params = new MapSqlParameterSource(Map.of(
                "firstName", author.getFirstName(), "lastName", author.getLastName()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into author (first_name, last_name) values (:firstName, :lastName)", params, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateAuthor(Author author) {
        return jdbc.update("update author set first_name = :firstName, last_name = :lastName where id = :id",
                Map.of("id", author.getId(),
                        "firstName", author.getFirstName(), "lastName", author.getLastName()));
    }

    @Override
    public int deleteAuthorById(long id) {
        return jdbc.update("delete from author where id = :id", Map.of("id", id));
    }
}
