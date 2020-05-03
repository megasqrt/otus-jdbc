package ru.kan.otus.libcat.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MessagePrinterImpl implements MessagePrinter {

    private static final String FORMAT_BOOK = "| %-2s| %-30s | %-30s | %-20s |%n";
    private static final String FORMAT_COMMENT = "| %-2s| %-40s |%n";

    @Override
    public void pm(String message) {
        System.out.println(message);
    }

    public void printBooksHeader() {
        printLine("_");
        System.out.printf(
                FORMAT_BOOK,
                "ID",
                StringUtils.center("Title", 30),
                StringUtils.center("Author", 30),
                StringUtils.center("Ganre", 20));
        printLine("-");
    }

    @Override
    public void printCommentHeader(String bookTilte) {
        printLine("_");
        System.out.printf("|%1$-42s|%n", StringUtils.center(bookTilte, 46));
        printLine("-");
        System.out.printf(FORMAT_COMMENT, "ID", StringUtils.center("TEXT", 40));
        printLine("-");
    }

    @Override
    public void printBookComment(Object... args) {
        System.out.printf(FORMAT_COMMENT, args);
        printLine("-");
    }

    @Override
    public void printBooksTable(Object... args) {
        System.out.printf(FORMAT_BOOK, args);
        printLine("-");
    }

    public void printLine(String s) {
        System.out.println(Stream.generate(() -> s).limit(94).collect(Collectors.joining()));
    }
}
