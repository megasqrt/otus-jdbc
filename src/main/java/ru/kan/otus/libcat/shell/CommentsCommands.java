package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.repositories.CommentRepository;
import ru.kan.otus.libcat.services.BooksService;
import ru.kan.otus.libcat.services.CommentsService;

@ShellComponent
@RequiredArgsConstructor
public class CommentsCommands {

    private final CommentRepository commentRepo;
    private final BooksService booksService;
    private final CommentsService commentsService;

    @ShellMethod(value = "List all comment by Book id", key = {"lc", "listComment"})
    public String listCommentByBookId(@ShellOption String bookId) {
        commentsService.printAllByBookId(bookId);
        return "This is all comment in this book";
    }

    @ShellMethod(value = "Add comment by book id", key = {"adc", "addComment"})
    public String addCommentByBookId(@ShellOption String bookId, @ShellOption String text) {
        commentsService.addComment(bookId, text);
        return "added comment";
    }

    @ShellMethod(value = "Delete comment by id", key = {"dc", "deleteComment"})
    public String deleteCommentById(@ShellOption String commentId) {
        commentRepo.delete(commentRepo.findById(commentId).get());
        return "Comment deleted";
    }

    @ShellMethod(value = "Delete comment by book id", key = {"dcb", "deleteCommentByBookId"})
    public String deleteCommentsByBookId(@ShellOption String bookId) {
        commentsService.deleteAllCommentByBookid(bookId);
        return "Comment deleted";
    }
}
