package ru.otus.lyamin.app.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.lyamin.app.domain.AuthorJpa;
import ru.otus.lyamin.app.domain.AuthorMongo;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final KeyService keyService;

    public AuthorMongo convert(AuthorJpa authorJpa) {
        var objectId = new ObjectId().toString();

        var authorMongo = new AuthorMongo(objectId, authorJpa.getName());
        keyService.putAuthorKey(authorJpa.getId(), authorMongo.getId());

        return authorMongo;
    }
}