package ru.otus.lyamin.app.dao.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.lyamin.app.entity.Book;
import ru.otus.lyamin.app.entity.Comment;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void updateCommentsBook(String id, Book book) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("book._id").is(id));

        Update update = new Update();
        update.set("book", book);

        mongoTemplate.updateMulti(query, update, Comment.class);
    }
}
