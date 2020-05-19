package ru.kan.otus.libcat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.domain.Comments;
import ru.kan.otus.libcat.repositories.BooksRepository;
import ru.kan.otus.libcat.repositories.CommentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepo;
    private final BooksRepository booksRepo;
    private final MessagePrinter messagePrinter;

    @Override
    public void printAllByBookId(String bookId) {
        Optional<Books> book = booksRepo.findById(bookId);
        messagePrinter.printCommentHeader(book.get().getTitle());
        commentRepo.findAllCommentsByBook(book.get()).forEach(comments ->
                messagePrinter.printBookComment(comments.getId(), comments.getText())
        );
    }

    @Override
    public void addComment(String bookId, String text) {
        Books book = booksRepo.findById(bookId).get();
        commentRepo.save(new Comments("0", text, book));
    }

    @Override
    public void deleteAllCommentByBookid(String bookId) {
        Books book = booksRepo.findById(bookId).get();
        commentRepo.findAllCommentsByBook(book).forEach(commentRepo::delete);
    }


}
