package ru.otus.lyamin.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lyamin.app.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private String id;
    private String name;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }


    public static List<AuthorDto> toDtoList(List<Author> entities) {
        return entities.stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    public static Author toEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName());
    }


    public static List<Author> toEntityList(List<AuthorDto> dto) {
        return dto.stream().map(AuthorDto::toEntity).collect(Collectors.toList());
    }
}
