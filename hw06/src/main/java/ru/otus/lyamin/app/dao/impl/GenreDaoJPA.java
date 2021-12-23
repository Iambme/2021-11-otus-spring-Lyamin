package ru.otus.lyamin.app.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.lyamin.app.dao.interf.GenreDao;
import ru.otus.lyamin.app.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class GenreDaoJPA implements GenreDao {
    @PersistenceContext
    private final EntityManager em;


    @Override
    public Optional<Genre> getGenreById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getGenreByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Genre> getGenres() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre addGenre(Genre genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public int updateGenreNameById(Long id, String name) {
        Query query = em.createQuery("update Genre g set g.name = :name where g.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        return query.executeUpdate();
    }

    @Override
    public int deleteGenreById(Long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
