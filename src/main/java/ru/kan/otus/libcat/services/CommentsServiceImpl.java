package ru.kan.otus.libcat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kan.otus.libcat.domain.Books;
import ru.kan.otus.libcat.repositories.BooksRepositoryJpa;
import ru.kan.otus.libcat.repositories.CommentRepositoryJpa;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepositoryJpa commentRepo;
    private final BooksRepositoryJpa booksRepo;
    private final MessagePrinter messagePrinter;

    @Override
    public void printAllByBookId(long bookId) {
        Optional<Books> book = booksRepo.findById(bookId);
        messagePrinter.printCommentHeader(book.get().getTitle());
        book.get().getComment().forEach(comments ->
                messagePrinter.printBookComment(comments.getId(), comments.getText())
        );
    }

    @Override
    public void deleteAllCommentByBookid(Long bookId) {
        Books book = booksRepo.findById(bookId).get();
        book.getComment().forEach(commentRepo::delete);
    }


}
