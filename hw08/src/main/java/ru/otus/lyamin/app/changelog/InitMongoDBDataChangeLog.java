package ru.otus.lyamin.app.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.lyamin.app.dao.AuthorRepository;
import ru.otus.lyamin.app.dao.BookRepository;
import ru.otus.lyamin.app.dao.CommentRepository;
import ru.otus.lyamin.app.dao.GenreRepository;
import ru.otus.lyamin.app.entity.Author;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.entity.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private final Author author1 = new Author("Stephen King");
    private final Author author2 = new Author("Agatha Сhristie");
    private final Genre genre1 = new Genre("Horror");
    private final Genre genre2 = new Genre("Detective");
    private final Book book1 = new Book("Book1", author1, genre1);
    private final Book book2 = new Book("Book2", author2, genre2);
    private final Comment comment1 = new Comment("Comment1", book1);
    private final Comment comment2 = new Comment("Comment2", book2);
    private final Comment comment3 = new Comment("Comment3", book1);

    @ChangeSet(order = "000", id = "dropDB", author = "plyamin", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "plyamin", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        repository.save(author1);
        repository.save(author2);
    }

    @ChangeSet(order = "002", id = "initGenres", author = "plyamin", runAlways = true)
    public void initGenres(GenreRepository repository) {
        repository.save(genre1);
        repository.save(genre2);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "plyamin", runAlways = true)
    public void initBooks(BookRepository repository) {
        repository.save(book1);
        repository.save(book2);
    }

    @ChangeSet(order = "004", id = "initComments", author = "plyamin", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(comment1);
        repository.save(comment2);
        repository.save(comment3);
    }
}
