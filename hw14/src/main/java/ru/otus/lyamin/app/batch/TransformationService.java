package ru.otus.lyamin.app.batch;

import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.entity.Genre;
import ru.otus.lyamin.app.mongoentity.MongoAuthor;
import ru.otus.lyamin.app.mongoentity.MongoBook;
import ru.otus.lyamin.app.mongoentity.MongoComment;
import ru.otus.lyamin.app.mongoentity.MongoGenre;

@Service
public class TransformationService {

    public Book transformBook(MongoBook mongoBook) {
        MongoAuthor mongoAuthor = mongoBook.getAuthor();
        MongoGenre mongoGenre = mongoBook.getGenre();

        return new Book(null,
                mongoBook.getName(),
                new Author(null, mongoAuthor.getName(), mongoAuthor.getId()),
                new Genre(null, mongoGenre.getName(), mongoGenre.getId()),
                mongoBook.getId());
    }

    public Comment transformComment(MongoComment mongoComment) {
        MongoBook mongoBook = mongoComment.getBook();

        return new Comment(null,
                mongoComment.getText(),
                new Book(null, null, null, null, mongoBook.getId()));
    }
}
