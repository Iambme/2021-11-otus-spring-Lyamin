package ru.otus.lyamin.app.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.service.interf.CommentService;

import static ru.otus.lyamin.app.dto.CommentDto.commentDto;
import static ru.otus.lyamin.app.dto.CommentDto.commentDtoList;

@ShellComponent
public class CommentShellController {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    public CommentShellController(CommentService commentService) {
        this.commentService = commentService;
        this.objectMapper = new ObjectMapper();
    }

    @ShellMethod(value = "Get all Comments", key = {"allc", "all-comments"})
    public String getComments() throws JsonProcessingException {
        return objectMapper.writeValueAsString(commentDtoList(commentService.findAll()));
    }

    @ShellMethod(value = "Get Comment by id", key = {"getc", "id-comment"})
    public String getCommentById(@ShellOption String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(commentDto(commentService.findById(id)));
    }

    @ShellMethod(value = "Get Comment by book id", key = {"getcb", "book-comment"})
    public String getCommentByIBookId(@ShellOption String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(commentService.getByBookId(id));
    }

    @ShellMethod(value = "add Comment", key = {"addc", "add-comment"})
    public String addComment(@ShellOption String text, @ShellOption String bookId) throws JsonProcessingException {
        return objectMapper.writeValueAsString(commentService.save(text, bookId));
    }

    @ShellMethod(value = "Update Comment", key = {"updc", "update-comment"})
    public void updateComment(@ShellOption String id, @ShellOption String name) throws JsonProcessingException {
        commentService.updateTextById(id, name);
    }

    @ShellMethod(value = "Delete Comment by id", key = {"delc", "delete-comment"})
    public void deleteCommentById(@ShellOption String id) {
        commentService.deleteById(id);
    }
}
