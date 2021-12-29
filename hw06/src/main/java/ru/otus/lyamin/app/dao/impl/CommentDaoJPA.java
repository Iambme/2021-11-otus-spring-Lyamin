package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.CommentDao;
import ru.otus.lyamin.app.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDaoJPA implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getCommentsByBookId(Long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public List<Comment> getComments() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c ", Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public int updateCommentTextById(Long id, String text) {
        Query query = em.createQuery("update Comment c set c.text = :text where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        return query.executeUpdate();
    }

    @Override
    public int deleteCommentById(Long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
