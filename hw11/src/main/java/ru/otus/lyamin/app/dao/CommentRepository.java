package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.lyamin.app.dao.custom.CommentRepositoryCustom;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {
    Mono<Void> deleteByBookId(String id);

}
