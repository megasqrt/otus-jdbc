package ru.kan.otus.tasker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//@WebMvcTest(controllers = TaskController.class)
@DisplayName("Контроллер для работы с книгами должен")
class TaskControllerTest {

    @Test
    public void test1() {

    }
/*
    private static final List<Task> TBL = fillTestBookList();

    private static final String TB_ID = "ID_ID_ID_ID";
    private static final String TB_TITLE = "TEST_BOOK_TITLE";
    private static final String TB_AUTHOR = "TEST_BOOK_AUTHOR";
    private static final String TB_GENRE = "TEST_BOOK_GENRE";

    @Autowired
    TaskRepository bookRepo;
    @Autowired
    AuthorsRepository authorsRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("создавать новую книгу")
    void createBook() throws Exception {

        Task bookCreated = Task.builder().title(TB_TITLE).author( new Authors(TB_AUTHOR)).genre(new Genres(TB_GENRE)).build();
        given(bookRepo.save(bookCreated)).willReturn(bookCreated);

        mockMvc.perform(post("/api/v1/task")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"" + TB_TITLE +
                        "\",\"authors\":\"" + TB_AUTHOR +
                        "\",\"genres\":\"" + TB_GENRE + "\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("получать книгу по ID")
    void getBookById() throws Exception {

        val bookFind = new Book(TB_ID, TB_TITLE,
                Collections.singletonList(new Author(TB_AUTHOR)),
                Collections.singletonList(new Genre(TB_GENRE)));
        given(bookService.findById(TB_ID)).willReturn(java.util.Optional.of(bookFind));

        mockMvc.perform(get("/api/v1/task/" + bookFind.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(bookFind.getAuthors().get(0).getAuthorName())))
                .andDo(print());
    }


    @Test
    @DisplayName("сохранять изменения в книге в базу")
    void updateBook() throws Exception {

        val bookUpdated = new Book(TB_ID, TB_TITLE,
                Collections.singletonList(new Author(TB_AUTHOR)),
                Collections.singletonList(new Genre(TB_GENRE)));

        given(bookService.updateBook(bookUpdated)).willReturn(bookUpdated);

        mockMvc.perform(put("/api/v1/task/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"" + bookUpdated.getId() + "\"," +
                        "\"title\":\"" + bookUpdated.getTitle() + "\"," +
                        "\"authors\":[{\"id\":\"" + bookUpdated.getAuthors().get(0).getId() + "\"," +
                        "\"authorName\":\"" + bookUpdated.getAuthors().get(0).getAuthorName() +"\"}]," +
                        "\"genres\":[{\"id\":\"" + bookUpdated.getGenres().get(0).getId() + "\"," +
                        "\"genreTitle\":\"" + bookUpdated.getGenres().get(0).getGenreTitle() + "\"}]}"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("удалить книгу с заданным ИД")
    void deleteBook() throws Exception {

        val bookRemoved = new Book(TB_ID, TB_TITLE,
                Collections.singletonList(new Author(TB_AUTHOR)),
                Collections.singletonList(new Genre(TB_GENRE)));

        mockMvc.perform(delete("/api/v1/task/" + bookRemoved.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("получить комментарии к книге")
    void getBookCommentsByBookId() throws Exception {

        val bookWithComments = new Book(TB_ID, TB_TITLE,
                Collections.singletonList(new Author(TB_AUTHOR)),
                Collections.singletonList(new Genre(TB_GENRE)));

        val commentList = Collections.singletonList(new Comment("Sample comment 1", bookWithComments));

        given(bookService.findById(bookWithComments.getId())).willReturn(java.util.Optional.of(bookWithComments));
        given(commentService.findAllCommentsByBookId(bookWithComments.getId())).willReturn(commentList);

        mockMvc.perform(get("/api/v1/task/" + bookWithComments.getId() + "/comments/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(commentList.get(0).getCommentText())))
                .andDo(print());
    }


    private static List<Task> fillTestBookList() {
        List<Task> bl = new ArrayList<>();
        Authors author1 = Authors.builder().id(1).fullname("Author1").build();
        Authors author2 = Authors.builder().id(2).fullname("Author2").build();
        Genres genre1 = Genres.builder().id(1).title("Genre1").build();
        Genres genre2 = Genres.builder().id(2).title("Genre2").build();

        bl.add(new Task(1, "Book1", author1, genre1));
        bl.add(new Task(2, "Book2", author2, genre2));
        return bl;
    }*/
}