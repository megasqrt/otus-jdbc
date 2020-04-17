package ru.kan.otus.libcat.domain;

public class Authors extends Entity {
    private final String fullName;

    public Authors(Long id, String fullName) {
        super(id);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
