package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.BookDao;
import ru.otus.lyamin.app.entity.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class BookDaoJPA implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> getBookById(Long id) {
        Map<String, Object> graph = Map.of("javax.persistence.fetchgraph", em.getEntityGraph("book-graph"));
        return Optional.ofNullable(em.find(Book.class, id, graph));
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> getBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }


    @Override
    public int deleteBookById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
