package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;

import static ru.otus.lyamin.app.prototype.BookPrototype.getAnotherBook;
import static ru.otus.lyamin.app.prototype.BookPrototype.getBook;

@UtilityClass
public class CommentPrototype {
    public static Comment getComment() {
        return new Comment(1L, "testCommentText1", getBook());
    }

    public static Comment getAnotherComment() {
        return new Comment(2L, "testCommentText2", getBook());
    }

    public static Comment getDeletableComment() {
        return new Comment(3L, "testCommentTextForDelete", getAnotherBook());
    }

    public static List<Comment> getComments() {
        return List.of(getComment(), getAnotherComment(), getDeletableComment());
    }
}
