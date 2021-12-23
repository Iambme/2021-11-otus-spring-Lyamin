package ru.otus.lyamin.app.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.lyamin.app.entity.Comment;
import ru.otus.lyamin.app.service.interf.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellController {
    private final CommentService commentService;

    @ShellMethod(value = "Get all Comments", key = {"allc", "all-comments"})
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @ShellMethod(value = "Get Comment by id", key = {"getc", "id-comment"})
    public Comment getCommentById(@ShellOption Long id) {
        return commentService.getCommentById(id);
    }

    @ShellMethod(value = "Get Comment by book id", key = {"getcb", "book-comment"})
    public List<Comment> getCommentByIBookd(@ShellOption Long id) {
        return commentService.getCommentsByBookId(id);
    }

    @ShellMethod(value = "add Comment", key = {"addc", "add-comment"})
    public String addComment(@ShellOption String text, @ShellOption Long bookId) {
        return commentService.addComment(text, bookId).getId().toString();

    }

    @ShellMethod(value = "Update Comment", key = {"updc", "update-comment"})
    public String updateComment(@ShellOption Long id, @ShellOption String name) {
        int result = commentService.updateCommentTextById(id, name);

        return result == 1 ? "Comment updated successfully" : "Comment has not been updated";
    }

    @ShellMethod(value = "Delete Comment by id", key = {"delc", "delete-comment"})
    public String deleteCommentById(@ShellOption Long id) {
        int result = commentService.deleteCommentById(id);

        return result == 1 ? "Comment deleted successfully" : "Comment has not been updated";
    }
}
