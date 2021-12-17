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
import ru.otus.lyamin.app.dao.interf.GenreDao;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Genre> genreRowMapper = new DataClassRowMapper<>(Genre.class);

    @Override
    public int countOfGenres() {
        return jdbc.queryForObject("select count(*) from genre", emptyMap(), Integer.class);
    }

    @Override
    public Genre getGenreById(Long id) {
        return jdbc.queryForObject("select * from genre where id = :id", Map.of("id", id), genreRowMapper);
    }

    @Override
    public List<Genre> getGenres() {
        return jdbc.query("select id, name from genre", genreRowMapper);
    }

    @Override
    public long addGenre(Genre genre) {
        SqlParameterSource params = new MapSqlParameterSource(Map.of("name", genre.getName()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genre (name) values (:name)", params, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateGenre(Genre genre) {
        return jdbc.update("update genre set name = :name where id = :id",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public int deleteGenreById(Long id) {
        return jdbc.update("delete from genre where id = :id", Map.of("id", id));
    }
}
