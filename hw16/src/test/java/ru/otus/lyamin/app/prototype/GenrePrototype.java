package ru.otus.lyamin.app.prototype;

import lombok.experimental.UtilityClass;
import ru.otus.lyamin.app.entity.Genre;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class GenrePrototype {
    public static Genre getGenre(){
        return new Genre(1L,"testGenreName1");
    }
    public static Genre getAnotherGenre(){
        return new Genre(2L,"testGenreName2");
    }
    public static Genre getDeletableGenre(){
        return new Genre(3L,"testGenreForDelete");
    }
    public static List<Genre> getGenres(){
        return Arrays.asList(getGenre(),getAnotherGenre(),getDeletableGenre());
    }
}
