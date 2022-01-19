package ru.otus.lyamin.app.dao.custom;

public interface BookRepositoryCustom {
    void updateBookAuthors(String id, String authorName);

    void updateBookGenres(String id, String genreName);

    boolean existsBookWithAuthorId(String id);

    boolean existsBookWithGenreId(String id);
}
