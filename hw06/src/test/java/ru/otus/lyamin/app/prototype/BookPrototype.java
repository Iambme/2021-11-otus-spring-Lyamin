//package ru.otus.lyamin.app.prototype;
//
//import lombok.experimental.UtilityClass;
//import ru.otus.lyamin.app.entity.Book;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAnotherAuthor;
//import static ru.otus.lyamin.app.prototype.AuthorPrototype.getAuthor;
//import static ru.otus.lyamin.app.prototype.GenrePrototype.getAnotherGenre;
//import static ru.otus.lyamin.app.prototype.GenrePrototype.getGenre;
//
//@UtilityClass
//public class BookPrototype {
//    public static Book getBook() {
//        return new Book(1L, "testBookTitle1", getAuthor(), getGenre());
//    }
//
//    public static Book getAnotherBook() {
//        return new Book(2L, "testBookTitle2", getAnotherAuthor(), getAnotherGenre());
//    }
//
//    public static List<Book> getBooks() {
//        return Arrays.asList(getBook(), getAnotherBook());
//    }
//}
