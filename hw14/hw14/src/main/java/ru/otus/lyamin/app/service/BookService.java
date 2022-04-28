package ru.otus.lyamin.app.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.domain.AuthorMongo;
import ru.otus.lyamin.app.domain.BookJpa;
import ru.otus.lyamin.app.domain.BookMongo;
import ru.otus.lyamin.app.domain.GenreMongo;

@RequiredArgsConstructor
@Service
public class BookService {

    private final KeyService keyService;

    public BookMongo convert(BookJpa bookJpa) {
        var objectId = new ObjectId().toString();

        var authorKey =  keyService.getAuthorKey(bookJpa.getAuthor().getId());
        var authorMongo = new AuthorMongo(authorKey, bookJpa.getAuthor().getName());

        var genreKey =  keyService.getGenreKey(bookJpa.getGenre().getId());
        var genreMongo = new GenreMongo(genreKey, bookJpa.getGenre().getName());

        var bookMongo = new BookMongo(objectId, bookJpa.getName(), authorMongo, genreMongo);
        keyService.putBookKey(bookJpa.getId(), bookMongo.getId());

        return bookMongo;
    }
}