package ru.otus.lyamin.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lyamin.app.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    private Long id;
    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }


    public static List<GenreDto> toDtoList(List<Genre> entities) {
        return entities.stream().map(GenreDto::toDto).collect(Collectors.toList());
    }

    public static Genre toEntity(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getName());
    }


    public static List<Genre> toEntityList(List<GenreDto> dto) {
        return dto.stream().map(GenreDto::toEntity).collect(Collectors.toList());
    }
}
