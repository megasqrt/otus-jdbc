package ru.kan.otus.libcat.services;

public interface MessagePrinter {

    public void pm(String message);

    void printBooksTable(Object... args);

    void printBooksHeader();

    void printCommentHeader(String bookTilte);

    void printBookComment(Object... args);
}
