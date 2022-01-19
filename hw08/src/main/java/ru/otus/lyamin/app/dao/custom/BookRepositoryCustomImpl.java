package ru.otus.lyamin.app.dao.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.lyamin.app.entity.Book;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public void updateBookAuthors(String id, String authorName) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("author._id").is(id));

        Update update = new Update();
        update.set("author.name", authorName);

        mongoTemplate.updateMulti(query, update, Book.class);
    }

    @Override
    public void updateBookGenres(String id, String genreName) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("genre._id").is(id));

        Update update = new Update();
        update.set("genre.name", genreName);

        mongoTemplate.updateMulti(query, update, Book.class);
    }

    @Override
    public boolean existsBookWithAuthorId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("author._id").is(id));
        return mongoTemplate.exists(query, Book.class);
    }

    @Override
    public boolean existsBookWithGenreId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("genre._id").is(id));
        return mongoTemplate.exists(query, Book.class);
    }


}
