package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByBookId(Long id);

    @Modifying
    @Query("update Comment a set a.text = :text where a.id = :id")
    int updateCommentTextById(@Param("id") Long id, @Param("text") String text);
}
