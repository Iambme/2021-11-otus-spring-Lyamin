package ru.otus.lyamin.app.dao.custom;

import ru.otus.lyamin.app.entity.Book;

public interface CommentRepositoryCustom {
    void updateCommentsBook(String id, Book book);
}
