package ru.kan.otus.tasker.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.kan.otus.tasker.domain.Task;
import ru.kan.otus.tasker.repositories.TaskRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Контроллер для работы с книгами должен")
class TaskControllerTest {

    private static final String TEST_BOOK_TITLE_1 = "Война и мир";
    private static final String TEST_BOOK_TITLE_2 = "У лукоморья дуб зелёный";
    private static final String TEST_AUTHOR_NAME = "BBB";
    private static final String TEST_GENRE_TITLE = "CCC";

    private static final String TEST_BOOK_TITLE_3 = "Test";
    /* private static final String TEST_BOOK_TITLE_5 = "Гиперион";
     private static final String TEST_BOOK_TITLE_6 = "TEST_TITLE_RENAMED";
 */
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository bookRepository;

    @Test
    @DisplayName("возвращать страницу со списком книг")
    void getTaskListPage() throws Exception {
        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("taskList")))
                .andReturn();
    }

    @Test
    @DisplayName("при успешном создании книги перенаправить на страницу списка книг")
    void createBook() throws Exception {
        mockMvc.perform(post("/task/create")
                .param("title", TEST_BOOK_TITLE_1)
                .param("author", TEST_AUTHOR_NAME)
                .param("genre", TEST_GENRE_TITLE))
                .andExpect(redirectedUrl("/task"));
    }

    @Test
    @DisplayName("возвращать страницу редактирования книги")
    void editBook() throws Exception {
        Optional<Task> task = bookRepository.findById(0L);
        mockMvc.perform(get("/task/edit/?id={id}", task.get().getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Редактирование книги")))
                .andReturn();
    }

    @Test
    @DisplayName("возвращать страницу после изменения поля название книги")
    void updateTitle() throws Exception {
       /* List<Task> book = bookRepository.findByTitle(TEST_BOOK_TITLE_1);
        book.get(0).setTitle(TEST_BOOK_TITLE_3);
        mockMvc.perform(post("/task/edit")
                .flashAttr("bookFromForm", book))
                .andExpect(redirectedUrl("/task/edit?id=" + book.get(0).getId()))
                .andReturn();*/
    }

    @Test
    @DisplayName("возвратить страницу со списком книг после удаления книги")
    void removeBook() throws Exception {
      /*  List<Task> book = bookRepository.findByTitle(TEST_BOOK_TITLE_1);
        val bSize = bookRepository.findAll().size();
        mockMvc.perform(post("/task/remove/{id}", book.get(0).getId()))
                .andExpect(redirectedUrl("/task"))
                .andReturn();

        val aSize = bookRepository.findAll().size();
        Assert.assertEquals(bSize - 1, aSize);*/
    }
}