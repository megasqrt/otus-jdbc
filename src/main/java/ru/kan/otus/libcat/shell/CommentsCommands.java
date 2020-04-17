package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.repositories.CommentRepositoryJpa;

@ShellComponent
@RequiredArgsConstructor
public class CommentsCommands {

    private final CommentRepositoryJpa cJpa;

    @ShellMethod(value = "List all comment by Book id", key = {"lc", "listComment"})
    public String listCommentByBookId(@ShellOption long bookId) {
        cJpa.listCommentsByBookId(bookId).forEach(comments ->
                System.out.println(comments.getText()));
        return "This is all comment in this book";
    }

    @ShellMethod(value = "Add comment by book id", key = {"ac", "addComment"})
    public String addCommentByBookId(@ShellOption long bookId, @ShellOption String text) {
        Comments comments = cJpa.addComment(new Comments(0, text, new Books(bookId, "", null, null, null)));
        return "added comment" + comments.getId();
    }

    @ShellMethod(value = "Delete comment by book id", key = {"dc", "deleteComment"})
    public String deleteCommentByBookId(@ShellOption long bookId) {
        cJpa.deleteById(bookId);
        return "Comment deleted";
    }
}
