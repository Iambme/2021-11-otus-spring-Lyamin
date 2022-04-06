package ru.otus.lyamin.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lyamin.app.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String text;

    public static CommentDto commentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText());
    }

    public static List<CommentDto> commentDtoList(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::commentDto)
                .collect(Collectors.toList());
    }
}

