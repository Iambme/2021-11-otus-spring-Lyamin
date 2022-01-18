package ru.otus.lyamin.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lyamin.app.dao.custom.CommentRepositoryCustom;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
    List<Comment> findCommentByBookId(String id);

}
