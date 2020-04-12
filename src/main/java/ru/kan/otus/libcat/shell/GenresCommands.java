package ru.kan.otus.libcat.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kan.otus.libcat.dao.GenresDao;
import ru.kan.otus.libcat.domain.Genres;

@ShellComponent
@RequiredArgsConstructor
public class GenresCommands {

    private final GenresDao genresDao;

    @ShellMethod(value = "Find genre in catalog by Id", key = {"fgi", "findGenreById"})
    public String findGenreByID(@ShellOption Long id) {
        Genres foundGenres = genresDao.getById(id);
        if (foundGenres == null)
            return "Nothing found";
        return "Find a genre " + foundGenres.getTitle() + " by id:" + id;
    }
}
