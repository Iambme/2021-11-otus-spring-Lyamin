package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.lyamin.app.entity.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    Mono<Void> deleteByBookId(String id);

}
