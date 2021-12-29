package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.AuthorDao;
import ru.otus.lyamin.app.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class AuthorDaoJPA implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAuthors() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Author saveAuthor(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public int updateAuthorNameById(Long id, String name) {
        Query query = em.createQuery("update Author a set a.name = :name where a.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        return query.executeUpdate();
    }

    @Override
    public int deleteAuthorById(Long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
